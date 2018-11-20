package co.mewi.sample.mockpoem.net;

import java.util.List;

import co.mewi.sample.mockpoem.models.Poem;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkRequestManager {

    private static NetworkRequestManager networkRequestManager;
    private Retrofit retrofit;

    public static final String URL = "https://www.poemist.com/api/";

    public static synchronized NetworkRequestManager getInstance(String url) {
        if (networkRequestManager == null) {
            networkRequestManager = new NetworkRequestManager(url);
        }
        return networkRequestManager;
    }

    private NetworkRequestManager(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getPoems(Callback<List<Poem>> callback) {
        PoemistService service = retrofit.create(PoemistService.class);
        service.getPoems().enqueue(callback);
    }

}
