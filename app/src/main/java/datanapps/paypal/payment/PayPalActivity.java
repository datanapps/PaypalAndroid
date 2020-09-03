package datanapps.paypal.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import datanapps.paypal.R;
import datanapps.paypal.config.PaypalConfig;
import datanapps.paypal.refund.PayPalImplementation;
import datanapps.paypal.refund.models.refund.APIRefund;


public class PayPalActivity extends AppCompatActivity {


    private String paypalOrderId = "PAY-0M98948388006303TL5IIMUY";
    private TextView tvPaymentResponse;
    private TextView tvRefundResponse;
    private PayPalImplementation payPalImplementation;

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        payPalImplementation = new PayPalImplementation();
        payPalImplementation.initialise(this, PaypalConfig.PAYPAL_CLIENT_ID, PaypalConfig.PAYPAL_CLIENT_SECRET, false);
        initViewStuff();

    }


    private void initViewStuff() {

        tvPaymentResponse = findViewById(R.id.tvPaymentRespnse);
        tvRefundResponse= findViewById(R.id.tvRefundRespnse);
        findViewById(R.id.btnPayNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payPalImplementation.processPayment(0.1f, "Item Order", PaypalConfig.PAYPAL_PAYMENT_REQUEST_CODE);
            }
        });


        findViewById(R.id.btnRefund).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //paypalOrderId = "PAY-1F623014M8948101BL5IHMRI";
                payPalImplementation.callTransactionDetailAndRefund(paypalOrderId);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PaypalConfig.PAYPAL_PAYMENT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                parsePaymentResponse(confirmation);
            } else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        } else if (requestCode == PaypalConfig.PAYPAL_REFUND_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                APIRefund apiRefund = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                parseRefundResponse(apiRefund);
            } else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
    }


    private void parsePaymentResponse(PaymentConfirmation confirmation) {
        if (confirmation != null) {
            try {
                String response = confirmation.toJSONObject().toString()
                        + "\n\n Payment Id : " + confirmation.getProofOfPayment().getPaymentId();

                paypalOrderId = confirmation.getProofOfPayment().getPaymentId();
                tvPaymentResponse.setText(response);

                Log.d("asd", paypalOrderId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void parseRefundResponse(APIRefund apiRefund) {
        if (apiRefund != null) {
            try {

                tvRefundResponse.setText("Refunded");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
