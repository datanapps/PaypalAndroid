package datanapps.paypal.config;

public enum Currency {

    USD("USD"),
    INR("INR");

    private String currency;

    Currency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }


}
