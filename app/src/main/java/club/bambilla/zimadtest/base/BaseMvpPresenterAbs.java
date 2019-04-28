package club.bambilla.zimadtest.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class BaseMvpPresenterAbs<V extends BaseContract.View>
        implements BaseContract.Presenter<V> {
    protected V view;

    @Override
    public void setView(V view) {
        this.view = view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstance) {

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

    @Override
    public void onSaveInstance(@NonNull Bundle outState) {

    }
}
