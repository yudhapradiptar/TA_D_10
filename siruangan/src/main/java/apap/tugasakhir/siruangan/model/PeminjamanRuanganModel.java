package apap.tugasakhir.siruangan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="peminjaman_ruangan")
public class PeminjamanRuanganModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeminjamanRuangan;

    @NotNull
    @Size(max=200)
    @Column(name="waktu_mulai", nullable = false)
    private String waktuMulai;

    @NotNull
    @Size(max=200)
    @Column(name="waktu_selesai", nullable = false)
    private String waktuSelesai;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="tanggal_mulai", nullable = false)
    private Date tanggalMulai;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="tanggal_selesai", nullable = false)
    private Date tanggalSelesai;

    @NotNull
    @Size(max=200)
    @Column(name="tujuan", nullable = false)
    private String tujuan;

    @NotNull
    @Size(max=200)
    @Column(name="keterangan", nullable = false)
    private String keterangan;

    @NotNull
    @Column(name="jumlah_peserta", nullable = false)
    private int jumlahPeserta;

    @NotNull
    @Column(name="is_disetujui", nullable = false)
    private boolean isDisetujui = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idRuangan", referencedColumnName = "idRuangan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    RuanganModel ruangan;

    @ManyToOne
    @JoinColumn(name= "idUserPeminjam", referencedColumnName = "idUser")
    UserModel userPeminjam;
    

    @ManyToOne
    @JoinColumn(name = "idUserPenyetuju", referencedColumnName = "idUser")
    UserModel userPenyetuju = null;
    

    public Long getIdPeminjamanRuangan() {
        return idPeminjamanRuangan;
    }

    public void setIdPeminjamanRuangan(Long idPeminjamanRuangan) {
        this.idPeminjamanRuangan = idPeminjamanRuangan;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(String waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public String getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(String waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public Date getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getJumlahPeserta() {
        return jumlahPeserta;
    }

    public void setJumlahPeserta(int jumlahPeserta) {
        this.jumlahPeserta = jumlahPeserta;
    }

    public boolean getIsDisetujui() {
        return isDisetujui;
    }

    public void setIsDisetujui(boolean disetujui) {
        isDisetujui = disetujui;
    }

    public RuanganModel getRuangan() {
        return ruangan;
    }

    public void setRuangan(RuanganModel ruangan) {
        this.ruangan = ruangan;
    }

    public UserModel getUserPeminjam() {
        return this.userPeminjam;
    }

    public void setUserPeminjam(UserModel userPeminjam) {
        this.userPeminjam = userPeminjam;
    }

    public UserModel getUserPenyetuju() {
        return this.userPenyetuju;
    }

    public void setUserPenyetuju(UserModel userPenyetuju) {
        this.userPenyetuju = userPenyetuju;
    }

    
}
