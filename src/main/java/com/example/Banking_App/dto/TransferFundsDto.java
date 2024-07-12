package com.example.Banking_App.dto;

public record TransferFundsDto(Long sender,
                               Long receiver,
                               double funds) {
}
