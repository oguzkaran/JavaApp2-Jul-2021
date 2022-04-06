package com.ondersahin.app.pdb.api;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author onder sahin
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ApiErrorCode {

    DUPLICATE_FIELD(-1001, "DUPLICATE_FIELD"),
    USER_NOT_FOUND(-1002, "USER_NOT_FOUND");

    private int code;
    private String message;

    ApiErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }
}
