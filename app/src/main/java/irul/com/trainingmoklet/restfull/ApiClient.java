package irul.com.trainingmoklet.restfull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //BASE URL DARI NAMA SITUS/DOMAIN
    //URL statis
    public static final String BASE_URL = "https://www.themealdb.com/api/json/v1/";
    public static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
