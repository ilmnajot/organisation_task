package uz.ilmnajot.organisation_task.payload.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse {

    private boolean success;
    private String message;
    private Object data;


    public ApiResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(){

    }

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }
}
