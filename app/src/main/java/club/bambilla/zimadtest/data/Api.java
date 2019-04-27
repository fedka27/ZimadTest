package club.bambilla.zimadtest.data;

import android.support.annotation.NonNull;

import club.bambilla.zimadtest.data.response.ListQueryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "http://kot3.com/";

    @GET("xim/api.php")
    Call<ListQueryResponse> findItems(@Query("query") @NonNull String query);
}
