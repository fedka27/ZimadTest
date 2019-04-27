package club.bambilla.zimadtest.base;

public class BaseMvpPresenterAbs<V extends BaseContract.View>
        implements BaseContract.Presenter<V> {
    protected V view;

    @Override
    public void setView(V view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
