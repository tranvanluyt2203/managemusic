package org.example.app;

public class ResponseApi {
    private String status;
    private String message;
    private int code;
    private Object data;

    public ResponseApi(String status, String message, int code, Object data) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ResponseApi(String status, String message, int code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }
}
