package payment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface C2BApi {

    @GET("oauth/v1/generate?grant_type=client_credentials")
    Call<AccessToken> getAccessToken();

    @GET("Lipa_na_mpesa/amount")
    Call<STKPush> getAmount(
            @Query("Amount") String Amount
    );


    @POST("mpesa/stkpush/v1/processrequest")
    Call<STKPush> getMPesa(@Body STKPush STKPush);

}
