
package com.example.myapplicationtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewItemAdapter extends RecyclerView.Adapter<RecyclerviewItemAdapter.MyViewHolder> implements OnMapReadyCallback {

    double longitude,latitude,lon=0.0,lat=0.0;
    GoogleMap googleMap;
    SupportMapFragment mapFragment;
    LatLng placeLocation;

    private List<getdata> itemsList;

    RecyclerviewItemAdapter(List<getdata> mItemList, SupportMapFragment mapFragment){
        this.itemsList = mItemList;
        this.mapFragment=mapFragment;
        //mapFragment.getMapAsync(this);
    }

    @Override
    public RecyclerviewItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //mapFragment.getMapAsync(this);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new MyViewHolder(view);

    }



    @Override public void onBindViewHolder(RecyclerviewItemAdapter.MyViewHolder holder, int position) {

        mapFragment.getMapAsync(this);

        getdata getdataele = itemsList.get(position);

        //mapFragment.getMapAsync(this);

        holder.loc.setText("\nLATITUDE    "+getdataele.getLat()+"\nLONGITUDE  "+getdataele.getLng()+"\n");

        holder.defaultnames.setText("id \nName \nUser Name \nEmail \n"+
                "ADDRESS\nStreet \nSuite \nCity \nZipcode \nGEO\nLatitude \nLongitude "+
                "\nCOMPANY\nName \nCatch Pharse \nBs \nPh No. \nWebsite ");

        holder.name.setText(getdataele.getId()+"\n"+getdataele.getName()+"\n"+getdataele.getUsername()+"\n"+getdataele.getEmail()+
                "\n\n"+getdataele.getStreet()+"\n"+getdataele.getSuite()+"\n"+getdataele.getCity()+"\n"+getdataele.getZipcode()+"\n\n"+
                getdataele.getLat()+"\n"+getdataele.getLng()+"\n\n"+getdataele.getName1()+"\n"+getdataele.getCatchPhrase()+"\n"+getdataele.getBs()+
                "\n"+getdataele.getPhone()+"\n"+getdataele.getWebsite());



        //mapFragment.getMapAsync(this);
        if(lat!=Double.parseDouble(getdataele.getLat())) {

            //mapFragment.getMapAsync(this);

            longitude = Double.parseDouble(getdataele.getLng());
            latitude = Double.parseDouble(getdataele.getLat());
            lat=Double.parseDouble(getdataele.getLat());

            //mapFragment.getMapAsync(this);
            placeLocation = new LatLng(latitude,longitude);

        }
        //placeLocation = new LatLng(latitude,longitude);
        //mapFragment.getMapAsync(this);

    }


    @Override
    public int getItemCount() {
        //mapFragment.getMapAsync(this);
        return itemsList.size();
    }

    @Override
    public void onMapReady(GoogleMap googleM) {

        //mapFragment.getMapAsync(this);

        googleMap=googleM;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        try {
            googleMap.setMyLocationEnabled(true);
            //mapFragment.getMapAsync(this);

        } catch (SecurityException se) {

        }

        //LatLng placeLocation = new LatLng(latitude,longitude);

        //mapFragment.getMapAsync(this);

        googleMap.addMarker(new MarkerOptions().position(placeLocation));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(placeLocation));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 1000, null);

        //mapFragment.getMapAsync(this);


    }


    class MyViewHolder extends RecyclerView.ViewHolder{


        public TextView defaultnames,name,loc;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            loc=itemView.findViewById(R.id.textView2);
            defaultnames=itemView.findViewById(R.id.textView3);
            //mapFragment.getMapAsync(RecyclerviewItemAdapter.this::onMapReady);


            /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);*/
        }
    }

}