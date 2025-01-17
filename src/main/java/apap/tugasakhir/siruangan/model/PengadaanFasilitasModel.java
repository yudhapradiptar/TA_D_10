package apap.tugasakhir.siruangan.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Entity
@Table(name="pengadaan_fasilitas")
public class PengadaanFasilitasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPengadaan;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "jumlah", nullable = false)
    private Integer jumlah;

    @NotNull
    @Column(name = "harga", nullable = false)
    private BigInteger harga;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne
    @NotNull
    @JoinColumn(name="idUser", nullable = false)
    UserModel user;

    public Long getIdPengadaan() {
        return idPengadaan;
    }

    public void setIdPengadaan(Long idPengadaan) {
        this.idPengadaan = idPengadaan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public BigInteger getHarga() {
        return harga;
    }

    public void setHarga(BigInteger harga) {
        this.harga = harga;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        if(status<=3){
            this.status=status;
        }
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
