package datanapps.paypal.refund.networkservices.apiservices;


import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import datanapps.paypal.refund.networkservices.retrofit.NetworkClient;
import datanapps.paypal.refund.networkservices.retrofit.RetrofitEventListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class PaypalAPIServicesImpl {

    // static variable single_instance of type Singleton
    private static PaypalAPIServicesImpl paypalAPIServices = null;

    // private constructor restricted to this class itself
    private PaypalAPIServicesImpl() {
    }

    // static method to create instance of Singleton class
    public static PaypalAPIServicesImpl getInstance() {
        if (paypalAPIServices == null)
            paypalAPIServices = new PaypalAPIServicesImpl();
    return paypalAPIServices;
    }




    public void getAccessToken(final RetrofitEventListener retrofitEventListener) {

        Retrofit retrofit = NetworkClient.getRetrofitClient();
        PayPalAPIServices apiServices = retrofit.create(PayPalAPIServices.class);


        Map<String, String> hashMapData = new HashMap<>();
        hashMapData.put("grant_type", "client_credentials");


        Call accessToken = apiServices.getAccessToken(hashMapData);
        accessToken.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d("asd", "Server Responce : " + response.body().toString());
                if (response.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                retrofitEventListener.onError(call, t);
            }
        });
    }


    public void getTransactionDetail(String paypalOrderId, final RetrofitEventListener retrofitEventListener) {

        Retrofit retrofit = NetworkClient.getRetrofitClient();
        PayPalAPIServices apiServices = retrofit.create(PayPalAPIServices.class);


        Call accessToken = apiServices.getTransactionId(paypalOrderId);
        accessToken.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d("asd", "Server Responce : " + response.body().toString());
                if (response.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                retrofitEventListener.onError(call, t);
            }
        });
    }


    public void callRefundAPI(String transactionId, final RetrofitEventListener retrofitEventListener) {

        Retrofit retrofit = NetworkClient.getRetrofitClient();
        PayPalAPIServices apiServices = retrofit.create(PayPalAPIServices.class);


        Call accessToken = apiServices.callRefundAPI(transactionId);
        accessToken.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                if (response.body() != null) {
                    Log.d("asd", "Server Responce : " + response.body().toString());
                    retrofitEventListener.onSuccess(call, response.body());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                retrofitEventListener.onError(call, t);
            }
        });
    }


}
