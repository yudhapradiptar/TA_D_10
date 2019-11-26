package apap.tugasakhir.siruangan.service;

import java.text.ParseException;
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
     * Asumsi: meminjam ruangan dari (tanggal mulai, tanggal selesai) dengan durasi per hari dari (waktu mulai, waktu selesai)
     */
    @Override
    public boolean dateTimeValidation(PeminjamanRuanganModel peminjaman) {
        Long idRuangan = peminjaman.getRuangan().getIdRuangan();
        Date tanggalWaktuSekarang = new Date();
        Date tanggalMulai = peminjaman.getTanggalMulai();
        Date tanggalSelesai = peminjaman.getTanggalSelesai();
        String waktuMulai = peminjaman.getWaktuMulai();
        String waktuSelesai = peminjaman.getWaktuSelesai();
        try {
            boolean isTanggalMulaiEqualsOrBeforeTanggalSelesai = tanggalMulai.equals(tanggalSelesai) || tanggalMulai.before(tanggalSelesai);
            boolean isWaktuMulaiBeforeWaktuSelesai = compareTimeBefore(waktuMulai, waktuSelesai);
            boolean isTanggalWaktuSekarangBeforeTanggalWaktuMulai = tanggalWaktuSekarang.before(combineDateAndTime(tanggalMulai, waktuMulai));
            if(isTanggalWaktuSekarangBeforeTanggalWaktuMulai
               && isTanggalMulaiEqualsOrBeforeTanggalSelesai
               && isWaktuMulaiBeforeWaktuSelesai) {
                List<PeminjamanRuanganModel> peminjamanRuanganListForSpecificRoom = peminjamanRuanganDB.findByRuanganIdRuangan(idRuangan);
                for(PeminjamanRuanganModel peminjamanObj : peminjamanRuanganListForSpecificRoom) {
                    if((tanggalMulai.before(peminjamanObj.getTanggalSelesai()) || tanggalMulai.equals(peminjamanObj.getTanggalSelesai()))
                       && (tanggalSelesai.after(peminjamanObj.getTanggalMulai()) || tanggalSelesai.equals(peminjamanObj.getTanggalMulai()))) {
                        if(compareTimeBefore(waktuMulai, peminjamanObj.getWaktuSelesai())
                          && compareTimeAfter(waktuSelesai, peminjamanObj.getWaktuMulai())) {
                              return false;
                          }
                    }
                }
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    
    }

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

    public boolean compareTimeBefore(String waktuMulai, String waktuSelesai) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date d1 = sdf.parse(waktuMulai);
        Date d2 = sdf.parse(waktuSelesai);
        long elapsed = d2.getTime() - d1.getTime();
        if(elapsed > 0) { return true; }
        else { return false; }
    }

    public boolean compareTimeAfter(String waktuMulai, String waktuSelesai) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date d1 = sdf.parse(waktuMulai);
        Date d2 = sdf.parse(waktuSelesai);
        long elapsed = d2.getTime() - d1.getTime();
        if(elapsed < 0) { return true; }
        else { return false; }
    }

    @Override
    public List<PeminjamanRuanganModel> getPeminjamanRuanganList() {
        
        return peminjamanRuanganDB.findAll();
    }

    @Override
    public PeminjamanRuanganModel changeStatus(PeminjamanRuanganModel peminjaman, int status) {
        PeminjamanRuanganModel targetPinjaman = peminjamanRuanganDB.findByIdPeminjamanRuangan(peminjaman.getIdPeminjamanRuangan());
        try{
            targetPinjaman.setIsDisetujui(status);
            peminjamanRuanganDB.save(targetPinjaman);
            return targetPinjaman;
        }
        catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public PeminjamanRuanganModel findRuanganByIdPeminjaman(Long idPeminjamanRuangan) {
        return peminjamanRuanganDB.findByIdPeminjamanRuangan(idPeminjamanRuangan);
    }
}