package club.bambilla.zimadtest.tabs.details;

import club.bambilla.zimadtest.base.BaseContract;
import club.bambilla.zimadtest.models.ListItem;

public interface DetailsContract {
    interface View extends BaseContract.View{

        void showContent(ListItem listItem, int position);
    }

    interface Presenter extends BaseContract.Presenter<View>{

    }
}
