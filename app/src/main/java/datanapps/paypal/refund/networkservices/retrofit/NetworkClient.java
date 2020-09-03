package datanapps.paypal.refund.networkservices.retrofit;

import java.util.concurrent.TimeUnit;

import datanapps.paypal.config.PaypalConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetworkClient {


    public static final String BASE_URL_SAND_BOX = "https://api.sandbox.paypal.com";
    public static final String BASE_URL_LIVE = "https://api.paypal.com";

    private static final int TIMEOUT = 10;


    /*
    This public static method will return Retrofit client
    anywhere in the appplication
    */
    public static Retrofit getRetrofitClient() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL_SAND_BOX)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient());
        return builder.build();
    }

    public static OkHttpClient getOkHttpClient() {

        return new OkHttpClient().newBuilder().connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new BasicAuthInterceptor(PaypalConfig.PAYPAL_CLIENT_ID, PaypalConfig.PAYPAL_CLIENT_SECRET))
                .build();
    }

}
