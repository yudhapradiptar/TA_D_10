package apap.tugasakhir.siruangan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PeminjamanRuanganResponse {

    @JsonProperty("status")
    private int status;
    
    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private PeminjamanRuanganModel result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PeminjamanRuanganModel getResult() {
        return this.result;
    }

    public void setResult(PeminjamanRuanganModel result) {
        this.result = result;
    }

}

