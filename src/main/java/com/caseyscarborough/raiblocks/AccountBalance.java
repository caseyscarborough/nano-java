package com.caseyscarborough.raiblocks;

import com.caseyscarborough.raiblocks.BaseResponse;

public class AccountBalance extends BaseResponse {

    private String balance;
    private String pending;

    public String getBalance() {
        return balance;
    }

    public String getPending() {
        return pending;
    }
}
