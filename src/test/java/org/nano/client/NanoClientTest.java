package org.nano.client;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NanoClientTest {

    private static final String ACCOUNT = "xrb_3t6k35gi95xu6tergt6p69ck76ogmitsa8mnijtpxm9fkcm736xtoncuohr3";
    private static final String PUBLIC_KEY = "E89208DD038FBB269987689621D52292AE9C35941A7484756ECCED92A65093BA";
    private static final String WALLET = "01028E84A43D7840A4DE32DF7EE0189819062269FE76A43A4FC560ACA35264AC";
    private static final String BAD_ACCOUNT_NUMBER_ERROR = "Bad account number";
    private static final String WALLET_NOT_FOUND_ERROR = "Wallet not found";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private HttpClient httpClient;
    private NanoClient client;

    @Before
    public void setUp() throws Exception {
        httpClient = mock(HttpClient.class);
        client = new NanoClient(httpClient);
//        client = new NanoClient();
    }

    @Test
    public void testGetAccountBalance() throws Exception {
        expectJson("account_balance");

        AccountBalance accountBalance = client.getAccountBalance(ACCOUNT);
        assertEquals("325586539664609129644855132177", accountBalance.getBalance());
        assertEquals(ACCOUNT, accountBalance.getAccount());
        assertEquals("2309370940000000000000000000000000", accountBalance.getPending());
    }

    @Test
    public void testGetAccountBalanceWhenAccountNotFound() throws Exception {
        expectJson("account_balance_not_found");

        expectedException.expect(NanoException.class);
        expectedException.expectMessage(BAD_ACCOUNT_NUMBER_ERROR);
        client.getAccountBalance(ACCOUNT);
    }

    @Test
    public void testGetAccountBlockCount() throws Exception {
        expectJson("account_block_count");

        assertEquals("42", client.getAccountBlockCount(ACCOUNT).getBlockCount());
    }

    @Test
    public void testGetAccountBlockCountWhenAddressNotFound() throws Exception {
        expectJson("account_block_count_not_found");

        expectedException.expect(NanoException.class);
        expectedException.expectMessage(BAD_ACCOUNT_NUMBER_ERROR);
        client.getAccountBlockCount(ACCOUNT);
    }

    @Test
    public void testGetAccountInformation() throws Exception {
        expectJson("account_info");

        AccountInformation info = client.getAccountInformation(ACCOUNT);
        assertEquals("ECCB8CB65CD3106EDA8CE9AA893FEAD497A91BCA903890CBD7A5C59F06AB9113", info.getFrontier());
        assertEquals("991CF190094C00F0B68E2E5F75F6BEE95A2E0BD93CEAA4A6734DB9F19B728948", info.getOpenBlock());
        assertEquals("991CF190094C00F0B68E2E5F75F6BEE95A2E0BD93CEAA4A6734DB9F19B728948", info.getRepresentativeBlock());
        assertEquals("325586539664609129644855132177", info.getBalance());
        assertEquals("42", info.getBlockCount());
    }

    @Test
    public void testGetAccountInformationWhenAddressNotFound() throws Exception {
        expectJson("account_info_not_found");

        expectedException.expect(NanoException.class);
        expectedException.expectMessage(BAD_ACCOUNT_NUMBER_ERROR);
        client.getAccountInformation(ACCOUNT);
    }

    @Test
    public void testCreateAccount() throws Exception {
        expectJson("account_create");

        AccountCreate account = client.createAccount(WALLET);
        assertEquals("xrb_1rophzebpsxhitm6cphr8qtph4gzjez899ibetqn1bftqydpp9yzau7fkhtm", account.getAccount());
    }

    @Test
    public void testCreateAccountWhenWalletNotFound() throws Exception {
        expectJson("account_create_wallet_not_found");

        expectedException.expect(NanoException.class);
        expectedException.expectMessage(WALLET_NOT_FOUND_ERROR);
        client.createAccount(WALLET);
    }

    @Test
    public void testGetAccountPublicKey() throws Exception {
        expectJson("account_key");

        AccountPublicKey pk = client.getAccountPublicKey(ACCOUNT);
        assertEquals("E89208DD038FBB269987689621D52292AE9C35941A7484756ECCED92A65093BA", pk.getKey());
    }

    @Test
    public void testGetAccount() throws Exception {
        expectJson("account_get");

        AccountGet account = client.getAccount(PUBLIC_KEY);
        assertEquals(ACCOUNT, account.getAccount());
    }

    @Test
    public void testGetAccountHistory() throws Exception {
        expectJson("account_history");

        AccountHistory history = client.getAccountHistory(ACCOUNT, 10);
        assertEquals(10, history.getHistory().size());

        History h = history.getHistory().get(0);
        assertEquals("xrb_1111111111111111111111111111111111111111111111111111hifc8npp", h.getAccount());
        assertEquals("205676479000000000000000000000000000000", h.getAmount());
        assertEquals("ECCB8CB65CD3106EDA8CE9AA893FEAD497A91BCA903890CBD7A5C59F06AB9113", h.getHash());
        assertEquals("send", h.getType());
    }

    @Test
    public void testGetAccountList() throws Exception {
        expectJson("account_list");

        AccountList list = client.getAccountList(WALLET);
        assertEquals(3, list.getAccounts().size());
        assertEquals(ACCOUNT, list.getAccounts().get(0));
    }

    private void expectJson(String name) throws IOException {
        when(httpClient.post(anyString())).thenReturn(loadJson(name));
    }

    private String loadJson(String name) {
        String filename = "json/" + name + ".json";
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(filename);
        return new Scanner(stream).useDelimiter("\\A").next();
    }
}
