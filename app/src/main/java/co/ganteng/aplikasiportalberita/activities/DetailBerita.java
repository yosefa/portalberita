package co.ganteng.aplikasiportalberita.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.ganteng.aplikasiportalberita.R;

import static co.ganteng.aplikasiportalberita.MyConstant.URL_API_IMAGES;

public class DetailBerita extends AppCompatActivity {

    public static final String EXTRA_JUDUL = "judul";
    public static final String EXTRA_GAMBAR_BERITA = "gambar";
    public static final String EXTRA_TGL_POSTING = "tanggal";
    public static final String EXTRA_PENULIS = "penulis";
    public static final String EXTRA_ISI_BERITA = "isi";

    @BindView(R.id.ivGambarBerita)
    ImageView ivGambarBerita;
    @BindView(R.id.tv_tgl_posting_detail)
    TextView tvTglPostingDetail;
    @BindView(R.id.tv_penulis_detail)
    TextView tvPenulisDetail;
    @BindView(R.id.tv_judul_detail)
    TextView tvJudulDetail;
    @BindView(R.id.wv_content_berita)
    WebView wvContentBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        ButterKnife.bind(this);

        showDetailBerita();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void showDetailBerita() {

        // get data yang dikirim dari adapter
        String judul = getIntent().getStringExtra(EXTRA_JUDUL);
        String gambar = getIntent().getStringExtra(EXTRA_GAMBAR_BERITA);
        String tgl = getIntent().getStringExtra(EXTRA_TGL_POSTING);
        String penulis = getIntent().getStringExtra(EXTRA_PENULIS);
        String isi = getIntent().getStringExtra(EXTRA_ISI_BERITA);

        // Set isi berita sebagai html ke WebView
        wvContentBerita.getSettings().setJavaScriptEnabled(true);
        wvContentBerita.loadData("<p style='text-align:justify'>" + isi + "</p>", "text/html; charset=utf-8", "UTF-8");

        tvJudulDetail.setText(judul);
        getSupportActionBar().setTitle(judul);

        tvTglPostingDetail.setText(tgl);
        tvPenulisDetail.setText(penulis);
        // Untuk gambar berita
        Picasso.get().load(URL_API_IMAGES + gambar).into(ivGambarBerita);
    }
}
