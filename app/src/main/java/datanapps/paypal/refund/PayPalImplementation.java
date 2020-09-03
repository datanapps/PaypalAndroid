package datanapps.paypal.refund;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;

import datanapps.paypal.config.PaypalConfig;
import datanapps.paypal.refund.models.APIAccessToken;
import datanapps.paypal.refund.models.refund.APIRefund;
import datanapps.paypal.refund.models.transactions.APITransaction;
import datanapps.paypal.refund.networkservices.apiservices.PaypalAPIServicesImpl;

import datanapps.paypal.refund.networkservices.retrofit.RetrofitEventListener;
import retrofit2.Call;

public class PayPalImplementation {


    public static  String clientKey;
    public static String clientSecret;

    private Activity activity;

    private boolean isLiveEnvironment;


    public void initialise(Activity context, String clientKey, String clientSecret, boolean isLiveEnvironment){

        this.activity = context;
        this.clientKey = clientKey;
        this.clientSecret = clientSecret;


        initPaypal(isLiveEnvironment);

    }



    public PayPalConfiguration getEnviorment(){


        PayPalConfiguration configSand = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(clientKey);


        PayPalConfiguration configLive = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(clientKey);

        return isLiveEnvironment?configLive:configSand;

    }


    private void initPaypal(boolean isLiveEnvironment) {
        //start datanapps.paypal service

        if(activity==null){
            Log.d("asd", "Context can not be null");
            return;
        }


        Intent intent = new Intent(activity, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, getEnviorment());
        activity.startService(intent);
    }



    public void processPayment(double amount, String itemDetail, int requestCode) {

        if(activity==null){
            Log.d("asd", "Context can not be null");
            return;
        }

        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(amount), "USD",
                itemDetail, PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(activity, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, getEnviorment());
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        activity.startActivityForResult(intent, requestCode);
    }


    /*
    *
    * Get Access Token
    * */

    public void getAccessToken(String paypalOrderId) {

        if(activity==null){
            Log.d("asd", "Context can not be null");
            return;
        }


        if(paypalOrderId==null){
            Log.d("asd", "Paypal order id can not be null");
            return;
        }


        // call accessToken
        PaypalAPIServicesImpl paypalAPIServices = PaypalAPIServicesImpl.getInstance();

        paypalAPIServices.getAccessToken(new RetrofitEventListener() {
            @Override
            public void onSuccess(Call call, Object response) {
                if (response instanceof APIAccessToken) {
                    Log.d("asd", "Get Token Now");
                }
            }

            @Override
            public void onError(Call call, Throwable t) {

            }
        });
    }


    /*
    *
    * Get transaction detail
    * */

    public void getTransactionId(String paypalOrderId) {

        if(activity==null){
            Log.d("asd", "Context can not be null");
            return;
        }

        if(paypalOrderId==null){
            Log.d("asd", "Paypal order id can not be null");
            return;
        }


        // call accessToken
        PaypalAPIServicesImpl paypalAPIServices = PaypalAPIServicesImpl.getInstance();

        paypalAPIServices.getTransactionDetail(paypalOrderId, new RetrofitEventListener() {
            @Override
            public void onSuccess(Call call, Object response) {
                if (response instanceof APITransaction) {
                    APITransaction apiTransaction = (APITransaction) response;


                    Log.d("asd", "Transaction Id : "+apiTransaction.getTransactions().get(0).getRelatedResources().get(0).getSale().getId());
                }
            }

            @Override
            public void onError(Call call, Throwable t) {

            }
        });
    }


/*
*
* Call refund API
* */
    public void callRefundAPI(String transactionId) {

        if(activity==null){
            Log.d("asd", "Context can not be null");
            return;
        }


        if(transactionId==null){
            Log.d("asd", "Paypal order id can not be null");
            return;
        }


        // call accessToken
        PaypalAPIServicesImpl paypalAPIServices = PaypalAPIServicesImpl.getInstance();

        paypalAPIServices.callRefundAPI(transactionId, new RetrofitEventListener() {
            @Override
            public void onSuccess(Call call, Object response) {
                if (response instanceof APIRefund) {
                    Log.d("asd", "Transaction Id : ");

                   // Bundle bundle =  new Bundle();
                    Intent intent = activity.getIntent();
                    intent.putExtra(PaypalConfig.PAYPAL_REFUND_DATA, (APIRefund)response);

                    activity.startActivityForResult(intent, PaypalConfig.PAYPAL_REFUND_REQUEST_CODE);
                }
            }

            @Override
            public void onError(Call call, Throwable t) {
                Log.d("asd", t.getMessage()+"");
            }
        });
    }


    /*
    *
    * Call transaction and Refund API
    * */

    public void callTransactionDetailAndRefund(String paypalOrderId) {

        if(activity==null){
            Log.d("asd", "Context can not be null");
            return;
        }


        if(paypalOrderId==null){
            Log.d("asd", "Paypal order id can not be null");
            return;
        }


        // call accessToken
        PaypalAPIServicesImpl paypalAPIServices = PaypalAPIServicesImpl.getInstance();

        paypalAPIServices.getTransactionDetail(paypalOrderId, new RetrofitEventListener() {
            @Override
            public void onSuccess(Call call, Object response) {
                if (response instanceof APITransaction) {
                    APITransaction apiTransaction = (APITransaction) response;
                    Log.d("asd", "Transaction Id : "+apiTransaction.getTransactions().get(0).getRelatedResources().get(0).getSale().getId());
                    callRefundAPI(apiTransaction.getTransactions().get(0).getRelatedResources().get(0).getSale().getId());

                }
            }

            @Override
            public void onError(Call call, Throwable t) {
                Log.d("asd", t.getMessage()+"");
            }
        });
    }






}
