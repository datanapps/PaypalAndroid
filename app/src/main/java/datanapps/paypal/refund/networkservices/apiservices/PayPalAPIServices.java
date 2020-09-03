package datanapps.paypal.refund.networkservices.apiservices;

import java.util.Map;


import datanapps.paypal.refund.models.APIAccessToken;
import datanapps.paypal.refund.models.refund.APIRefund;
import datanapps.paypal.refund.models.transactions.APITransaction;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * API for getting letslit
 */

public interface PayPalAPIServices {

    // access token
    @POST("v1/oauth2/token")
    Call<APIAccessToken> getAccessToken(@QueryMap Map<String, String> options);


    @GET("v1/payments/payment/{paypal_order_id}") //PAY-6G391285C17409004L4OYRLQ
    Call<APITransaction> getTransactionId(@Path("paypal_order_id") String paypalOrderId);


    @Headers({"Content-Type: application/json"})
    @POST("v1/payments/sale/{transaction_id}/datanapps.paypaldemo.refund") //PAY-6G391285C17409004L4OYRLQ
    Call<APIRefund> callRefundAPI(@Path("transaction_id") String transactionId);



}
