package com.merin.userService.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InValidAddressException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InValidAddressException(String message) {
        super(message);
    }

    public InValidAddressException() {
    }

}
