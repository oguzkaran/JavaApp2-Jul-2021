package org.csystem.app.pdb.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author onder sahin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {

    private T data;
    private ApiError error;
    private LocalDateTime timestamp;

    public ApiResponse() {
        this(null, null);
    }

    public ApiResponse(T data) {
        this(data, null);
    }

    public ApiResponse(ApiError error) {
        this(null, error);
    }

    public ApiResponse(T data, ApiError error) {
        this.data = data;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


}
