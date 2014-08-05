package br.com.ereceitas;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import br.com.ereceitas.dao.TipoReceitaDao;
import br.com.ereceitas.model.TipoReceita;


public class TipoReceitaActivity extends Activity {

	EditText nome;
	EditText descricao;
	Button cadastrar;
	Button editar;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tipo_receita);
		
		listView = (ListView) findViewById(R.id.listagemTipoReceita);
		exibirLista();
		
		nome = (EditText) findViewById(R.id.nome);
		descricao = (EditText) findViewById(R.id.descriao);
		
		cadastrar = (Button) findViewById(R.id.cadastrarTipoReceita);
		cadastrar.setOnClickListener(new OnClickListener() {
	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String nomeTipoReceita = nome.getText().toString();
			String descricaoTipoReceita = descricao.getText().toString();
			
			if(nomeTipoReceita.trim().equals("") || nomeTipoReceita == null){
				Toast.makeText(TipoReceitaActivity.this, "O campo nome precisa ser preenchido", 
						Toast.LENGTH_LONG).show();
			}else if (descricaoTipoReceita.trim().equals("") || descricaoTipoReceita == null){
				Toast.makeText(TipoReceitaActivity.this, "O campo descrição precisa ser preenchido", 
						Toast.LENGTH_LONG).show();
			}else{
				TipoReceita tipoReceita = new TipoReceita();
				tipoReceita.setNome(nomeTipoReceita);
				tipoReceita.setDescricao(descricaoTipoReceita);
				
				TipoReceitaDao dao = TipoReceitaDao.getInstance(getApplicationContext());
				
				try {
					dao.salvar(tipoReceita);
					nome.setText("");
					descricao.setText("");
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				Toast.makeText(TipoReceitaActivity.this, "Tipo de Receita Cadastrada!", Toast.LENGTH_LONG).show();
				//listarTipoReceitas();
				exibirLista();
			}
		  }
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

	           @Override
	           public void onItemClick(AdapterView<?> arg0, View arg1,
	                   int position, long arg3) {
	           	//enviar posicao do tipo de conta que foi selecionado para a activity editarExcluir
	              Intent editarTipoReceitaIntent = new Intent(TipoReceitaActivity.this,EditarTipoReceitaActivity.class);
	              editarTipoReceitaIntent.putExtra("posicao", position);
	              startActivity(editarTipoReceitaIntent);
	           }
	       });
	}

	
	public void exibirLista() {
		try {
			String[] from = new String[] { "codigo", "nome" };
			int[] to = new int[] { R.id.listCod, R.id.listNome, };

			TipoReceitaDao tipoReceitaDao = TipoReceitaDao.getInstance(getApplicationContext());
			List<TipoReceita> listaTipos = new ArrayList<TipoReceita>();
			listaTipos = tipoReceitaDao.recuperarTodos();
			Log.i("LISTA 2", listaTipos.size() + "");

			List<HashMap<String, String>> mapItensLista = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < listaTipos.size(); i++) {
				HashMap<String, String> mapItens = new HashMap<String, String>();
				
				mapItens.put("codigo","" + listaTipos.get(i).getId());
				mapItens.put("nome", listaTipos.get(i).getNome() );

				mapItensLista.add(mapItens);
			}
			
			SimpleAdapter adapter = new SimpleAdapter(this, mapItensLista,
					R.layout.item_listview, from, to);
			listView.setAdapter(adapter);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tipo_receita, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
