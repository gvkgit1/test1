package com.example.myapplicationtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    RecyclerView recyclerView;
    RecyclerviewItemAdapter recyclerviewItemAdapter;
    List<getdata> itemsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsList = new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        101);

            }
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);


        Call<List<UsersModel>> call = ApiClient.getInstance().getMyApi().usersdata();

        call.enqueue(new Callback<List<UsersModel>>() {
            @Override
            public void onResponse(Call<List<UsersModel>> call, Response<List<UsersModel>> response) {


                Log.d("TAG_67", response.code() + "");
                List<UsersModel> resource = response.body();
                for(int i=0;i<resource.size();i++) {


                    Integer id = resource.get(i).getId();
                    String name = resource.get(i).getName();
                    String username = resource.get(i).getUsername();
                    String email = resource.get(i).getEmail();

                    String street = resource.get(i).getAddress().getStreet();
                    String suite = resource.get(i).getAddress().getSuite();
                    String city = resource.get(i).getAddress().getCity();
                    String zipcode = resource.get(i).getAddress().getZipcode();


                    String lat = resource.get(i).getAddress().getGeo().getLat();
                    String lng = resource.get(i).getAddress().getGeo().getLng();

                    String phone = resource.get(i).getPhone();
                    String website = resource.get(i).getWebsite();

                    String name1 = resource.get(i).getCompany().getName();
                    String catchPhrase = resource.get(i).getCompany().getCatchPhrase();
                    String bs = resource.get(i).getCompany().getBs();


                    getdata items = new getdata(id, name, username, email, street, suite, city, zipcode, lat, lng, phone, website, name1, catchPhrase, bs);
                    itemsList.add(items);

                }
                recyclerviewItemAdapter = new RecyclerviewItemAdapter(itemsList,mapFragment);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);

                        if(newState==RecyclerView.SCROLL_STATE_IDLE){
                            recyclerView.scrollToPosition(((LinearLayoutManager)recyclerView.getLayoutManager())
                                    .findFirstVisibleItemPosition());
                        }
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });

                recyclerView.setAdapter(recyclerviewItemAdapter);
            }

            @Override
            public void onFailure(Call<List<UsersModel>> call, Throwable t) {
                Log.i("TAG_67", "onFailure: " + t.getMessage());
                Log.i("TAG_67", "onFailure: " + t.getCause() + t.getStackTrace());


            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleM) {

    }

}