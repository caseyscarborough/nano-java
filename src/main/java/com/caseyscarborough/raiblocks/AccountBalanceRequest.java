package com.caseyscarborough.raiblocks;

class AccountBalanceRequest extends AccountRequest {
    public AccountBalanceRequest(String account) {
        this.account = account;
        this.action = "account_balance";
    }
}
