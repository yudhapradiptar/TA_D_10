package apap.tugasakhir.siruangan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.rest.GuruDetail;
import apap.tugasakhir.siruangan.rest.SiswaDetail;
import apap.tugasakhir.siruangan.service.RoleService;
import apap.tugasakhir.siruangan.service.UserRestService;
import apap.tugasakhir.siruangan.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRestService userRestService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, 
    @RequestParam("passwordKonfirmasi") String confirmPassword, Model model){
        String messages = "";

        UserModel checkUser = userService.getUserByUsername(user.getUsername());
        if(checkUser != null) {
            messages = "Username has been taken. Refresh the page then try again.";
            model.addAttribute("message3", messages);
            return "form-add-user";
        }

        if (validatePassword(user.getPassword())) {
            if(confirmPassword.equals(user.getPassword())){
                messages = "User berhasil ditambahkan.";
                userService.addUser(user);
                model.addAttribute("message4", messages);
                return "add-user-success";
            }
            else{
                messages = "Your password and confirmation password do not match. Refresh the page then try again.";
                model.addAttribute("message2", messages);
                return "form-add-user";
            }
        }
        else {
            messages = "Your password must be at least 8 characters long and contain minimum one number and one letter. Refresh the page then try again.";
            model.addAttribute("message", messages);
            return "form-add-user";
        }
    }

    @PostMapping(value = "/addUser")
    private String addUserSubmit(@ModelAttribute UserModel user,
                                @RequestParam(required = false) String nama, 
                                @RequestParam(required = false) String tempatLahir,
                                @RequestParam(required = false) String tanggalLahir,
                                @RequestParam(required = false) String alamat,
                                @RequestParam(required = false) String telepon,    
                                RedirectAttributes redirect) throws ParseException{
        userService.addUser(user);
        Date tanggalLahirUser = null;
        if (tanggalLahir != null){
            tanggalLahirUser =  new SimpleDateFormat("yyyy-mm-dd").parse(tanggalLahir);
        }
        if(user.getRole().getNama().equals("Guru")){
            GuruDetail guru = new GuruDetail();
            // String nig = userService.generateNig(user, tanggalLahirUser);
            guru.setNama(nama);
            guru.setAlamat(alamat);
            guru.setTempatLahir(tempatLahir);
            guru.setTanggalLahir(tanggalLahirUser);
            guru.setTelepon(telepon);
            // guru.setNig(nig);
            if(userRestService.addGuru(user, guru).block().getStatus()=="200"){
                return "redirect:/";
            }
        }
        else if(user.getRole().getNama().equals("Siswa")){
            SiswaDetail siswa = new SiswaDetail();
            // String nis = userService.generateNis(user, tanggalLahirUser);
            siswa.setNama(nama);
            siswa.setAlamat(alamat);
            siswa.setTempatLahir(tempatLahir);
            siswa.setTanggalLahir(tanggalLahirUser);
            siswa.setTelepon(telepon);
            // siswa.setNis(nis);
            if(userRestService.addSiswa(user, siswa).block().getStatus()=="200"){
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

    public boolean validatePassword(String password) {
        if (password.length()>=8 && Pattern.compile("[0-9]").matcher(password).find() &&  Pattern.compile("[a-zA-Z]").matcher(password).find())  {
            return true;
        }
        else {
            return false;
        }
    }
}
