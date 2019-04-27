package club.bambilla.zimadtest.business;

import android.support.annotation.NonNull;

import club.bambilla.zimadtest.data.Api;
import club.bambilla.zimadtest.data.ApiMapper;
import club.bambilla.zimadtest.data.response.ListQueryResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListInteractorImpl implements ListInteractor {
    private Api api;
    private ApiMapper apiMapper;
    private ListResponseMapper listResponseMapper;

    private Call<ListQueryResponse> call;

    public ListInteractorImpl(Api api,
                              ApiMapper apiMapper,
                              ListResponseMapper listResponseMapper) {
        this.api = api;
        this.apiMapper = apiMapper;
        this.listResponseMapper = listResponseMapper;
    }

    @Override
    public void queryItems(ListType listType, CallBackListener callBackListener) {
        String query;
        switch (listType) {
            case CATS: {
                query = "cat";
                break;
            }
            case DOGS: {
                query = "dog";
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown list type: " + listType);
            }
        }

        call = api.findItems(query);
        call.enqueue(new Callback<ListQueryResponse>() {
            @Override
            public void onResponse(Call<ListQueryResponse> call, Response<ListQueryResponse> response) {
                try {
                    ListQueryResponse listQueryResponse = apiMapper.mapResponse(response);

                    callBackListener.onSuccess(listResponseMapper.mapData(listQueryResponse));
                } catch (Exception e) {
                    e.printStackTrace();
                    callBackListener.onError(e);
                }
            }

            @Override
            public void onFailure(Call<ListQueryResponse> call, Throwable t) {
                callBackListener.onError(t);
            }
        });
    }

    @Override
    public void cancel() {
        if (call != null) {
            call.cancel();
        }
    }
}
