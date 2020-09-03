package datanapps.paypal;

import retrofit2.Call;

public interface ResponseListener {
    void onSuccess(Object result);
    void onError(Throwable t);
}
