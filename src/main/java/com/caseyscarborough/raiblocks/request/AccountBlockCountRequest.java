package com.caseyscarborough.raiblocks.request;

public class AccountBlockCountRequest extends AccountRequest {
    public AccountBlockCountRequest(String account) {
        this.action = "account_block_count";
        this.account = account;
    }
}
