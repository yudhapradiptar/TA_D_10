package apap.tugasakhir.siruangan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class GuruDetail {
    @JsonProperty("idUser")
    private String uuid;

    @JsonProperty("nig")
    private String nig;

    @JsonProperty("nama")
    private String nama;

    @JsonProperty("tempatLahir")
    private String tempatLahir;

    @JsonProperty("tanggalLahir")
    private String tanggalLahir;

    @JsonProperty("alamat")
    private String alamat;

    @JsonProperty("telepon")
    private String telepon;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNig() {
        return nig;
    }

    public void setNig(String nig) {
        this.nig = nig;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempatLahir() {
        return this.tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}