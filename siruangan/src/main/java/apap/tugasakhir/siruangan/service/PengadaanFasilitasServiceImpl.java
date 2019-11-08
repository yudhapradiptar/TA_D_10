package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;
import apap.tugasakhir.siruangan.repository.PengadaanFasilitasDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PengadaanFasilitasServiceImpl implements PengadaanFasilitasService{
    @Autowired
    private PengadaanFasilitasDB pengadaanFasilitasDb;

    @Override
    public void addPengadaanFasilitas(PengadaanFasilitasModel pengadaanFasilitas) { pengadaanFasilitasDb.save(pengadaanFasilitas);}

    @Override
    public List<PengadaanFasilitasModel> getListPengadaanFasilitas() { return pengadaanFasilitasDb.findAll();}

    @Override
    public PengadaanFasilitasModel generateStatusPengadaan(PengadaanFasilitasModel pengadaanFasilitas){
        pengadaanFasilitas.setStatus(1);
        return pengadaanFasilitas;
    }
}
