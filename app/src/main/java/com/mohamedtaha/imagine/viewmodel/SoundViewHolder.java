package com.mohamedtaha.imagine.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mohamedtaha.imagine.mvp.model.ImageModel;

import java.util.ArrayList;

public class SoundViewHolder extends AndroidViewModel {
    SoundRepositiory soundRepositiory;
    public SoundViewHolder(@NonNull Application application) {
        super(application);
        soundRepositiory = new SoundRepositiory(application);
    }
    public LiveData<ArrayList<ImageModel>> getAllNameSora(int position){
        return soundRepositiory.getMutableLiveDataNameSora(position);
    }
}
