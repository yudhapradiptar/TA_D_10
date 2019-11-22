package apap.tugasakhir.siruangan.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import apap.tugasakhir.siruangan.model.RuanganModel;
import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.service.PeminjamanRuanganService;
import apap.tugasakhir.siruangan.service.RuanganService;
import apap.tugasakhir.siruangan.service.UserService;


@Controller
@RequestMapping("/peminjaman-ruangan")
public class PeminjamanRuanganController {
    @Autowired
    RuanganService ruanganService;

    @Autowired
    UserService userService;

    @Autowired
    PeminjamanRuanganService peminjamanRuanganService;
    
    @RequestMapping(value = "/pinjam", method = RequestMethod.GET)
    public String peminjamanRuanganFormPage(@RequestParam(value = "idRuangan") Long idRuangan,
                                            Model model)
    {
        String message = "";
        RuanganModel ruangan = ruanganService.getRuanganByIdRuangan(idRuangan).get();
        model.addAttribute("ruangan", ruangan);
        model.addAttribute("message", message);
        return "form-peminjaman-ruangan";
    }


    @RequestMapping(value = "/pinjam", method = RequestMethod.POST)
    public String peminjamanRuanganSubmitButton(@RequestParam(value = "idRuangan") Long idRuangan,
                                                @ModelAttribute PeminjamanRuanganModel peminjaman,
                                                Model model)
    {   
        String message;
        RuanganModel ruangan = ruanganService.getRuanganByIdRuangan(idRuangan).get();
        UserModel userPeminjam = userService.getCurrentLoggedInUser();
        if(peminjamanRuanganService.dateTimeValidation(peminjaman) 
            && peminjamanRuanganService.capacityValidation(peminjaman)) {
            peminjaman.setUserPeminjam(userPeminjam);
            peminjamanRuanganService.mengajukanPeminjamanRuangan(peminjaman);
            message = "Pengajuan peminjaman ruangan berhasil!";
            
        } else {
            message = "Ruangan tidak tersedia pada waktu tersebut atau jumlah peserta melebihi kapasitas";
        }
       
        model.addAttribute("ruangan", ruangan);
        model.addAttribute("message", message);
        return "form-peminjaman-ruangan";
    }

    @RequestMapping(value = "/daftar", method = RequestMethod.GET)
    public String pengajuanPeminjamanPageList(Model model) {
        UserModel currentLoggedInUser = userService.getCurrentLoggedInUser();
        boolean isTindakanAuthorized;
        if(currentLoggedInUser.getRole().getNama().equals("Admin TU")) {
            isTindakanAuthorized = true;
        } else {
            isTindakanAuthorized = false;
        }
        List<PeminjamanRuanganModel> listPeminjamanRuangan = peminjamanRuanganService.getPeminjamanRuanganList();
        List<String> editedDateFormatTanggalMulaiStrList = new ArrayList<String>();
        List<String> editedDateFormatTanggalSelesaiStrList = new ArrayList<String>();
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMM yyyy");
        for(PeminjamanRuanganModel peminjaman : listPeminjamanRuangan) {
            String newDateFormatTanggalMulaiStr = newDateFormat.format(peminjaman.getTanggalMulai());
            String newDateFormatTanggalSelesaiStr = newDateFormat.format(peminjaman.getTanggalSelesai());
            editedDateFormatTanggalMulaiStrList.add(newDateFormatTanggalMulaiStr);
            editedDateFormatTanggalSelesaiStrList.add(newDateFormatTanggalSelesaiStr);
        }
        model.addAttribute("isTindakanAuthorized", isTindakanAuthorized);
        model.addAttribute("listPeminjamanRuangan", listPeminjamanRuangan);
        model.addAttribute("listTanggalMulai", editedDateFormatTanggalMulaiStrList);
        model.addAttribute("listTanggalSelesai", editedDateFormatTanggalSelesaiStrList);
        model.addAttribute("role", userService.getUserRole());
        return "viewall-peminjaman-ruangan";
    }

    @RequestMapping(value="/status-peminjaman/{idPeminjamanRuangan}", method = RequestMethod.POST)
    public String changePeminjamanStatusSubmit(@PathVariable Long idPeminjamanRuangan, @ModelAttribute PeminjamanRuanganModel peminjaman, 
    @RequestParam(value="status") int status , Model model){
            if(status == 1){
                PeminjamanRuanganModel newStatus = peminjamanRuanganService.changeStatus(peminjaman, 1);
                model.addAttribute("statusPeminjaman", newStatus);
                return "redirect:/peminjaman-ruangan/daftar";
            }
            else{
                PeminjamanRuanganModel newStatus = peminjamanRuanganService.changeStatus(peminjaman, 2);
                model.addAttribute("statusPeminjaman", newStatus);
                return "redirect:/peminjaman-ruangan/daftar";
            }
    }
}

