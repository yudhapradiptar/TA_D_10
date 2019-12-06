package apap.tugasakhir.siruangan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugasakhir.siruangan.model.*;
import apap.tugasakhir.siruangan.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PengadaanFasilitasController {
    @Qualifier("pengadaanFasilitasServiceImpl")
    @Autowired
    private PengadaanFasilitasService pengadaanFasilitasService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/pengadaan-fasilitas/add", method = RequestMethod.GET)
    public String addPengadaanFormPage(Model model) {
        PengadaanFasilitasModel newPengadaan = new PengadaanFasilitasModel();
        String title = "Form Pengajuan Pengadaan Fasilitas";
        model.addAttribute("pengadaan", newPengadaan);
        model.addAttribute("title", title);
        model.addAttribute("role", userService.getUserRole());

        return "form-pengadaan";
    }

    @RequestMapping(value = "/pengadaan-fasilitas", method = RequestMethod.POST)
    public String addPengadaanSubmit(@ModelAttribute PengadaanFasilitasModel pengadaanFasilitas, @AuthenticationPrincipal UserDetails currentUser, Model model) {
        UserModel userLoggedIn = userService.getUserByUsername(currentUser.getUsername());
        try {
            pengadaanFasilitasService.generateStatusPengadaanAndIdUser(pengadaanFasilitas, userLoggedIn);
            pengadaanFasilitasService.addPengadaanFasilitas(pengadaanFasilitas);
            List<PengadaanFasilitasModel> listPengadaan = pengadaanFasilitasService.getListPengadaanFasilitas();
            String title = "Pengadaan Fasilitas";
            model.addAttribute("listPengadaan", listPengadaan);
            model.addAttribute("title", title);
            String notifGood = "Pengadaan Fasilitas " + pengadaanFasilitas.getNama() + " telah berhasil diajukan";
            String notifBad = "";
            return viewAllPengadaan(currentUser, notifBad, notifGood, model);
        } catch (NullPointerException e) {
            return "form-pengadaan";
        }
    }

    @RequestMapping(value = "/pengadaan-fasilitas/hapus/{idPengadaan}", method = RequestMethod.GET)
    public String deletePengadaan(@PathVariable Long idPengadaan, @AuthenticationPrincipal UserDetails currentUser, Model model) {
        PengadaanFasilitasModel pengadaanDeleted = pengadaanFasilitasService.getPengadaanByIdPengadaan(idPengadaan);
        UserModel userLoggedIn = userService.getUserByUsername(currentUser.getUsername());
        String notifBad = "Pengadaan Fasilitas " + pengadaanDeleted.getNama() + " telah berhasil dihapus";
        String notifGood = "";
        if(userLoggedIn.getRole().getIdRole()==3){
            if(pengadaanDeleted.getUser()==userLoggedIn){
                pengadaanFasilitasService.deletePengadaan(pengadaanDeleted);
            }
        } else if(userLoggedIn.getRole().getIdRole()==2){
            pengadaanFasilitasService.deletePengadaan(pengadaanDeleted);
        }
        return viewAllPengadaan(currentUser, notifBad, notifGood, model);
    }

    @RequestMapping("/pengadaan-fasilitas")
    public String viewAllPengadaan(@AuthenticationPrincipal UserDetails currentUser, String notifBad, String notifGood, Model model) {
        List<PengadaanFasilitasModel> listPengadaan = pengadaanFasilitasService.getListPengadaanFasilitas();
        UserModel userLoggedIn = userService.getUserByUsername(currentUser.getUsername());
        List<PengadaanFasilitasModel> listPengadaanGuru = new ArrayList<>();
        if (userLoggedIn.getRole().getIdRole() == 2) {
            model.addAttribute("listPengadaan", listPengadaan);
        } else if (userLoggedIn.getRole().getIdRole() == 3) {
            for (PengadaanFasilitasModel pengadaan : listPengadaan) {
                if (pengadaan.getUser() == userLoggedIn) {
                    listPengadaanGuru.add(pengadaan);
                }
            }
            model.addAttribute("listPengadaan", listPengadaanGuru);
        }
        model.addAttribute("notifBad", notifBad);
        model.addAttribute("notifGood", notifGood);

        model.addAttribute("title", "Pengadaan Fasilitas");
        model.addAttribute("role", userLoggedIn.getRole().getNama());

        return "viewall-pengadaan";
    }
}
