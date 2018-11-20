package co.mewi.sample.mockpoem.net;

import java.util.List;

import co.mewi.sample.mockpoem.models.Poem;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PoemistService {

    @GET("/api/v1/randompoems")
    Call<List<Poem>> getPoems();

}
