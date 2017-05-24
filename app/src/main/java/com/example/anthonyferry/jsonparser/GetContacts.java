package com.example.anthonyferry.jsonparser;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Anthony FERRY on 24/05/2017.
 */

public class GetContacts extends AsyncTask<String, Void, String> {

    private Context context;

    @Override
    protected String doInBackground(String... params) {
        // Lien vers l'api
        String link = "http://api.androidhive.info/contacts/";
        BufferedReader bufferedReader;
        String result;

        try{
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            Log.d("Result", result);

            return result;
        }
        catch (Exception e)
        {
            Log.d("Erreur", e.getMessage());
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        String jsonStr = result;
        Log.d("JSON", jsonStr);
        if (jsonStr != null)
        {
            try {

                JSONObject jsonObj = new JSONObject(jsonStr);
                JSONArray contacts = jsonObj.getJSONArray("contacts");


                for (int i = 0; i < contacts.length(); i++)
                {
                    Log.e("name", contacts.getJSONObject(i).getString("name"));

                }


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Erreur lors de l'interprétation des données", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Erreur de données", Toast.LENGTH_SHORT).show();
        }
    }


    public GetContacts(Context context) {
        this.context = context;
    }
}
