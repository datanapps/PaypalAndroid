
package datanapps.paypal.refund.models.refund;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefundFromTransactionFee implements Parcelable {

    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("value")
    @Expose
    private String value;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.currency);
        dest.writeString(this.value);
    }

    public RefundFromTransactionFee() {
    }

    protected RefundFromTransactionFee(Parcel in) {
        this.currency = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<RefundFromTransactionFee> CREATOR = new Parcelable.Creator<RefundFromTransactionFee>() {
        @Override
        public RefundFromTransactionFee createFromParcel(Parcel source) {
            return new RefundFromTransactionFee(source);
        }

        @Override
        public RefundFromTransactionFee[] newArray(int size) {
            return new RefundFromTransactionFee[size];
        }
    };
}
