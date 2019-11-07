package apap.tugasakhir.siruangan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @Column(name="tanggal_mulai", nullable = false)
    private Date tanggalMulai;

    @NotNull
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
    private boolean isDisetujui;

    @ManyToOne
    @JoinColumn(name="idRuangan")
    RuanganModel ruangan;

    @ManyToOne
    @JoinColumn(name="idUser")
    UserModel user;

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

    public boolean isDisetujui() {
        return isDisetujui;
    }

    public void setDisetujui(boolean disetujui) {
        isDisetujui = disetujui;
    }

    public RuanganModel getRuangan() {
        return ruangan;
    }

    public void setRuangan(RuanganModel ruangan) {
        this.ruangan = ruangan;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
