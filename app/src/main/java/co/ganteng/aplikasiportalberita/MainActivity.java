package co.ganteng.aplikasiportalberita;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.ganteng.aplikasiportalberita.adapter.AdapterBerita;
import co.ganteng.aplikasiportalberita.network.ApiService;
import co.ganteng.aplikasiportalberita.response.BeritaItem;
import co.ganteng.aplikasiportalberita.response.ResponseBerita;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static co.ganteng.aplikasiportalberita.network.ConfigRetrofit.getInstance;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);

        // todo 7 set recyclerview
        recyclerview.setHasFixedSize(true);
        // cara menampilkan datanya dalam bentuk vertical ke bawah
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        // method untuk menampilkan data berita pada menu utama
        showBerita();
    }

    private void showBerita() {

        // buat object dari class ApiService
        ApiService service = getInstance();

        // menyiapkan request ke url
        Call<ResponseBerita> beritaCall = service.getShowAllBerita();
        // kirim requestnya
        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {

                // buat kondisi
                if (response.isSuccessful()) {
                    Log.d("TAG", response.body().toString());

                    List<BeritaItem> berita = response.body().getBerita();

                    boolean status = response.body().isStatus();

                    // jika statusnya benar
                    if (status) {
                        AdapterBerita adapter = new AdapterBerita(MainActivity.this, berita);

                        // set recyclerview dengan adapter
                        recyclerview.setAdapter(adapter);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "berita tidak ada", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                Toast.makeText(MainActivity.this, "gagal gagal berhasil", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }
}
