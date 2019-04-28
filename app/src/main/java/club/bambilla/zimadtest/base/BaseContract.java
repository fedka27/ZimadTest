package club.bambilla.zimadtest.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface BaseContract {
    interface View {

    }

    interface Presenter<T extends View> {
        void setView(T view);

        void onCreate(@Nullable Bundle savedInstance);

        void onStart();

        void onStop();

        void onDestroy();

        void onSaveInstance(@NonNull Bundle outState);
    }
}
