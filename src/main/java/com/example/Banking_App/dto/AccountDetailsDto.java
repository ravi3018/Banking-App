package com.example.Banking_App.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDetailsDto {

    private long id;
    private String name;
    private double balance;
}
