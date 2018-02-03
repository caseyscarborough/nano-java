package org.nano.client;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;

public class NanoClient {

    private static final String DEFAULT_HOST = "http://localhost:7076";

    private final OkHttpClient client = new OkHttpClient();
    private final String host;
    private final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    public NanoClient() {
        this(DEFAULT_HOST);
    }

    public NanoClient(String host) {
        this.host = host;
    }

    /**
     * Returns how many RAW is owned and how many have not yet been received for an account.
     *
     * @param account the address of the account
     */
    public AccountBalance getAccountBalance(String account) {
        Request request = Request.action("account_balance")
                .param("account", account)
                .build();

        return response(request, AccountBalance.class);
    }

    /**
     * Get number of blocks for a specific account.
     *
     * @param account the address of the account
     */
    public AccountBlockCount getAccountBlockCount(String account) {
        Request request = Request.action("account_block_count")
                .param("account", account)
                .build();

        return response(request, AccountBlockCount.class);
    }

    /**
     * Returns frontier, open block, change representative block, balance, last
     * modified timestamp from local database & block count for account.
     *
     * @param account the address of the account
     * @see NanoClient#getAccountInformation(String, boolean, boolean, boolean)
     */
    public AccountInformation getAccountInformation(String account) {
        return getAccountInformation(account, false, false, false);
    }

    /**
     * Returns frontier, open block, change representative block, balance, last
     * modified timestamp from local database & block count for account.
     * <p>
     * This overloaded version can additionally returns representative, voting
     * weight, and pending balance for account.
     *
     * @param account        the address of the account
     * @param representative whether or not to return the representative for the account
     * @param weight         whether or not to return the voting weight of the account
     * @param pending        whether or not to return the pending balance of the account
     * @see NanoClient#getAccountInformation(String)
     */
    public AccountInformation getAccountInformation(String account,
                                                    boolean representative,
                                                    boolean weight,
                                                    boolean pending) {
        Request request = Request.action("account_info")
                .param("account", account)
                .param("representative", representative)
                .param("weight", weight)
                .param("pending", pending)
                .build();
        return response(request, AccountInformation.class);
    }

    /**
     * Creates a new account and inserts the next deterministic key in wallet.
     * <p>
     * Requires enable_account.
     *
     * @param wallet the specified wallet.
     * @return the address of the new account.
     */
    public AccountCreate createAccount(String wallet) {
        return createAccount(wallet, true);
    }

    /**
     * Creates a new account and inserts the next deterministic key in wallet.
     * <p>
     * This overloaded version takes can disable the generating the work after
     * the account is created.
     * <p>
     * Requires enable_account.
     *
     * @param wallet the specified wallet.
     * @param work   whether or not generate work.
     * @return the address of the new account.
     */
    public AccountCreate createAccount(String wallet, boolean work) {
        Request request = Request.action("account_create")
                .param("wallet", wallet)
                .param("work", work)
                .build();

        return response(request, AccountCreate.class);
    }

    /**
     * Get account number for the public key.
     *
     * @param publicKey the public key for an account.
     * @return the address of the associated account.
     */
    public AccountGet getAccount(String publicKey) {
        Request request = Request.action("account_get")
                .param("key", publicKey)
                .build();

        return response(request, AccountGet.class);
    }

    /**
     * Reports send/receive information for an account.
     *
     * @param account the account address.
     * @param count   the number of transactions to return.
     * @return the history for the account.
     */
    public AccountHistory getAccountHistory(String account, Integer count) {
        Request request = Request.action("account_history")
                .param("account", account)
                .param("count", count)
                .build();

        return response(request, AccountHistory.class);
    }

    /**
     * Lists all the accounts inside the wallet.
     *
     * @param wallet the wallet identifier.
     * @return the list of accounts.
     */
    public AccountList getAccountList(String wallet) {
        Request request = Request.action("account_list")
                .param("wallet", wallet)
                .build();

        return response(request, AccountList.class);
    }

    /**
     * Moves accounts from source to wallet.
     * <p>
     * Requires enable_control.
     *
     * @param wallet   the wallet to move to.
     * @param source   the source.
     * @param accounts the accounts to move.
     * @return the number of accounts moved.
     */
    public AccountMove moveAccounts(String wallet, String source, String... accounts) {
        Request request = Request.action("account_move")
                .param("wallet", wallet)
                .param("source", source)
                .param("accounts", Arrays.asList(accounts))
                .build();

        return response(request, AccountMove.class);
    }

    /**
     * Get the public key for an account.
     *
     * @param account the account to retrieve the public key for.
     * @return the account's public key.
     */
    public AccountPublicKey getAccountPublicKey(String account) {
        Request request = Request.action("account_key")
                .param("account", account)
                .build();

        return response(request, AccountPublicKey.class);
    }

    /**
     * Remove account from wallet.
     * <p>
     * Requires enable_control.
     *
     * @param wallet  the wallet to remove the account from.
     * @param account the account to remove.
     * @return the number of accounts removed (1 for successful, 0 for unsuccessful).
     */
    public AccountRemove removeAccount(String wallet, String account) {
        Request request = Request.action("account_remove")
                .param("account", account)
                .param("wallet", wallet)
                .build();

        return response(request, AccountRemove.class);
    }

    /**
     * Returns the representative for an account.
     *
     * @param account the account to get the representative for.
     * @return the account's representative.
     */
    public AccountRepresentative getAccountRepresentative(String account) {
        Request request = Request.action("account_representative")
                .param("account", account)
                .build();

        return response(request, AccountRepresentative.class);
    }

    /**
     * Sets the representative for an account within a wallet.
     * <p>
     * Requires enable_control.
     *
     * @param wallet         the wallet associated with the account.
     * @param account        the account to set the representative in.
     * @param representative the representative to set.
     * @return the block associated with setting the account representative.
     */
    public AccountRepresentativeSet setAccountRepresentative(String wallet, String account, String representative) {
        Request request = Request.action("account_representative_set")
                .param("wallet", wallet)
                .param("account", account)
                .param("representative", representative)
                .build();

        return response(request, AccountRepresentativeSet.class);
    }

    /**
     * Send funds from one account to another.
     * <p>
     * Requires enable_control.
     *
     * @param wallet      the wallet which contains the source account.
     * @param source      the account to send from.
     * @param destination the receiving account.
     * @param amount      the amount to send. // TODO: document the unit here
     * @return the block associated with the send transaction.
     */
    public Send send(String wallet, String source, String destination, String amount) {
        Request request = Request.action("send")
                .param("wallet", wallet)
                .param("source", source)
                .param("destination", destination)
                .param("amount", amount)
                .build();

        return response(request, Send.class);
    }

    private <T extends BaseResponse> T response(Request r, Class<T> clazz) {
        try {
            String json = gson.toJson(r.getMap());
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .post(RequestBody.create(MediaType.parse("application/json"), json))
                    .url(host)
                    .build();

            Response response = client.newCall(request).execute();
            String body = response.body().string();
            T t = gson.fromJson(body, clazz);
            if (!t.isSuccess()) {
                throw new NanoException(t.getError());
            }
            return t;
        } catch (IOException e) {
            throw new NanoException("Unable to get account balance", e);
        }
    }
}
