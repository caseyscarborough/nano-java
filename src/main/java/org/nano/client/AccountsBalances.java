package org.nano.client;

import java.util.Map;

class AccountsBalances extends BaseResponse {

    private Map<String, AccountBalance> balances;

    public Map<String, AccountBalance> getBalances() {
        return balances;
    }
}
