package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.FasilitasModel;
import apap.tugasakhir.siruangan.model.FasilitasRuanganModel;
import apap.tugasakhir.siruangan.model.RuanganModel;
import apap.tugasakhir.siruangan.repository.FasilitasRuanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class FasilitasRuanganServiceImpl implements FasilitasRuanganService {

    @Autowired
    FasilitasRuanganDB fasilitasRuanganDB;

    @Qualifier("fasilitasRuanganServiceImpl")
    @Autowired
    FasilitasRuanganService fasilitasRuanganService;

    @Autowired
    FasilitasService fasilitasService;

    @Override
    public List<FasilitasRuanganModel> getFasilitasRuanganByRuangan (RuanganModel ruangan){
        return fasilitasRuanganDB.findAllByRuangan(ruangan);
    }

    @Override
    public List<FasilitasModel> getFasilitasList (RuanganModel ruangan){
        List<FasilitasModel> listFasilitas = new ArrayList<>();
        List<FasilitasRuanganModel> fasilitasRuangan = fasilitasRuanganDB.findAllByRuangan(ruangan);
        for (FasilitasRuanganModel a : fasilitasRuangan){
            FasilitasModel theFasilitas = fasilitasService.getFasilitasByIdFasilitas(a.getFasilitas().getIdFasilitas()).get();
            listFasilitas.add(theFasilitas);
        }

        return listFasilitas;
    }

    @Override
    public HashMap<FasilitasModel, Integer> getFasilitasDanJumlah (RuanganModel ruangan){
        HashMap<FasilitasModel,Integer> fasilitasDanJumlah = new HashMap<FasilitasModel,Integer>();
        List<FasilitasRuanganModel> listFasilitasRuangan = fasilitasRuanganService.getFasilitasRuanganByRuangan(ruangan);
        for (FasilitasRuanganModel a : listFasilitasRuangan){
            fasilitasDanJumlah.put(fasilitasService.getFasilitasByIdFasilitas(a.getFasilitas().getIdFasilitas()).get(),a.getJumlahFasilitas());
        }

        return fasilitasDanJumlah;
    }

    @Override
    public void addFasilitasRuangan(FasilitasRuanganModel fasilitasRuanganModel) {
        fasilitasRuanganDB.save(fasilitasRuanganModel);
    }

    @Override
    public FasilitasRuanganModel getFasilitasRuanganById(Long id) {
        return fasilitasRuanganDB.findById(id).get();
    }

}
