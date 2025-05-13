package DTO;

import java.util.Map;

public class ResponseDTO {
    private boolean status; // Indica si la operación fue exitosa
    private String msn; // Mensaje de respuesta
    private Map<String, Object> data; // Datos adicionales de la respuesta

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getMsn() {
        return msn;
    }
    public void setMsn(String msn) {
        this.msn = msn;
    }
    public Map<String, Object> getData() {
        return data;
    }
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    

}
