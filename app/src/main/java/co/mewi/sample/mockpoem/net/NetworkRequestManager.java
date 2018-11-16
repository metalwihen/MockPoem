package co.mewi.sample.mockpoem.net;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkRequestManager {

    private static NetworkRequestManager networkRequestManager;
    private Retrofit retrofit;

    public static synchronized NetworkRequestManager getInstance() {
        if (networkRequestManager == null) {
            networkRequestManager = new NetworkRequestManager();
        }
        return networkRequestManager;
    }

    private NetworkRequestManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.poemist.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getPoems(Callback<List<Poem>> callback) {
        PoemistService service = retrofit.create(PoemistService.class);
        service.getPoems().enqueue(callback);
    }

}
