package club.bambilla.zimadtest.business;

import club.bambilla.zimadtest.data.Api;
import club.bambilla.zimadtest.data.ApiMapper;
import club.bambilla.zimadtest.data.ApiProvider;

public class ListInteractorProvider {
    private static ListInteractor listInteractor;

    public static ListInteractor provideListInteractor() {
        if (listInteractor == null) {
            Api api = ApiProvider.provideApi();
            listInteractor = new ListInteractorImpl(api,
                    new ApiMapper(),
                    new ListResponseMapper());
        }
        return listInteractor;
    }
}
