package irul.com.trainingmoklet.restfull;

import irul.com.trainingmoklet.model.Meals;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
        //url non statis
        @GET("1/latest.php")
        Call<Meals> getData();
}
