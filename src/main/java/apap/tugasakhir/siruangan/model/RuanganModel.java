package apap.tugasakhir.siruangan.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="ruangan")
public class RuanganModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRuangan;

    @NotNull
    @Size(max=200)
    @Column(name="namaRuangan", nullable = false)
    private String namaRuangan;

    @NotNull
    @Column(name="kapasitasRuangan", nullable = false)
    private int kapasitasRuangan;

    @OneToMany(mappedBy="ruangan")
    List<FasilitasRuanganModel> listFasilitasRuangan;

    @OneToMany(mappedBy = "ruangan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PeminjamanRuanganModel> listPeminjamanRuangan;


    public Long getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(Long idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public int getKapasitasRuangan() {
        return kapasitasRuangan;
    }

    public void setKapasitasRuangan(int kapasitasRuangan) {
        this.kapasitasRuangan = kapasitasRuangan;
    }

    public List<FasilitasRuanganModel> getListFasilitasRuangan() {
        return listFasilitasRuangan;
    }

    public void setListFasilitasRuangan(List<FasilitasRuanganModel> listFasilitasRuangan) {
        this.listFasilitasRuangan = listFasilitasRuangan;
    }

    public List<PeminjamanRuanganModel> getListPeminjamanRuangan() {
        return this.listPeminjamanRuangan;
    }

    public void setListPeminjamanRuangan(List<PeminjamanRuanganModel> listPeminjamanRuangan) {
        this.listPeminjamanRuangan = listPeminjamanRuangan;
    }
}
