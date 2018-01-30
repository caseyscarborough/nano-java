package com.caseyscarborough.raiblocks;

class AccountInformationRequest extends AccountRequest {

    private boolean representative;
    private boolean weight;
    private boolean pending;

    public AccountInformationRequest(String account) {
        this(account, false, false, false);
    }

    public AccountInformationRequest(String account, boolean representative, boolean weight, boolean pending) {
        this.action = "account_info";
        this.account = account;
        this.representative = representative;
        this.weight = weight;
        this.pending = pending;
    }

    public boolean isRepresentative() {
        return representative;
    }

    public boolean isWeight() {
        return weight;
    }

    public boolean isPending() {
        return pending;
    }
}
