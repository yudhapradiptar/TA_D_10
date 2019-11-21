package apap.tugasakhir.siruangan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class GuruDetailResponse {

    @JsonProperty("status")
    private String status;
    
    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private GuruDetail result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GuruDetail getResult() {
        return result;
    }

    public void setResult(GuruDetail result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}