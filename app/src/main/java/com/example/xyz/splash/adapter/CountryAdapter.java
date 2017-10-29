package com.example.xyz.splash.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xyz.splash.R;
import com.example.xyz.splash.activities.CountryDetailsActivity;
import com.example.xyz.splash.models.CountryModel;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by xyz on 29/10/17.
 */

public class CountryAdapter extends ArrayAdapter<com.example.xyz.splash.models.CountryModel> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<CountryModel> mCountryData = new ArrayList<>();

    public CountryAdapter(Context context, int layoutResourceId, ArrayList<CountryModel> mCountryData){
        super(context, layoutResourceId, mCountryData);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.mCountryData = mCountryData;
    }

    public void setGridData(ArrayList<CountryModel> mCountryData) {
        this.mCountryData = mCountryData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) row.findViewById(R.id.grid_item_desc);
            holder.imageView = (ImageView) row.findViewById(R.id.grid_item_image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.titleTextView.setText(mCountryData.get(position).getCountryName());
        new DownloadImageTask(holder.imageView).execute(mCountryData.get(position).getFlagPng());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryModel item = mCountryData.get(position);
                holder.imageView.buildDrawingCache();
                Bitmap image = holder.imageView.getDrawingCache();

                Bundle bundle = new Bundle();
                bundle.putParcelable("countryImage", image);
                bundle.putString("countryName", item.getCountryName());
                bundle.putString("countryLocalName", item.getCountryNativeName());
                bundle.putString("countryRegion", item.getRegion());
                bundle.putString("countryArea", item.getArea());
                bundle.putString("countryCurrency", item.getCurrencyName());
                bundle.putString("countryFlag", item.getFlagPng());
                bundle.putString("currencyCode", item.getCurrencyCode());
                bundle.putString("alpha2Code", item.getAlpha2Code());
                bundle.putString("alpha3Code", item.getAlpha3Code());
                bundle.putString("latitude", item.getLatitude());
                bundle.putString("longitude", item.getLongitude());
                bundle.putString("subregion", item.getSubRegion());

                Intent intent = new Intent(context, CountryDetailsActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return row;
    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        ProgressDialog dialog;

        public DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
            //dialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*dialog.setMessage("Downloading Data, please wait....");
            dialog.show();*/
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
            /*if (dialog.isShowing()) {
                dialog.dismiss();
            }*/
        }

    }

}
