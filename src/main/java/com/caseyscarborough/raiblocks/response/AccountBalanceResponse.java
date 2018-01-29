package com.caseyscarborough.raiblocks.response;

public class AccountBalanceResponse extends BaseResponse {

    private String balance;
    private String pending;

    public String getBalance() {
        return balance;
    }

    public String getPending() {
        return pending;
    }
}
