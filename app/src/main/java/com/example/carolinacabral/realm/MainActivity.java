package com.example.carolinacabral.realm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carolinacabral.realm.Adapter.ProdutoAdapter;
import com.example.carolinacabral.realm.Domain.Produto;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private EditText listanome;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.btnLogin);
        final EditText nome = (EditText) findViewById(R.id.nomeMain);
        final EditText email = (EditText) findViewById(R.id.emailMain);
        login.setEnabled(false);

        Button gravar = (Button) findViewById(R.id.btnGravar);
        gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = prefs.edit();

                ed.putString("nome", nome.getText().toString());
                ed.putString("email",email.getText().toString());

                ed.apply();
                Toast.makeText(getBaseContext(), "Gravado com sucesso",Toast.LENGTH_SHORT).show();
                login.setEnabled(true);

            }
        });

        Button limpar = (Button) findViewById(R.id.btnLimpar);
        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome.setText("");
                email.setText("");
            }
        });

        Button recuperar = (Button) findViewById(R.id.btnRecuperar);
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("preferencias",Context.MODE_PRIVATE);
                nome.setText(prefs.getString("nome","Nao encontrado"));
                email.setText(prefs.getString("email","Nao encontrado"));
                login.setEnabled(true);


            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                String txt="";
                txt = nome.getText().toString();
                Bundle bundle = new Bundle();

                bundle.putString("txt",txt);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
