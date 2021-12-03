package com.example.uts_pbp;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;
import static com.android.volley.Request.Method.PUT;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.uts_pbp.api.LatihanApi;
import com.example.uts_pbp.geolocation.GeoActivity;
import com.example.uts_pbp.model.Latihan;
import com.example.uts_pbp.model.LatihanResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AddEditActivity extends AppCompatActivity {

    private EditText etNama, etHarga, etDeskripsi;
    private ImageView ivGambar;
    private LinearLayout layoutLoading;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        // Pendeklarasian request queue
        queue = Volley.newRequestQueue(this);

        ivGambar = findViewById(R.id.iv_gambar);
        etNama = findViewById(R.id.et_nama);
        etHarga = findViewById(R.id.et_harga);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        layoutLoading = findViewById(R.id.layout_loading);

        Button btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnSave = findViewById(R.id.btn_save);
        TextView tvTitle = findViewById(R.id.tv_title);
        long id = getIntent().getLongExtra("id", -1);

        if (id == -1) {
            tvTitle.setText("Tambah Latihan");

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createProduk();
                }
            });
        } else {
            tvTitle.setText("Edit Latihan");
            getProdukById(id);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateProduk(id);
                }
            });
        }
    }

    private void getProdukById(long id) {
        // TODO: Tambahkan fungsi untuk menampilkan data buku berdasarkan id.
        //  (hint: gunakan Glide untuk load gambar)
        setLoading(true);

        // Membuat request baru untuk mengambil data mahasiswa berdasarkan id
        StringRequest stringRequest = new StringRequest(GET, LatihanApi.GET_BY_ID_URL + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                 /* Deserialiasai data dari response JSON dari API
                 menjadi java object ProdukResponse menggunakan Gson */
                LatihanResponse latihanResponse = gson.fromJson(response, LatihanResponse.class);

                Latihan latihan = latihanResponse.getLatihanList().get(0);
                etNama.setText(latihan.getAlfabet());
                etHarga.setText(latihan.getNama());
                etDeskripsi.setText(latihan.getUrlgambar());
                Glide.with(ivGambar)
                        .load(latihan.getUrlgambar())
                        .placeholder(R.drawable.tanpagambar)
                        .into(ivGambar);

                Toast.makeText(AddEditActivity.this, latihanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(AddEditActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(AddEditActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void createProduk() {
        // TODO: Tambahkan fungsi untuk menambah data buku.
        setLoading(true);

        Latihan latihan = new Latihan(
                etNama.getText().toString(),
                etHarga.getText().toString(),
                etDeskripsi.getText().toString());

        // Membuat request baru untuk membuat data mahasiswa baru
        StringRequest stringRequest = new StringRequest(POST, LatihanApi.ADD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                /* Deserialiasai data dari response JSON dari API
                menjadi java object ProdukResponse menggunakan Gson */
                LatihanResponse latihanResponse = gson.fromJson(response, LatihanResponse.class);

                Toast.makeText(AddEditActivity.this, latihanResponse.getMessage(), Toast.LENGTH_SHORT).show();

                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

                setLoading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(AddEditActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(AddEditActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
            // Menambahkan request body berupa object produk
            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                /* Serialisasi data dari java object MahasiswaResponse
                menjadi JSON string menggunakan Gson */
                String requestBody = gson.toJson(latihan);

                return requestBody.getBytes(StandardCharsets.UTF_8);
            }
            // Mendeklarasikan content type dari request body yang ditambahkan
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);
    }

    private void updateProduk(long id) {
        // TODO: Tambahkan fungsi untuk mengubah data buku.
        setLoading(true);

        Latihan latihan = new Latihan(
                etNama.getText().toString(),
                etHarga.getText().toString(),
                etDeskripsi.getText().toString());

        // Membuat request baru untuk mengedit data mahasiswa
        StringRequest stringRequest = new StringRequest(PUT, LatihanApi.UPDATE_URL + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                /* Deserialiasai data dari response JSON dari API
                menjadi java object MahasiswaResponse menggunakan Gson */
                LatihanResponse latihanResponse = gson.fromJson(response, LatihanResponse.class);

                Toast.makeText(AddEditActivity.this, latihanResponse.getMessage(), Toast.LENGTH_SHORT).show();Intent returnIntent = new Intent();

                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                setLoading(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);

                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(AddEditActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(AddEditActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
            // Menambahkan request body berupa object mahasiswa
            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                 /* Serialisasi data dari java object ProdukResponse
                 menjadi JSON string menggunakan Gson */
                String requestBody = gson.toJson(latihan);
                return requestBody.getBytes(StandardCharsets.UTF_8);
            }
            // Mendeklarasikan content type dari request body yang ditambahkan
            @Override
            public String getBodyContentType() {
                return "application/json";
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
            layoutLoading.setVisibility(View.INVISIBLE);
        }
    }

}