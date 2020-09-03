
package datanapps.paypal.refund.models.refund;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class APIRefund implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("create_time")
    @Expose
    private String createTime;
    @SerializedName("update_time")
    @Expose
    private String updateTime;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("amount")
    @Expose
    private Amount amount;
    @SerializedName("refund_from_transaction_fee")
    @Expose
    private RefundFromTransactionFee refundFromTransactionFee;
    @SerializedName("total_refunded_amount")
    @Expose
    private TotalRefundedAmount totalRefundedAmount;
    @SerializedName("refund_from_received_amount")
    @Expose
    private RefundFromReceivedAmount refundFromReceivedAmount;
    @SerializedName("sale_id")
    @Expose
    private String saleId;
    @SerializedName("parent_payment")
    @Expose
    private String parentPayment;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public RefundFromTransactionFee getRefundFromTransactionFee() {
        return refundFromTransactionFee;
    }

    public void setRefundFromTransactionFee(RefundFromTransactionFee refundFromTransactionFee) {
        this.refundFromTransactionFee = refundFromTransactionFee;
    }

    public TotalRefundedAmount getTotalRefundedAmount() {
        return totalRefundedAmount;
    }

    public void setTotalRefundedAmount(TotalRefundedAmount totalRefundedAmount) {
        this.totalRefundedAmount = totalRefundedAmount;
    }

    public RefundFromReceivedAmount getRefundFromReceivedAmount() {
        return refundFromReceivedAmount;
    }

    public void setRefundFromReceivedAmount(RefundFromReceivedAmount refundFromReceivedAmount) {
        this.refundFromReceivedAmount = refundFromReceivedAmount;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getParentPayment() {
        return parentPayment;
    }

    public void setParentPayment(String parentPayment) {
        this.parentPayment = parentPayment;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.createTime);
        dest.writeString(this.updateTime);
        dest.writeString(this.state);
        dest.writeParcelable(this.amount, flags);
        dest.writeParcelable(this.refundFromTransactionFee, flags);
        dest.writeParcelable(this.totalRefundedAmount, flags);
        dest.writeParcelable(this.refundFromReceivedAmount, flags);
        dest.writeString(this.saleId);
        dest.writeString(this.parentPayment);
        dest.writeList(this.links);
    }

    public APIRefund() {
    }

    protected APIRefund(Parcel in) {
        this.id = in.readString();
        this.createTime = in.readString();
        this.updateTime = in.readString();
        this.state = in.readString();
        this.amount = in.readParcelable(Amount.class.getClassLoader());
        this.refundFromTransactionFee = in.readParcelable(RefundFromTransactionFee.class.getClassLoader());
        this.totalRefundedAmount = in.readParcelable(TotalRefundedAmount.class.getClassLoader());
        this.refundFromReceivedAmount = in.readParcelable(RefundFromReceivedAmount.class.getClassLoader());
        this.saleId = in.readString();
        this.parentPayment = in.readString();
        this.links = new ArrayList<Link>();
        in.readList(this.links, Link.class.getClassLoader());
    }

    public static final Creator<APIRefund> CREATOR = new Creator<APIRefund>() {
        @Override
        public APIRefund createFromParcel(Parcel source) {
            return new APIRefund(source);
        }

        @Override
        public APIRefund[] newArray(int size) {
            return new APIRefund[size];
        }
    };
}
