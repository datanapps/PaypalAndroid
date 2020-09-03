
package datanapps.paypal.refund.models.transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Payer {

    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("payer_info")
    @Expose
    private PayerInfo payerInfo;
    @SerializedName("funding_instruments")
    @Expose
    private List<FundingInstrument> fundingInstruments = null;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PayerInfo getPayerInfo() {
        return payerInfo;
    }

    public void setPayerInfo(PayerInfo payerInfo) {
        this.payerInfo = payerInfo;
    }

    public List<FundingInstrument> getFundingInstruments() {
        return fundingInstruments;
    }

    public void setFundingInstruments(List<FundingInstrument> fundingInstruments) {
        this.fundingInstruments = fundingInstruments;
    }

}
