package org.nano.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class AccountBalances extends BaseResponse {

    private Map<String, AccountBalance> balances;

    public List<AccountBalance> getBalances() {
        List<AccountBalance> output = new ArrayList<>();
        for (String account : balances.keySet()) {
            AccountBalance balance = balances.get(account);
            balance.setAccount(account);
            output.add(balance);
        }
        return output;
    }
}
