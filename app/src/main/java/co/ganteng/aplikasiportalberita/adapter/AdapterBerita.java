package co.ganteng.aplikasiportalberita.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.ganteng.aplikasiportalberita.R;
import co.ganteng.aplikasiportalberita.activities.DetailBerita;
import co.ganteng.aplikasiportalberita.response.BeritaItem;

import static co.ganteng.aplikasiportalberita.MyConstant.URL_API;
import static co.ganteng.aplikasiportalberita.MyConstant.URL_API_IMAGES;

// todo 2 buat adapter untuk recyclerview
public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.ViewHolder> {

    Context context;
    List<BeritaItem> listBerita;

    public AdapterBerita(Context context, List<BeritaItem> listBerita) {
        this.context = context;
        this.listBerita = listBerita;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // todo 4 untuk menyisipkan layout lain di dalam adapter
        View view = LayoutInflater.from(context).inflate(R.layout.item_berita, viewGroup, false);
        ViewHolder objectViewHolder = new ViewHolder(view);
        return objectViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvJudulBerita.setText(listBerita.get(position).getJudulBerita());
        holder.tvTglPosting.setText(listBerita.get(position).getTanggalPosting());
        holder.tvPenulis.setText(listBerita.get(position).getPenulis());

        final String images = URL_API_IMAGES + listBerita.get(position).getFoto();
        Picasso.get().load(images).into(holder.ivBerita);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objectIntent = new Intent(context, DetailBerita.class);
                objectIntent.putExtra(DetailBerita.EXTRA_JUDUL, listBerita.get(position).getJudulBerita());
                objectIntent.putExtra(DetailBerita.EXTRA_ISI_BERITA, listBerita.get(position).getIsiBerita());
                objectIntent.putExtra(DetailBerita.EXTRA_PENULIS, listBerita.get(position).getPenulis());
                objectIntent.putExtra(DetailBerita.EXTRA_TGL_POSTING, listBerita.get(position).getTanggalPosting());
                objectIntent.putExtra(DetailBerita.EXTRA_GAMBAR_BERITA, listBerita.get(position).getFoto());
                context.startActivity(objectIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBerita.size();
    }

    static

    // todo 3 deklarasi variable dan hubungkan id nya pada layout item_buah.xml
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_berita)
        ImageView ivBerita;
        @BindView(R.id.tv_judul_berita)
        TextView tvJudulBerita;
        @BindView(R.id.tv_tgl_posting)
        TextView tvTglPosting;
        @BindView(R.id.tv_penulis)
        TextView tvPenulis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
