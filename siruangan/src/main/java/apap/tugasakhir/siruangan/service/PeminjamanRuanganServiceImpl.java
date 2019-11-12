package apap.tugasakhir.siruangan.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    /**
     * Asumsi: meminjam ruangan dari (tanggal mulai, waktu mulai) sampai (tanggal selesai, waktu selesai)
     * Kelebihan: mudah diimplementasikan
     * Kekurangan: tidak masuk akal seseorang meminjam ruangan dengan durasi selama itu
     */
    @Override
    public boolean dateTimeValidation(PeminjamanRuanganModel peminjaman) {
        Date waktuTanggalSekarang = new Date();
        Date combinedDateTimeMulai = combineDateAndTime(peminjaman.getTanggalMulai(), peminjaman.getWaktuMulai());
        Date combinedDateTimeSelesai = combineDateAndTime(peminjaman.getTanggalSelesai(), peminjaman.getWaktuSelesai());

        if(combinedDateTimeMulai.after(waktuTanggalSekarang) 
           && combinedDateTimeMulai.before(combinedDateTimeSelesai)) { 
            List<PeminjamanRuanganModel> listPeminjamanModel = peminjamanRuanganDB.findByRuanganIdRuangan(peminjaman.getRuangan().getIdRuangan());
            for(PeminjamanRuanganModel peminjamanObj : listPeminjamanModel) {
                Date combinedDateTimeObjMulai = combineDateAndTime(peminjamanObj.getTanggalMulai(), peminjamanObj.getWaktuMulai());
                Date combinedDateTimeObjSelesai = combineDateAndTime(peminjamanObj.getTanggalSelesai(), peminjamanObj.getWaktuSelesai());
                if(combinedDateTimeObjMulai.before(combinedDateTimeSelesai) && combinedDateTimeObjSelesai.after(combinedDateTimeMulai)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Asumsi: meminjam ruangan dari (tanggal mulai, tanggal selesai) dengan durasi per hari dari (waktu mulai, waktu selesai)
     * Kelebihan: masuk akal
     * Kekurangan: sulit diimplementasikan
     */
    // @Override
    // public boolean dateTimeValidation(PeminjamanRuanganModel peminjaman) {
    //     Date tanggalWaktuSekarang = new Date();

        
    //     Date tanggalMulaiWaktuMulai = combineDateAndTime(peminjaman.getTanggalMulai(), peminjaman.getWaktuMulai());
    //     Date tanggalMulaiWaktuSelesai = combineDateAndTime(peminjaman.getTanggalMulai(), peminjaman.getWaktuSelesai());
    //     Date tanggalSelesaiWaktuMulai = combineDateAndTime(peminjaman.getTanggalSelesai(), peminjaman.getWaktuMulai());
    //     Date tanggalSelesaiWaktuSelesai = combineDateAndTime(peminjaman.getTanggalSelesai(), peminjaman.getWaktuSelesai());

    //     //Cek input tanggal valid atau tidak
    //     if(tanggalWaktuSekarang.before(tanggalMulaiWaktuMulai)
    //        && tanggalWaktuSekarang.before(tanggalSelesaiWaktuMulai)
    //        && tanggalMulaiWaktuMulai.before(tanggalMulaiWaktuSelesai)
    //        && tanggalSelesaiWaktuMulai.before(tanggalSelesaiWaktuSelesai) {
    //             for()
    //            return true;
    //     } else {
    //         return false;
    //     }
        
    // }

    @Override
    public boolean capacityValidation(PeminjamanRuanganModel peminjaman) {
        int jumlahPesertaDiajukan = peminjaman.getJumlahPeserta();
        int kapasitasMaksimal = peminjaman.getRuangan().getKapasitasRuangan();

        if(jumlahPesertaDiajukan <= kapasitasMaksimal) { return true; }
        else { return false; }
    }

    public Date combineDateAndTime(Date tanggal, String waktu) {
        String[] waktuSplit = waktu.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tanggal);
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(waktuSplit[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(waktuSplit[1]));
        Date waktuTanggal = calendar.getTime();
        return waktuTanggal;
        
    }

    public long differenceBetweenTwoDates(Date startDate, Date endDate) {
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }

    @Override
    public List<PeminjamanRuanganModel> getPeminjamanRuanganList() {
        
        return peminjamanRuanganDB.findAll();
    }

    


}