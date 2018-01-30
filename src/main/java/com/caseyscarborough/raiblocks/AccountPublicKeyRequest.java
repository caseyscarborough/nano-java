package com.caseyscarborough.raiblocks;

class AccountPublicKeyRequest extends AccountRequest {
    public AccountPublicKeyRequest(String account) {
        this.action = "account_key";
        this.account = account;
    }
}
