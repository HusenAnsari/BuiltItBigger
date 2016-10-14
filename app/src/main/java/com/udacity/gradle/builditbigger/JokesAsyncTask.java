package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.husenansari.myapplication.backend.myApi.MyApi;
import com.example.husenansari.myapplication.backend.myApi.model.MyBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.husenansari.androidlibrary.JokeViewActivity;

import java.io.IOException;


public class JokesAsyncTask extends AsyncTask<Pair<Context,String>,Void,String> {
    private static MyApi myApiService = null;
    private Context context;

    public JokesAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/");
            myApiService = builder.build();
        }
        try {
            return myApiService.sendJoke(new MyBean()).execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(context, JokeViewActivity.class);
        intent.putExtra(JokeViewActivity.JOKE_KEY,result);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
//  return myApiService.sendJoke(new MyBean()).execute().getJoke();