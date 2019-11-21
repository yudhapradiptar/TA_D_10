package apap.tugasakhir.siruangan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PengadaanBukuDetailResponse {
    private String status;

    private String message;

    private PengadaanBukuDetail result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PengadaanBukuDetail getResult() {
        return result;
    }

    public void setResult(PengadaanBukuDetail result) {
        this.result = result;
    }
}
