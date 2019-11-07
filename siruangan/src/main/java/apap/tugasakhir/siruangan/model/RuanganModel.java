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
    List<RuanganFasilitasModel> listRuanganFasilitas;

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

    public List<RuanganFasilitasModel> getListRuanganFasilitas() {
        return listRuanganFasilitas;
    }

    public void setListRuanganFasilitas(List<RuanganFasilitasModel> listRuanganFasilitas) {
        this.listRuanganFasilitas = listRuanganFasilitas;
    }
}
