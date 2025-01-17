package apap.tugasakhir.siruangan.controller;


import apap.tugasakhir.siruangan.model.FasilitasModel;
import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;
import apap.tugasakhir.siruangan.model.RuanganModel;
import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.service.FasilitasRuanganService;
import apap.tugasakhir.siruangan.service.FasilitasService;
import apap.tugasakhir.siruangan.service.RuanganService;
import apap.tugasakhir.siruangan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/ruangan")
public class RuanganController {

    @Qualifier("ruanganServiceImpl")
    @Autowired
    private RuanganService ruanganService;

    @Autowired
    private FasilitasRuanganService fasilitasRuanganService;

    @Autowired
    private FasilitasService fasilitasService;
    
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/view/{idRuangan}", method = RequestMethod.GET)
    public String viewRuangan(@PathVariable Long idRuangan, Model model){

        RuanganModel ruangan = ruanganService.getRuanganByIdRuangan(idRuangan).get();
        HashMap<FasilitasModel, Integer> pairOfFasilitasAndJumlah = fasilitasRuanganService.getFasilitasDanJumlah(ruangan);
        boolean isPinjamRuanganAuthorized;
        boolean isTindakanAuthorized;
        UserModel currentLoggedInUser = userService.getCurrentLoggedInUser();
        if(currentLoggedInUser.getRole().getNama().equals("Admin TU")) {
            isTindakanAuthorized = true;
        } else {
            isTindakanAuthorized = false;
        }
        if(currentLoggedInUser.getRole().getNama().equals("Guru") || currentLoggedInUser.getRole().getNama().equals("Siswa")) {
            isPinjamRuanganAuthorized = true;
        } else {
            isPinjamRuanganAuthorized = false;
        }
        List<FasilitasRuanganModel> fasilitasRuang = ruanganService.getFasilitasRuanganList(idRuangan);
        String pageTitle = "Detil Ruangan";
        model.addAttribute("title", pageTitle);
        model.addAttribute("ruangan", ruangan);
        model.addAttribute("role", userService.getUserRole());
        model.addAttribute("fasilitasJumlah", pairOfFasilitasAndJumlah);
        model.addAttribute("isPinjamRuanganAuthorized", isPinjamRuanganAuthorized);
        model.addAttribute("fasilitasRuang", fasilitasRuang);
        model.addAttribute("isTindakanAuthorized", isTindakanAuthorized);
        return "view-ruangan";
    }

    @RequestMapping("/daftar-ruangan")
    public String daftarRuangan (Model model){
        List<RuanganModel> listRuanganModel = ruanganService.getRuanganList();
        model.addAttribute("listRuanganModel", listRuanganModel);
        model.addAttribute("role", userService.getUserRole());
        return "list-ruangan";
    }
}
