package apap.tugasakhir.siruangan.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="fasilitas")
public class FasilitasModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFasilitas;

    @NotNull
    @Size(max=200)
    @Column(name="namaFasilitas", nullable = false)
    private String namaFasilitas;

    @NotNull
    @Column(name = "jumlahFasilitas", nullable = false)
    private Integer jumlahFasilitas;

    /**
     * @param idFasilitas the idFasilitas to set
     */
    public void setIdFasilitas(Long idFasilitas) {
        this.idFasilitas = idFasilitas;
    }
    /**
     * @return the idFasilitas
     */
    public Long getIdFasilitas() {
        return idFasilitas;
    }
    /**
     * @param jumlahFasilitas the jumlahFasilitas to set
     */
    public void setJumlahFasilitas(Integer jumlahFasilitas) {
        this.jumlahFasilitas = jumlahFasilitas;
    }
    /**
     * @return the jumlahFasilitas
     */
    public Integer getJumlahFasilitas() {
        return jumlahFasilitas;
    }
    /**
     * @param namaFasilitas the namaFasilitas to set
     */
    public void setNamaFasilitas(String namaFasilitas) {
        this.namaFasilitas = namaFasilitas;
    }
    /**
     * @return the namaFasilitas
     */
    public String getNamaFasilitas() {
        return namaFasilitas;
    }
    /**
     * @param listFasilitasRuangan the listFasilitasRuangan to set
     */
    public void setListFasilitasRuangan(List<FasilitasRuanganModel> listFasilitasRuangan) {
        this.listFasilitasRuangan = listFasilitasRuangan;
    }
    /**
     * @return the listFasilitasRuangan
     */
    public List<FasilitasRuanganModel> getListFasilitasRuangan() {
        return listFasilitasRuangan;
    }

    @OneToMany(mappedBy = "fasilitas", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FasilitasRuanganModel> listFasilitasRuangan;
}