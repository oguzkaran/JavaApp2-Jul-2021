package com.ondersahin.app.pdb.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * @author onder sahin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError implements Serializable {
    private int code;
    private String message;
    private String description;
    private List<SubError> subErrors;

    public ApiError(ApiErrorCode errorCode, String description) {
        this(errorCode, description, null);
    }

    public ApiError(ApiErrorCode errorCode, List<SubError> subErrors) {
        this(errorCode, null, subErrors);
    }

    public ApiError(ApiErrorCode errorCode, String description, List<SubError> subErrors) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.description = description;
        this.subErrors = subErrors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubError> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<SubError> subErrors) {
        this.subErrors = subErrors;
    }
}
