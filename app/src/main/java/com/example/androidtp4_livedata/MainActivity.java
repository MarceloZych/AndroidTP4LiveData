package com.example.androidtp4_livedata;

import java.util.*;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidtp4_livedata.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UserLiveViewModel userLiveViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userLiveViewModel = new ViewModelProvider(this).get(UserLiveViewModel.class);
        tarea();
    }

    public void tarea() {
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = binding.editTextNombre.getText().toString();
                int edad;
                if (!nombre.isEmpty() && !binding.editTextNumber.getText().toString().isEmpty()) {
                    try {
                        edad = Integer.parseInt(binding.editTextNumber.getText().toString());
                        Usuario usuario = new Usuario(nombre, edad);
                        userLiveViewModel.addUser(usuario);
                        binding.editTextNombre.setText("");
                        binding.editTextNumber.setText("");
                    } catch (NumberFormatException e) {
                        binding.textView.setText("ERROR");
                    }
                } else {
                    binding.textView.setText("ERROR");
                }
                binding.editTextNombre.setText("");
                binding.editTextNumber.setText("");

            }
        });
        final Observer<List<Usuario>> listaObservada = new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {
                String texto = "";
                for (Usuario u : usuarios) {
                    texto += "Nombre: "+u.getNombre()+" Edad "+u.getEdad()+"\n";
                }
            binding.textView.setText(texto);
            }
        };
        userLiveViewModel.getUserList().observe(this, listaObservada);
    }
}