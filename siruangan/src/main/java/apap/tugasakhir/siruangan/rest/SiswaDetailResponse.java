package apap.tugasakhir.siruangan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SiswaDetailResponse {

    @JsonProperty("status")
    private String status;
    
    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private SiswaDetail result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SiswaDetail getResult() {
        return result;
    }

    public void setResult(SiswaDetail result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}