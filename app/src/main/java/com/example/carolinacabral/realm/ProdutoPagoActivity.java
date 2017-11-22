package com.example.carolinacabral.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.carolinacabral.realm.Adapter.ProdutoAdapterPago;
import com.example.carolinacabral.realm.Domain.Produto;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ProdutoPagoActivity extends AppCompatActivity {

    private Realm realm;
    private RealmResults<Produto> produtos;
    private RealmChangeListener realmChangeListener;
    private ListView lstProdutoPago;

    @Override
    protected void onDestroy() {
        realm.removeAllChangeListeners();
        realm.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_pago);

        realm = Realm.getInstance(this);
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange() {
                ((ProdutoAdapterPago) lstProdutoPago.getAdapter()).notify();

            }
        };

        realm.addChangeListener(realmChangeListener);
        produtos = realm.where(Produto.class).findAll();

        lstProdutoPago = (ListView) findViewById(R.id.listIdContasPagas);
        lstProdutoPago.setAdapter(new ProdutoAdapterPago(this,produtos,false));







    }
    }

