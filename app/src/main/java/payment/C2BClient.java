package payment;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;

public class C2BClient {
    private static Retrofit retrofit = null;
    private static  C2BApi C2BApi=null;
    private static STKPush STKPush=null;

    public static STKPush getApi(String SANDBOX_BASE_URL,String DARAJA_CONSUMER_KEY) throws IOException {
        if(STKPush == null){
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType,
//                    "{\"ShortCode\":\" \",
//                    \"CommandID\":\"CustomerPayBillOnline\",
//                    \"Amount\":\" \",
//                    \"Msisdn\":\" \",
//                    \"BillRefNumber\":\" \" }"
            );
            Request request = new Request.Builder()
                    .url("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/simulate")
                    .post(body)
                    .addHeader("authorization", "Bearer ACCESS_TOKEN")
                    .addHeader("content-type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();

        }
        return STKPush;
    }

}
