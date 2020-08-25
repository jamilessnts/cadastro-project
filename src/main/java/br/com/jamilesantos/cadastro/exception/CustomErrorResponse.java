package br.com.jamilesantos.cadastro.exception;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.Date;
import java.util.List;

/**
 * Classe modelo de erros
 * @author Jamile Santos
 *
 */

@JsonRootName(value = "error")
public class CustomErrorResponse {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String details;

    public CustomErrorResponse(int statusCode, Date timestamp, String message, String details) {
        super();
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
