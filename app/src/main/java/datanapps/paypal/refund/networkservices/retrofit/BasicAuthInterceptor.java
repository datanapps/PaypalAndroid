package datanapps.paypal.refund.networkservices.retrofit;

import java.io.IOException;

import datanapps.paypal.config.PaypalConfig;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {


    private String clientKey;
    private String secretKey;


    BasicAuthInterceptor(String clientKey, String secretKey){
        this.clientKey=clientKey;
        this.secretKey=secretKey;
    }

    String credentials = Credentials.basic(PaypalConfig.PAYPAL_CLIENT_ID, PaypalConfig.PAYPAL_CLIENT_SECRET);

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder()
                .header("Authorization", credentials);
        Request request = builder.build();
        return chain.proceed(request);
    }
}
