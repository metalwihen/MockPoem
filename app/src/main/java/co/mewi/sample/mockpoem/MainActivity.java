package co.mewi.sample.mockpoem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import co.mewi.sample.mockpoem.net.NetworkRequestManager;
import co.mewi.sample.mockpoem.net.Poem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String FAIL = "FAILURE";
    private static final String LOADING = "LOADING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView) findViewById(R.id.output)).setText(LOADING);
                NetworkRequestManager.getInstance().getPoems(new Callback<List<Poem>>() {
                    @Override
                    public void onResponse(Call<List<Poem>> call, Response<List<Poem>> response) {
                        if (response.isSuccessful() && response != null && response.body() != null && response.body().size() > 0) {
                            ((TextView) findViewById(R.id.output)).setText(response.body().get(0).content);
                        } else {
                            ((TextView) findViewById(R.id.output)).setText(FAIL);
                            Log.e("MockPoem", "Failed due to invalid response");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Poem>> call, Throwable t) {
                        ((TextView) findViewById(R.id.output)).setText(FAIL);
                        t.printStackTrace();
                    }
                });
            }
        });
    }

}
