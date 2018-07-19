package co.ganteng.aplikasiportalberita.network;

import co.ganteng.aplikasiportalberita.response.ResponseBerita;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    // get end_point dari folder portal_berita
    @GET("tampil_berita.php")
    Call<ResponseBerita> getShowAllBerita();
}
