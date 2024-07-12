package com.example.Banking_App.mapper;

import com.example.Banking_App.dto.AccountDetailsDto;
import com.example.Banking_App.entity.AccountDetails;

public class AccountMapper {

    public  static AccountDetails mapToAccountDetails(AccountDetailsDto accountDetailsDto){

        return new AccountDetails(
                accountDetailsDto.getId(),
                accountDetailsDto.getName(),
                accountDetailsDto.getBalance()
        );
    }
    public static AccountDetailsDto mapToAccountDetailsDto(AccountDetails accountDetails){

        return new AccountDetailsDto(
                accountDetails.getId(),
                accountDetails.getName(),
                accountDetails.getBalance()
        );

    }

}
