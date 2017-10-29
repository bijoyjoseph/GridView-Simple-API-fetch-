package com.example.xyz.splash.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xyz.splash.R;

public class CountryDetailsActivity extends AppCompatActivity {

    private ImageView countryImage;
    private TextView countryName;
    private TextView countryLocalName;
    private TextView countryregion;
    private TextView countryArea;
    private TextView countryCurrency;
    private TextView currencyCode;
    private TextView alpha2Code;
    private TextView alpha3Code;
    private TextView latitude;
    private TextView longitude;
    private TextView subregion;

    private String _countryName;
    private String _countryLocalName;
    private String _countryRegion;
    private String _countryArea;
    private String _countryCurrency;
    private Bitmap _countryImage;
    private String _currencyCode;
    private String _alpha2Code;
    private String _alpha3Code;
    private String _latitude;
    private String _longitude;
    private String _subregion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        }

        countryImage = (ImageView) findViewById(R.id.country_image);
        countryName = (TextView) findViewById(R.id.country_name);
        countryLocalName = (TextView) findViewById(R.id.local_name);
        countryregion = (TextView) findViewById(R.id.country_region);
        countryArea = (TextView) findViewById(R.id.country_area);
        countryCurrency = (TextView) findViewById(R.id.country_currency);
        currencyCode = (TextView) findViewById(R.id.country_currency_code);
        alpha2Code = (TextView) findViewById(R.id.country_alpha2Code);
        alpha3Code = (TextView) findViewById(R.id.country_alpha3code);
        latitude = (TextView) findViewById(R.id.country_latitude);
        longitude = (TextView) findViewById(R.id.country_longitude);
        subregion = (TextView) findViewById(R.id.country_subregion);

        if(getIntent() != null) {

            Intent detailsIntent = getIntent();
            Bundle detailsBundle = detailsIntent.getExtras();
            _countryImage = (Bitmap) detailsBundle.getParcelable("countryImage");
            _countryName = detailsBundle.getString("countryName");
            _countryLocalName = detailsBundle.getString("countryLocalName");
            _countryRegion = detailsBundle.getString("countryRegion");
            _countryArea = detailsBundle.getString("countryArea");
            _countryCurrency = detailsBundle.getString("countryCurrency");
            _currencyCode = detailsBundle.getString("currencyCode");
            _alpha2Code = detailsBundle.getString("alpha2Code");
            _alpha3Code = detailsBundle.getString("alpha3Code");
            _latitude = detailsBundle.getString("latitude");
            _longitude = detailsBundle.getString("longitude");
            _subregion = detailsBundle.getString("subregion");

        }

        countryImage.setImageBitmap(_countryImage);
        countryName.setText(_countryName);
        countryLocalName.setText(getString(R.string.local_name) + _countryLocalName);
        countryregion.setText(getString(R.string.region) + _countryRegion);
        countryArea.setText(getString(R.string.area) + _countryArea + " sq/Km");
        countryCurrency.setText(getString(R.string.currency) + _countryCurrency);
        currencyCode.setText(getString(R.string.currency_code) + _currencyCode);
        alpha2Code.setText(getString(R.string.alpha2_code) + _alpha2Code);
        alpha3Code.setText(getString(R.string.alpha3_code) + _alpha3Code);
        latitude.setText(getString(R.string.latitude) + _latitude);
        longitude.setText(getString(R.string.longitude) + _longitude);
        subregion.setText(getString(R.string.subregion) + _subregion);
     }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
