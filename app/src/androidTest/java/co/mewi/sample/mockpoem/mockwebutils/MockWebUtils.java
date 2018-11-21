package co.mewi.sample.mockpoem.mockwebutils;

import java.io.IOException;

import co.mewi.sample.mockpoem.net.Injector;
import okhttp3.mockwebserver.MockWebServer;

public class MockWebUtils {

    private static MockWebServer mockWebServer;

    public static void callOnSetup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        Injector.setUrl(mockWebServer.url("/").toString());
    }

    public static MockWebServer getMockWebServer() {
        return mockWebServer;
    }

    public static void callOnTearDown() throws IOException {
        if (mockWebServer != null) {
            mockWebServer.shutdown();
        }
    }
}
