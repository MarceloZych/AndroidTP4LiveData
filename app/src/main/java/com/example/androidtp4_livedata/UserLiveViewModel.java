package com.example.androidtp4_livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserLiveViewModel extends ViewModel {

    private MutableLiveData<List<Usuario>> listaUsuarioLiveData;
    private List<Usuario> listUsuarios;

    public LiveData<List<Usuario>> getUserList() {
        if (listaUsuarioLiveData == null) {
            listaUsuarioLiveData = new MutableLiveData<>();
            listUsuarios = new ArrayList<>();
        }

        return listaUsuarioLiveData;
    }

    public void addUser(Usuario user) {
        listUsuarios.add(user);
        listaUsuarioLiveData.setValue(listUsuarios);
    }
}