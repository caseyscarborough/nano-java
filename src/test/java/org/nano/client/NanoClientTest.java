package org.nano.client;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NanoClientTest {

    private static final String ACCOUNT = "xrb_1111111111111111111111111111111111111111111111";
    private HttpClient httpClient;
    private NanoClient client;

    @Before
    public void setUp() throws Exception {
        httpClient = mock(HttpClient.class);
        client = new NanoClient(httpClient);
    }

    @Test
    public void testGetAccountBalance() throws Exception {
        when(httpClient.post(Matchers.anyString())).thenReturn(loadJson("accounts/account_balance"));
        AccountBalance accountBalance = client.getAccountBalance(ACCOUNT);
        assertEquals("1002601076000000000000000000000000", accountBalance.getBalance());
        assertEquals(ACCOUNT, accountBalance.getAccount());
        assertEquals("126030480000000000000000000000000", accountBalance.getPending());
    }

    private String loadJson(String name) {
        String filename = "json/" + name + ".json";
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(filename);
        return new Scanner(stream).useDelimiter("\\A").next();
    }
}
