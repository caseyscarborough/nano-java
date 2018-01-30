package com.caseyscarborough.raiblocks;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class RaiBlocksClient {

    private final OkHttpClient client = new OkHttpClient();
    private final String host;
    private final Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

    public RaiBlocksClient() {
        this("http://localhost:7076");
    }

    public RaiBlocksClient(String host) {
        this.host = host;
    }

    public static void main(String[] args) {
        RaiBlocksClient client = new RaiBlocksClient();
        String account = "xrb_3te9uqhgshefm1s41ooi9gebkrdkdxwqygizn11a7pxohephmb79y9fmaaa1";
        String wallet = "02028E84A43D7840A4DE32DF7EE0189819062269FE76A43A4FC560ACA35264AD";
        String publicKey = "E987DDDEECBD8D98322056B03B989961725F797F3A1FA00082DBB57B2CF9A4A7";
//        client.getAccountBalance(account);
//        client.getAccountBlockCount(account);
//        client.getAccountInformation(account);
//        client.getAccountInformation(account, true, true, true);
//        AccountCreateResponse acr = client.createAccount(wallet);
//        System.out.println(acr.getAccount());
//        AccountPublicKeyResponse response = client.getAccountPublicKey(account);
//        System.out.println(response.getKey());
    }

    /**
     * Returns how many RAW is owned and how many have not yet been received for an account.
     *
     * @param account the address of the account
     */
    public AccountBalance getAccountBalance(String account) {
        AccountBalanceRequest request = new AccountBalanceRequest(account);
        return response(request, AccountBalance.class);
    }

    /**
     * Get number of blocks for a specific account.
     *
     * @param account the address of the account
     */
    public AccountBlockCount getAccountBlockCount(String account) {
        AccountBlockCountRequest request = new AccountBlockCountRequest(account);
        return response(request, AccountBlockCount.class);
    }

    /**
     * Returns frontier, open block, change representative block, balance, last
     * modified timestamp from local database& block count for account.
     *
     * @param account the address of the account
     * @see RaiBlocksClient#getAccountInformation(String, boolean, boolean, boolean)
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
     * @see RaiBlocksClient#getAccountInformation(String)
     */
    public AccountInformation getAccountInformation(String account,
                                                    boolean representative,
                                                    boolean weight,
                                                    boolean pending) {
        AccountInformationRequest request = new AccountInformationRequest(account, representative, weight, pending);
        return response(request, AccountInformation.class);
    }

    /**
     * Creates a new account and inserts the next deterministic key in wallet.
     *
     * @param wallet the specified wallet.
     * @return the address of the new account.
     */
    public AccountCreate createAccount(String wallet) {
        return createAccount(wallet, true);
    }

    /**
     * Creates a new account and inserts the next deterministic key in wallet.
     *
     * This overloaded version takes can disable the generating the work after
     * the account is created.
     *
     * @param wallet the specified wallet.
     * @param work whether or not generate work.
     * @return the address of the new account.
     */
    public AccountCreate createAccount(String wallet, boolean work) {
        AccountCreateRequest request = new AccountCreateRequest(wallet, work);
        return response(request, AccountCreate.class);
    }

    /**
     * Get the public key for an account.
     *
     * @param account the account to retrieve the public key for.
     * @return the account's public key.
     */
    public AccountPublicKey getAccountPublicKey(String account) {
        AccountPublicKeyRequest request = new AccountPublicKeyRequest(account);
        return response(request, AccountPublicKey.class);
    }

    private <T extends BaseResponse> T response(Object o, Class<T> clazz) {
        try {
            String json = gson.toJson(o);
            Request request = new Request.Builder()
                .post(RequestBody.create(MediaType.parse("application/json"), json))
                .url(host)
                .build();

            Response response = client.newCall(request).execute();
            String body = response.body().string();
            T t = gson.fromJson(body, clazz);
            if (!t.isSuccess()) {
                throw new RaiBlocksException(t.getError());
            }
            return t;
        } catch (IOException e) {
            throw new RaiBlocksException("Unable to get account balance", e);
        }
    }
}
