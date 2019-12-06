package apap.tugasakhir.siruangan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class JenisSuratDetail {
    @JsonProperty("id")
    private String idJenisSurat;
    
    @JsonProperty("nama")
    private String nama;
    

    @JsonProperty("keterangan")
    private String keterangan;

    public String getIdJenisSurat() {
		return this.idJenisSurat;
	}

	public void setIdJenisSurat(String idJenisSurat) {
		this.idJenisSurat = idJenisSurat;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
    
}