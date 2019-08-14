package com.example.firepizzaapp.modules.login.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firepizzaapp.repository.FirebaseRepository;

import io.reactivex.disposables.CompositeDisposable;

public class LoginViewModel extends AndroidViewModel {

    MutableLiveData<Boolean> autenticadorLiveData = new MutableLiveData<>();
    CompositeDisposable disposable = new CompositeDisposable();
    FirebaseRepository repository = new FirebaseRepository();


    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void autenticarUsuario(String email , String senha){
        
    }

}
