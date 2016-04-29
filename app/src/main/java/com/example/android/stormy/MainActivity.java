package com.example.android.stormy;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = "a79dc965f755b2fe63f92174cdf1a7ea";
        double latitude = 37.8267;
        double longtitude = -122.423;
        String forecastUrl = "https://api.forecast.io/forecast/" + apiKey + "/" + latitude +
                "," + longtitude;

        //Create a new client
        OkHttpClient client = new OkHttpClient();

        //Create a request
        Request request = new Request.Builder()
                .url(forecastUrl)
                .build();

        //Call the request
        Call call = client.newCall(request);
        //enque queues the call
        //Callback is communication bridge of the background and main thread
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log the response
                try {
                    Log.v(TAG, response.body().string());
                    if (response.isSuccessful()){

                    }
                    else{
                        alertUserAboutError();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception caught:", e);
                }
            }
        });
        Log.d(TAG, "Main UI code is running!");
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");

    }
}
