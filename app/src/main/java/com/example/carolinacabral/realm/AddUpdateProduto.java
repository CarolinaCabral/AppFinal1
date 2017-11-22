package com.example.carolinacabral.realm;

import android.content.Intent;
import android.icu.text.SelectFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carolinacabral.realm.Adapter.ProdutoAdapterPago;
import com.example.carolinacabral.realm.Domain.Produto;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddUpdateProduto extends AppCompatActivity {

    private Realm realm;
    private RealmResults<Produto> produtos;

    private Produto produto;
    private EditText cliente;
    private EditText descricao;
    private EditText quantidade;
    private EditText preco;
    private Spinner pagamentos;

    private Button adicionar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_produto);





        final String[] pagamento = {"Aberto", "Pago"};
        final Spinner spnPagamento = (Spinner) findViewById(R.id.spinnerCategoria);
        final ArrayAdapter<String> adapterPagamento = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pagamento);
        adapterPagamento.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnPagamento.setAdapter(adapterPagamento);


        produto = new Produto();
        cliente = (EditText) findViewById(R.id.edtCliente);
        descricao = (EditText) findViewById(R.id.editTextDescricao);
        quantidade = (EditText) findViewById(R.id.editTextQuantidade);
        preco = (EditText) findViewById(R.id.edtPreco);
        pagamentos = (Spinner) findViewById(R.id.spinnerCategoria);
        adicionar = (Button) findViewById(R.id.buttonAdicionat);


        realm = Realm.getInstance(this);
        produtos = realm.where(Produto.class).findAll();

        if (getIntent() != null && getIntent().getIntExtra(Produto.ID, 0) > 0) {
            produto.setId((getIntent().getIntExtra(Produto.ID, 0)));
            produto = produtos.where().equalTo("id", produto.getId()).findAll().get((0));
            cliente.setText(produto.getCliente());
            descricao.setText(produto.getDescricao());
            quantidade.setText(String.valueOf(produto.getQuantidade()));
            preco.setText(String.valueOf(produto.getPreco()));

            //categorias.getSelectedItem().toString();

            adicionar.setText("Atualizar");


        }

    }
    public void callAddUpdateProduto(View view)
    {

                if (produto.getId()==0)
                {
                    produtos.sort("id", RealmResults.SORT_ORDER_DESCENDING);
                    int id = produtos.size() == 0 ? 1 : produtos.get(0).getId()+1;
                    produto.setId(id);

                }
                try{
                    if(pagamentos.getSelectedItem() == "Aberto") {
                        realm.beginTransaction();
                        produto.setCliente(cliente.getText().toString());
                        produto.setDescricao(descricao.getText().toString());
                        produto.setPreco(Integer.parseInt(preco.getText().toString()));
                        produto.setQuantidade(Integer.parseInt(quantidade.getText().toString()));
                        produto.setPagamento(pagamentos.getSelectedItem().toString());
                        realm.copyToRealmOrUpdate(produto);
                        realm.commitTransaction();
                        Toast.makeText(AddUpdateProduto.this, "Produto adicionado", Toast.LENGTH_SHORT).show();
                    }
                    if(pagamentos.getSelectedItem() == "Pago")
                    {


                       Realm realm = Realm.getInstance(this);
                        realm.beginTransaction();
                        produto.removeFromRealm();
                        realm.commitTransaction();
                        Toast toast = Toast.makeText(this,"Produto pago",Toast.LENGTH_SHORT);
                        toast.show();
                        realm.close();


                    }


                }catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(AddUpdateProduto.this,"Erro",Toast.LENGTH_SHORT).show();

                }
            }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}



