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

import apap.tugasakhir.siruangan.model.RoleModel;
import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.rest.GuruDetail;
import apap.tugasakhir.siruangan.rest.SiswaDetail;
import apap.tugasakhir.siruangan.service.RoleService;
import apap.tugasakhir.siruangan.service.UserRestService;
import apap.tugasakhir.siruangan.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
// @RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRestService userRestService;

    @RequestMapping(value = "/user/add-user", method = RequestMethod.GET)
    private String addUserSubmit(Model model) {
        List<RoleModel> listRole= roleService.findAll().subList(2,4);
        model.addAttribute("listRole", listRole );
        String pageTitle = "Tambah Anggota";
        model.addAttribute("title", pageTitle);
        return "form-add-user";
    }

    @PostMapping(value = "/user/add-user")
    private String addUserSubmit(@ModelAttribute UserModel user,
                                @RequestParam String nama, 
                                @RequestParam String tempatLahir,
                                @RequestParam String tanggalLahir,
                                @RequestParam String alamat,
                                @RequestParam String telepon,    
                                RedirectAttributes redirect) throws ParseException{
        userService.addUser(user);
        // Date tanggalLahirUser = new SimpleDateFormat("yyyy-mm-dd").parse(tanggalLahir);
        if(user.getRole().getNama().equals("Guru")){
            GuruDetail guru = new GuruDetail();
            String nig = userService.generateNig(user, tanggalLahir);
            guru.setNig(nig);
            guru.setNama(nama);
            guru.setTempatLahir(tempatLahir);
            guru.setTanggalLahir(tanggalLahir);
            guru.setAlamat(alamat);
            guru.setTelepon(telepon);
            if(userRestService.addGuru(user, guru).block().getStatus()=="200"){
                return "home";
            }
        }
        else {
            SiswaDetail siswa = new SiswaDetail();
            String nis = userService.generateNis(user, tanggalLahir);
            siswa.setNis(nis);
            siswa.setNama(nama);
            siswa.setTempatLahir(tempatLahir);
            siswa.setTanggalLahir(tanggalLahir);
            siswa.setAlamat(alamat);
            siswa.setTelepon(telepon);
            if(userRestService.addSiswa(user, siswa).block().getStatus()=="200"){
                return "redirect:/";
            }
        }
        return "redirect:/";
    }
}
