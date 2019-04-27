package club.bambilla.zimadtest.base;

public interface BaseContract {
    interface View {

    }

    interface Presenter<T extends View> {
        void setView(T view);

        void onCreate();

        void onStart();

        void onStop();

        void onDestroy();
    }
}
