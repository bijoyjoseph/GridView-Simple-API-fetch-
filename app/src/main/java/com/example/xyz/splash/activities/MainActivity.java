package com.example.xyz.splash.activities;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.xyz.splash.R;
import com.example.xyz.splash.adapter.CountryAdapter;
import com.example.xyz.splash.models.CountryModel;
import com.example.xyz.splash.util.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetworkUtil.Network.ResponseCallBack {

    private GridView mGridView;
    private ProgressBar mProgressBar;

    private CountryAdapter mCountryAdapter;
    private ArrayList<CountryModel> countryModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        }

        new NetworkUtil.Network(this, NetworkUtil.COUNTRY_LIST, null, 0).execute();

        mGridView = (GridView) findViewById(R.id.gridView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        countryModelArrayList = new ArrayList<>();
        mCountryAdapter = new CountryAdapter(this, R.layout.country_item_layout, countryModelArrayList);

        mGridView.setAdapter(mCountryAdapter);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void responseCallBack(String response, int responseCode, int connectionId) {
        /*btnLogin.setVisibility(View.VISIBLE);
        mProgressView.setVisibility(View.GONE);*/
        try {
            if (response.equals(NetworkUtil.EXCEPTION)) {
                NetworkUtil.displayMessage("Something went wrong", this);
                return;
            } else if (response.equals(NetworkUtil.NO_INTERNET)) {
                NetworkUtil.displayMessage("No Internet Connection", this);
                return;
            } else if (responseCode == 412 || responseCode == 500) {
                JSONObject jsonObject = new JSONObject(response);
                NetworkUtil.displayMessage(jsonObject.getString("message"), this);
                return;
            } else {

                mProgressBar.setVisibility(View.GONE);
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("Response");

                //Log.e("response", jsonArray.toString());

                /*The API returns a list of 250 countries of which I am displaying only 30.
                If you want to see the list of entire countries then remove the length that
                I have set on the below for-loop.*/

                for(int i =0 ; i< jsonArray.length()-230; i++){
                    JSONObject object = jsonArray.getJSONObject(i);

                    CountryModel countryModel = new CountryModel();
                    countryModel.setCountryName(object.getString("Name"));
                    countryModel.setCountryNativeName(object.getString("NativeName"));
                    countryModel.setRegion(object.getString("Region"));
                    countryModel.setCurrencyName(object.getString("CurrencyName"));
                    countryModel.setArea(object.getString("Area"));
                    countryModel.setFlagPng(object.getString("FlagPng"));
                    countryModel.setCurrencyCode(object.getString("CurrencyCode"));
                    countryModel.setAlpha2Code(object.getString("Alpha2Code"));
                    countryModel.setAlpha3Code(object.getString("Alpha3Code"));
                    countryModel.setLatitude(object.getString("Latitude"));
                    countryModel.setLongitude(object.getString("Longitude"));
                    countryModel.setSubRegion(object.getString("Longitude"));
                    countryModelArrayList.add(countryModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
