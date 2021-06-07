package payment;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static Constants.Constants.DARAJA_CONSUMER_KEY;
import static Constants.Constants.MPESA_C2B_SIMULATE_URL;

public class C2BClient {
    private static Retrofit retrofit = null;

    public static C2BApi getAmount(){
        if(retrofit==null){
            OkHttpClient okHttpClient=new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public @NotNull Response intercept(@NotNull Chain chain) throws IOException {
                            Request newRequest  = chain.request().newBuilder()
                                    .addHeader("Authorization", DARAJA_CONSUMER_KEY)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    }).build();

            retrofit=new Retrofit.Builder()
                    .baseUrl(MPESA_C2B_SIMULATE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit.create(C2BApi.class);
    }
//    private static  C2BApi C2BApi=null;
//    private static STKPush STKPush=null;
//
//    public static STKPush getApi(String SANDBOX_BASE_URL,String DARAJA_CONSUMER_KEY) throws IOException {
//        if(STKPush == null){
//            OkHttpClient client = new OkHttpClient();
//
//            MediaType mediaType = MediaType.parse("application/json");
//            RequestBody body = RequestBody.create(mediaType,
////                    "{\"ShortCode\":\" \",
////                    \"CommandID\":\"CustomerPayBillOnline\",
////                    \"Amount\":\" \",
////                    \"Msisdn\":\" \",
////                    \"BillRefNumber\":\" \" }"
//            );
//            Request request = new Request.Builder()
//                    .url("https://sandbox.safaricom.co.ke/mpesa/c2b/v1/simulate")
//                    .post(body)
//                    .addHeader("authorization", "Bearer ACCESS_TOKEN")
//                    .addHeader("content-type", "application/json")
//                    .build();
//
//            Response response = client.newCall(request).execute();
//
//        }
//        return STKPush;

}


