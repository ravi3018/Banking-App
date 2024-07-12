package com.example.Banking_App.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime time,
                           String message,
                           String info,
                           String errorCode) {
}
