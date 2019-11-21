package apap.tugasakhir.siruangan.controller;

import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.rest.PengadaanBukuDetail;
import apap.tugasakhir.siruangan.service.PengadaanBukuRestService;
import apap.tugasakhir.siruangan.service.RoleService;
import apap.tugasakhir.siruangan.service.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;

@Controller
@RequestMapping("/pengadaan-buku")
public class PengadaanBukuController {
    @Autowired
    private UserService userService;

    @Autowired
    private PengadaanBukuRestService bukuRestService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    private String createPengadaanBukuFormPage(Model model) {
        PengadaanBukuDetail buku = new PengadaanBukuDetail();
        model.addAttribute("buku", buku);
        return "form-pengadaan-buku";
    }

    @PostMapping(value = "")
    private String createPengadaanBukuSubmit(@RequestParam String judul,
                                 @RequestParam String pengarang,
                                 @RequestParam String penerbit,
                                 @RequestParam String jumlah,
                                 @RequestParam String harga,
                                 @ModelAttribute PengadaanBukuDetail buku,
                                 @AuthenticationPrincipal UserDetails currentUser,
                                 RedirectAttributes redirect) throws ParseException, JSONException {

        buku.setUuid(userService.getUserByUsername(currentUser.getUsername()).getIdUser());
        buku.setJudul(judul);
        buku.setPengarang(pengarang);
        buku.setPenerbit(penerbit);
        buku.setJumlah(jumlah);
        buku.setHarga(harga);
        bukuRestService.generateStatusBuku(buku, currentUser);
        if(bukuRestService.createPengadaanBuku(currentUser, buku).block().getStatus().equals("200")){
            return "home";
        }
        return "home";
    }
}
