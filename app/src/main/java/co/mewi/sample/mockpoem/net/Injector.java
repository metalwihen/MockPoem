package co.mewi.sample.mockpoem.net;

public class Injector {

    private static String sUrl;

    private Injector() {
    }

    public static void setUrl(String url) {
        sUrl = url;
    }

    public static String getUrl() {
        if (sUrl == null) {
            sUrl = NetworkRequestManager.URL;
        }
        return sUrl;
    }
}
