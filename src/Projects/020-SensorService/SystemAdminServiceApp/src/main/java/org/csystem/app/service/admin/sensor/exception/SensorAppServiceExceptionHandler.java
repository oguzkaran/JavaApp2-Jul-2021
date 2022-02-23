package org.csystem.app.service.admin.sensor.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SensorAppServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        var apiErr = new SensorAdminAppApiError(HttpStatus.BAD_REQUEST, "Invalid JSON format", ex.getValue(), ex.getMessage());

        return new ResponseEntity<>(apiErr, apiErr.getStatus());
    }
}
