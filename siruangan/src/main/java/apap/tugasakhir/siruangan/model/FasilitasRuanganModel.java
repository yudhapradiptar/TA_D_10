package apap.tugasakhir.siruangan.model;

import javax.persistence.*;

@Entity
@Table(name="fasilitas_ruangan")
public class FasilitasRuanganModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFasilitasRuangan;

    @ManyToOne
    @JoinColumn(name="idRuangan")
    RuanganModel ruangan;

    @ManyToOne
    @JoinColumn(name="idFasilitas")
    FasilitasModel fasilitas;

    public Long getIdFasilitasRuangan() {
        return idFasilitasRuangan;
    }

    public void setIdFasilitasRuangan(Long idFasilitasRuangan) {
        this.idFasilitasRuangan = idFasilitasRuangan;
    }

    public RuanganModel getRuangan() {
        return ruangan;
    }

    public void setRuangan(RuanganModel ruangan) {
        this.ruangan = ruangan;
    }

    public FasilitasModel getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(FasilitasModel fasilitas) {
        this.fasilitas = fasilitas;
    }
}
