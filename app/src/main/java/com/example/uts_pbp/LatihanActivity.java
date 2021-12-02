package com.example.uts_pbp;

import static com.android.volley.Request.Method.DELETE;
import static com.android.volley.Request.Method.GET;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uts_pbp.adapters.LatihanAdapter;
import com.example.uts_pbp.api.LatihanApi;
import com.example.uts_pbp.content.TampilDataAlphabet;
import com.example.uts_pbp.geolocation.GeoActivity;
import com.example.uts_pbp.model.LatihanResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LatihanActivity extends AppCompatActivity {

    public static final int LAUNCH_ADD_ACTIVITY = 123;

    private SwipeRefreshLayout srLatihan;
    private LatihanAdapter adapter;
    private SearchView svLatihan;
    private LinearLayout layoutLoading;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView;

        // Pendeklarasian request queue
        queue = Volley.newRequestQueue(this);

        layoutLoading = findViewById(R.id.layout_loading);
        srLatihan = findViewById(R.id.sr_latihan);
        svLatihan = findViewById(R.id.sv_latihan);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.beranda);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.beranda:
                        startActivity(new Intent(LatihanActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.baca:
                        startActivity(new Intent(LatihanActivity.this, TampilDataAlphabet.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.lokasi:
                        startActivity(new Intent(LatihanActivity.this, GeoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.tentang:
                        startActivity(new Intent(LatihanActivity.this, TentangActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.latihan:
                        startActivity(new Intent(LatihanActivity.this, LatihanActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        srLatihan.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllProduk();
            }
        });

        svLatihan.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LatihanActivity.this, AddEditActivity.class);
                startActivityForResult(i, LAUNCH_ADD_ACTIVITY);
            }
        });

        RecyclerView rvMahasiswa = findViewById(R.id.rv_latihan);
        adapter = new LatihanAdapter(new ArrayList<>(), this);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            //Grid Layout Portrait
            rvMahasiswa.setLayoutManager(new GridLayoutManager(LatihanActivity.this, 2));
        }else{
            //Grid Layout Landscape
            rvMahasiswa.setLayoutManager(new GridLayoutManager(LatihanActivity.this, 4));
        }
        rvMahasiswa.setAdapter(adapter);

        getAllProduk();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_ADD_ACTIVITY && resultCode == Activity.RESULT_OK)
            getAllProduk();
    }

    private void getAllProduk() {
        // TODO: Tambahkan fungsi untuk menampilkan seluruh data buku.
        srLatihan.setRefreshing(true);

        StringRequest stringRequest = new StringRequest(GET, LatihanApi.GET_ALL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                 /* Deserialiasai data dari response JSON dari API
                 menjadi java object MahasiswaResponse menggunakan Gson */
                LatihanResponse latihanResponse = gson.fromJson(response, LatihanResponse.class);

                adapter.setLatihanList(latihanResponse.getLatihanList());
                adapter.getFilter().filter(svLatihan.getQuery());

                Toast.makeText(LatihanActivity.this, latihanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                srLatihan.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                srLatihan.setRefreshing(false);

                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(LatihanActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(LatihanActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            // Menambahkan header pada request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }
        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);
    }

    public void deleteProduk(long id) {
        // TODO: Tambahkan fungsi untuk menghapus data buku.
        setLoading(true);
        StringRequest stringRequest = new StringRequest(DELETE, LatihanApi.DELETE_URL + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                 /* Deserialiasai data dari response JSON dari API
                 menjadi java object MahasiswaResponse menggunakan Gson */
                LatihanResponse latihanResponse = gson.fromJson(response, LatihanResponse.class);

                setLoading(false);
                Toast.makeText(LatihanActivity.this, latihanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                getAllProduk();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);

                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(LatihanActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(LatihanActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            // Menambahkan header pada request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }
        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);
    }

    // Fungsi ini digunakan menampilkan layout loading
    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.GONE);
        }
    }



}