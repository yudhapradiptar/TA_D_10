package apap.tugasakhir.siruangan.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import apap.tugasakhir.siruangan.repository.PeminjamanRuanganDB;

@Service
public class PeminjamanRuanganServiceImpl implements PeminjamanRuanganService {
    
    @Autowired
    PeminjamanRuanganDB peminjamanRuanganDB;

    @Override
    public PeminjamanRuanganModel mengajukanPeminjamanRuangan(PeminjamanRuanganModel peminjaman) {
        return peminjamanRuanganDB.save(peminjaman);
    }

    @Override
    public boolean dateTimeValidation(PeminjamanRuanganModel peminjaman) {
        Date waktuTanggalSekarang = new Date();
        Date combinedDateTimeMulai = combineStartAndEndDateTime(peminjaman.getTanggalMulai(), peminjaman.getWaktuMulai());
        Date combinedDateTimeSelesai = combineStartAndEndDateTime(peminjaman.getTanggalSelesai(), peminjaman.getWaktuSelesai());

        if(combinedDateTimeMulai.after(waktuTanggalSekarang) 
           && combinedDateTimeMulai.before(combinedDateTimeSelesai)) { 
            List<PeminjamanRuanganModel> listPeminjamanModel = peminjamanRuanganDB.findByRuanganIdRuangan(peminjaman.getRuangan().getIdRuangan());
            for(PeminjamanRuanganModel peminjamanObj : listPeminjamanModel) {
                Date combinedDateTimeObjMulai = combineStartAndEndDateTime(peminjamanObj.getTanggalMulai(), peminjamanObj.getWaktuMulai());
                Date combinedDateTimeObjSelesai = combineStartAndEndDateTime(peminjamanObj.getTanggalSelesai(), peminjamanObj.getWaktuSelesai());
                if(combinedDateTimeObjMulai.before(combinedDateTimeSelesai) && combinedDateTimeObjSelesai.after(combinedDateTimeMulai)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }


    }

    @Override
    public boolean capacityValidation(PeminjamanRuanganModel peminjaman) {
        int jumlahPesertaDiajukan = peminjaman.getJumlahPeserta();
        int kapasitasMaksimal = peminjaman.getRuangan().getKapasitasRuangan();

        if(jumlahPesertaDiajukan <= kapasitasMaksimal) { return true; }
        else { return false; }
    }

    public Date combineStartAndEndDateTime(Date tanggal, String waktu) {
        String[] waktuSplit = waktu.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tanggal);
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(waktuSplit[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(waktuSplit[1]));
        Date waktuTanggal = calendar.getTime();
        return waktuTanggal;
        
    }

    


}