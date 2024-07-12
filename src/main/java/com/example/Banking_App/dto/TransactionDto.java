package com.example.Banking_App.dto;

import java.time.LocalDateTime;

public record TransactionDto(Long transactionId,
                             Long accountId,
                             double amount,
                             String transactionType,
                             LocalDateTime timeStamp
                             ) {
}

