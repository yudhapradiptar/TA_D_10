package apap.tugasakhir.siruangan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)

public class SuratDetailResponse {

    @JsonProperty("status")
    private int status;
    
    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private SuratDetail result;

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

    public SuratDetail getResult() {
        return this.result;
    }

    public void setResult(SuratDetail result) {
        this.result = result;
    }

}