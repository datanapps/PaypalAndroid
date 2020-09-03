package datanapps.paypal.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import datanapps.paypal.config.PaypalConfig;
import datanapps.paypal.R;
import datanapps.paypal.refund.PayPalImplementation;


import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;


public class PayPalActivity extends AppCompatActivity {


    private static final int PAYPAL_REQUEST_CODE = 7777;

    private EditText edtAmount;
    private String paypalOrderId="PAY-55F4551110955092RL4O3YKA";
    private TextView tvResponse;
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
        edtAmount = findViewById(R.id.edtAmount);
        tvResponse = findViewById(R.id.tvRespnse);

        findViewById(R.id.btnPayNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payPalImplementation.processPayment(0.1f, "Item Order", PAYPAL_REQUEST_CODE);
            }
        });

        findViewById(R.id.btnRefund).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                payPalImplementation.callTransactionDetailAndRefund(paypalOrderId);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                parseServerResponse(confirmation);
            } else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
    }


    private void parseServerResponse(PaymentConfirmation confirmation) {
        if (confirmation != null) {
            try {
                String response = confirmation.toJSONObject().toString()
                        + "\n\n Payment Id : " + confirmation.getProofOfPayment().getPaymentId();

                paypalOrderId = confirmation.getProofOfPayment().getPaymentId();
                tvResponse.setText(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
