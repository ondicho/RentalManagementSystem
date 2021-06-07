package payment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface C2BApi {

    @GET("oauth/v1/generate?grant_type=client_credentials")
    Call<AccessToken> getAccessToken();


    @POST("mpesa/stkpush/v1/processrequest")
    Call<STKPush> getMPesa(@Body STKPush STKPush);

}
