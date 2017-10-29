package com.example.xyz.splash.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xyz on 29/10/17.
 */

public class NetworkUtil {

    public static final String COUNTRY_LIST = "http://countryapi.gear.host/v1/Country/getCountries";

    public static final String NO_INTERNET = "NO_INTERNET";
    public static final String EXCEPTION = "EXCEPTION";

    public static class Network extends AsyncTask<Void, Void, String> {

        private Context context;
        private String uri;
        private JSONObject params;
        private ResponseCallBack responseCallBack;
        private int responseCode;
        private int connectionId;

        public interface ResponseCallBack {
            void responseCallBack(String response, int responseCode, int connectionId);
        }

        public Network(Context context, String uri, JSONObject params, int connectionId) {
            this.context = context;
            this.uri = uri;
            this.params = params;
            responseCallBack = (ResponseCallBack) context;
            this.connectionId = connectionId;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        public String doInBackground(Void... params) {
            if (!isNetworkAvailable(context)) {
                return NO_INTERNET;
            }
            HttpURLConnection con;
            String responseTxt = null;
            try {
                URL x = new URL(uri);
                con = (HttpURLConnection) x.openConnection();
                if (this.params != null) {
                    OutputStream os = con.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(this.params.toString());
                    writer.flush();
                    writer.close();
                }
                con.connect();
                responseCode = con.getResponseCode();
                InputStream inputstream;
                if (responseCode == 200) {
                    inputstream = con.getInputStream();
                } else {
                    inputstream = con.getErrorStream();
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                responseTxt = builder.toString();
                inputstream.close();
                con.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                responseTxt = EXCEPTION;
            }
            return responseTxt;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            responseCallBack.responseCallBack(s, responseCode, connectionId);
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            if (context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void displayMessage(String message, Activity activity) {
        Snackbar snack = Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(activity, android.R.color.white));
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        snack.show();
    }
}
