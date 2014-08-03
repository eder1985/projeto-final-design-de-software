package br.com.ereceitas;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import br.com.ereceitas.R;
import br.com.ereceitas.dao.TipoReceitaDao;
import br.com.ereceitas.model.TipoReceita;


public class TipoReceitaActivity extends Activity {

	Button cadastrarTipoReceita;
	ListView listarTipoReceita;
	 ArrayAdapter<String> listAdapter;
	ArrayList<String> tipoReceitasString = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tipo_receita);
		
		cadastrarTipoReceita = (Button) findViewById(R.id.cadastrarTipoReceita);
		cadastrarTipoReceita.setOnClickListener(new OnClickListener() {
			
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent cadastrarTipoReceita = new Intent(TipoReceitaActivity.this, CadastrarTipoReceitaActivity.class);
			startActivity(cadastrarTipoReceita);
		}
		});
		
		//Listar todos os tipos de receitas
		listarTipoReceitas();
		
		 
	    listarTipoReceita.setOnItemClickListener(new OnItemClickListener() {

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

	private void listarTipoReceitas() {
		TipoReceitaDao tipoReceitaDao = TipoReceitaDao.getInstance(getApplicationContext());
		List<TipoReceita> tipoReceitas = new ArrayList<TipoReceita>();
		tipoReceitasString.clear();
		
		try {
			tipoReceitas = tipoReceitaDao.recuperarTodos();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(!tipoReceitas.isEmpty()){
			for (TipoReceita tipoReceita : tipoReceitas) {
				tipoReceitasString.add(tipoReceita.getNome());
			}
		}
		
		listarTipoReceita = (ListView) findViewById(R.id.listaTipoReceita);
		listAdapter = new ArrayAdapter<String>(TipoReceitaActivity.this,
                android.R.layout.simple_list_item_1, tipoReceitasString);
		listarTipoReceita.setAdapter(listAdapter);
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
