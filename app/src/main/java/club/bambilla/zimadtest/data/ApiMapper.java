package club.bambilla.zimadtest.data;

import java.util.List;

import club.bambilla.zimadtest.data.exceptions.ApiException;
import club.bambilla.zimadtest.data.response.ListQueryResponse;
import club.bambilla.zimadtest.models.ListItem;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class ApiMapper {

    public <T> T mapResponse(Response<T> response) throws Exception{
        if (!response.isSuccessful()){
            throw new ApiException(response.message());
        }
        return response.body();
    }
}
