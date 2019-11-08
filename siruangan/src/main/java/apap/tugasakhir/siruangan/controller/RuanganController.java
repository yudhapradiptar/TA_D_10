package apap.tugasakhir.siruangan.controller;


import apap.tugasakhir.siruangan.model.FasilitasModel;
import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;
import apap.tugasakhir.siruangan.model.RuanganModel;
import apap.tugasakhir.siruangan.service.FasilitasRuanganService;
import apap.tugasakhir.siruangan.service.FasilitasService;
import apap.tugasakhir.siruangan.service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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



    @RequestMapping(path = "/view/{idRuangan}", method = RequestMethod.GET)
    public String viewRuangan(@PathVariable Long idRuangan, Model model){
        RuanganModel ruangan = ruanganService.getRuanganByIdRuangan(idRuangan).get();
        List<FasilitasModel> listFasilitas = fasilitasRuanganService.getFasilitasList(ruangan);
        List<FasilitasRuanganModel> listFasilitarRuangan = fasilitasRuanganService.getFasilitasRuanganByRuangan(ruangan);
        
    }

}
