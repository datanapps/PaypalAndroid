
package datanapps.paypal.refund.networkservices.models.transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShippingAddress {

    @SerializedName("default_address")
    @Expose
    private Boolean defaultAddress;
    @SerializedName("preferred_address")
    @Expose
    private Boolean preferredAddress;
    @SerializedName("primary_address")
    @Expose
    private Boolean primaryAddress;
    @SerializedName("disable_for_transaction")
    @Expose
    private Boolean disableForTransaction;

    public Boolean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Boolean getPreferredAddress() {
        return preferredAddress;
    }

    public void setPreferredAddress(Boolean preferredAddress) {
        this.preferredAddress = preferredAddress;
    }

    public Boolean getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Boolean primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Boolean getDisableForTransaction() {
        return disableForTransaction;
    }

    public void setDisableForTransaction(Boolean disableForTransaction) {
        this.disableForTransaction = disableForTransaction;
    }

}
