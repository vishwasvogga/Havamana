package com.vpkarise.havamana.network;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * To manage all the disposables and dump them at once
 */
public class HDisposableManager {

    private static CompositeDisposable compositeDisposable;

    /**
     * Add the disposable to manager
     * @param disposable Disposable
     */
    public static void add(Disposable disposable) {
        getCompositeDisposable().add(disposable);
    }

    /**
     * Dispose all the disposables at once
     */
    public static void dispose() {
        getCompositeDisposable().dispose();
    }

    /**
     * Get the composite disposables
     * @return CompositeDisposable
     */
    private static CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    private HDisposableManager() {}
}
