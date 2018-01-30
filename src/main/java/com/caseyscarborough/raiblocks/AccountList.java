package com.caseyscarborough.raiblocks;

import com.caseyscarborough.raiblocks.BaseResponse;

import java.util.List;

public class AccountList extends BaseResponse {

    private List<String> accounts;

    public List<String> getAccounts() {
        return accounts;
    }
}
