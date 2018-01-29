package com.caseyscarborough.raiblocks.request;

public class AccountCreateRequest extends BaseRequest {

    private String wallet;
    private boolean work;

    public AccountCreateRequest(String wallet) {
        this(wallet, true);
    }

    public AccountCreateRequest(String wallet, boolean work) {
        this.action = "account_create";
        this.wallet = wallet;
        this.work = work;
    }

    public String getWallet() {
        return wallet;
    }

    public boolean getWork() {
        return work;
    }
}
