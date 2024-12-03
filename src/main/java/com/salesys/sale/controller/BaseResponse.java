package com.salesys.sale.controller;

import com.salesys.sale.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public abstract class BaseResponse {

    public ResponseEntity<?> buildResponse(Object data) {
        if (data instanceof List<?> && ((List<?>) data).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "No data found for the given fields/attributes."));
        }
        return ResponseEntity.ok(data);
    }
}
