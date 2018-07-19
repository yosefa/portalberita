package co.ganteng.aplikasiportalberita.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static co.ganteng.aplikasiportalberita.MyConstant.URL_API;

public class ConfigRetrofit {

    public static Retrofit setInit() {
        return new Retrofit.Builder().baseUrl(URL_API).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiService getInstance() {
        return setInit().create(ApiService.class);
    }
}
