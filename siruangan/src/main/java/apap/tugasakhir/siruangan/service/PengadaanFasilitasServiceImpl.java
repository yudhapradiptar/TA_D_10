package apap.tugasakhir.siruangan.service;

import apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;
import apap.tugasakhir.siruangan.repository.PengadaanFasilitasDB;
import apap.tugasakhir.siruangan.model.UserModel;
import apap.tugasakhir.siruangan.model.RoleModel;
import apap.tugasakhir.siruangan.service.*;
import apap.tugasakhir.siruangan.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PengadaanFasilitasServiceImpl implements PengadaanFasilitasService{
    @Autowired
    private PengadaanFasilitasDB pengadaanFasilitasDb;

    @Autowired
    private UserDB userDb;

    @Override
    public void addPengadaanFasilitas(PengadaanFasilitasModel pengadaanFasilitas) { pengadaanFasilitasDb.save(pengadaanFasilitas);}

    @Override
    public List<PengadaanFasilitasModel> getListPengadaanFasilitas() { return pengadaanFasilitasDb.findAll();}

    @Override
    public PengadaanFasilitasModel generateStatusPengadaanAndIdUser(PengadaanFasilitasModel pengadaanFasilitas){
        if(SecurityContextHolder.getContext().getAuthentication().getName().equals("admin")){
            pengadaanFasilitas.setUser(userDb.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            pengadaanFasilitas.setStatus(1);
        }
        else if(SecurityContextHolder.getContext().getAuthentication().getName().equals("guru")){
            pengadaanFasilitas.setUser(userDb.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            pengadaanFasilitas.setStatus(2);
        }
        return pengadaanFasilitas;
    }
}
