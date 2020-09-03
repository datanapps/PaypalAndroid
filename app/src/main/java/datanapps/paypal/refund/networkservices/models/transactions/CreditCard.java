
package datanapps.paypal.refund.networkservices.models.transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditCard {

    @SerializedName("expire_month")
    @Expose
    private String expireMonth;
    @SerializedName("expire_year")
    @Expose
    private String expireYear;
    @SerializedName("type")
    @Expose
    private String type;

    public String getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(String expireMonth) {
        this.expireMonth = expireMonth;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(String expireYear) {
        this.expireYear = expireYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
