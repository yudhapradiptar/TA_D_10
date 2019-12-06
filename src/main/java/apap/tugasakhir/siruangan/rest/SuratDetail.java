package apap.tugasakhir.siruangan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class SuratDetail {
    
    @JsonProperty("jenisSurat")
    private String jenisSurat;
    
    @JsonProperty("keterangan")
	private String keterangan;
	
	@JsonProperty("status")
	private String status;

    @JsonProperty("uuidUser")
    private String idUser;
    
	public String getJenisSurat() {
		return this.jenisSurat;
	}

	public void setJenisSurat(String jenisSurat) {
		this.jenisSurat = jenisSurat;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
    }
}