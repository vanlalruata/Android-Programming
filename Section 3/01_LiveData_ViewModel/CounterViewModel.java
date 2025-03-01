package com.mzu.livedataviewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    private final MutableLiveData<Integer> counter = new MutableLiveData<>(0);

    public LiveData<Integer> getCounter() {
        return counter;
    }

    public void increment() {
        counter.setValue(counter.getValue() + 1);
    }

    public void decrement() {
        counter.setValue(counter.getValue() - 1);
    }
}
