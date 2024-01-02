package com.hackerton.demo.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@JsonPropertyOrder({"isSuccess", "status", "message", "data"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private boolean isSucceess;
    private int status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ApiResponse(ApiResponseStatus status){
        this.isSucceess = status.isSuccess();
        this.status = status.getStatus();
        this.message = status.getMessage();
    }

    public ApiResponse(ApiResponseStatus status, T data){
        this.isSucceess = status.isSuccess();
        this.status = status.getStatus();
        this.message = status.getMessage();
        this.data = data;
    }

    @JsonProperty("status")
    public int getStatus(){
        return status;
    }

    @JsonProperty("message")
    public String getMessage(){
        return message;
    }

    @JsonProperty("data")
    public T getData(){
        return data;
    }
}
