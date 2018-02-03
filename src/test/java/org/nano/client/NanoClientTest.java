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
    private static final String INVALID_ACCOUNT = "invalid_account";
    private static final String BAD_ACCOUNT_NUMBER_ERROR = "Bad account number";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private HttpClient httpClient;
    private NanoClient client;

    @Before
    public void setUp() throws Exception {
        httpClient = mock(HttpClient.class);
        client = new NanoClient(httpClient);
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
        client.getAccountBalance(INVALID_ACCOUNT);
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
        client.getAccountBlockCount(INVALID_ACCOUNT);
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
        client.getAccountInformation(INVALID_ACCOUNT);
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
