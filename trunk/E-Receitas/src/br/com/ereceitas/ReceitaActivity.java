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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.ereceitas.dao.TipoReceitaDao;
import br.com.ereceitas.model.Receita;
import br.com.ereceitas.model.TipoReceita;

public class ReceitaActivity extends Activity {

	private String[] tipoReceitaArray;
	private Button button;
	private Spinner tipoReceita;
	private EditText nomeReceita;
	private RadioGroup dificuladeReceita;
	private EditText tempoPreparoReceita;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receita);
		
		// Metodo de preenchimento do tipo conta
		preencherTipoReceita();
		
		tipoReceita = (Spinner) findViewById(R.id.tipoReceita);
		nomeReceita = (EditText) findViewById(R.id.nome);
		dificuladeReceita = (RadioGroup) findViewById(R.id.radioDificuladade);
		tempoPreparoReceita = (EditText) findViewById(R.id.tempoPreparo);
		
		button = (Button) findViewById(R.id.buttonIngredientes);
		button.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Receita receita = new Receita();
				String nome = nomeReceita.getText().toString();
				String tempoPreparo = tempoPreparoReceita.toString();
				
				if(nome.trim().equals("") || nome == null){
					Toast.makeText(ReceitaActivity.this, "O campo nome precisa ser preenchido!",Toast.LENGTH_LONG).show(); 
				}else if(tempoPreparo.trim().equals("") || tempoPreparo == null){
					Toast.makeText(ReceitaActivity.this, "O campo tempo de preparo precisa ser preenchido!", Toast.LENGTH_LONG).show();
				}else{
					TipoReceitaDao tipoDao = TipoReceitaDao.getInstance(getApplicationContext());
					receita.setTipo(tipoDao.findByDescricao(tipoReceita.getSelectedItem().toString()));
					receita.setNome(nomeReceita.getText().toString());
					switch (dificuladeReceita.getCheckedRadioButtonId()) {
					case R.id.inic:
						receita.setDificuldade("INICIANTE");
						break;
					case R.id.med:
						receita.setDificuldade("MEDIA");
						break;
					case R.id.chef:
						receita.setDificuldade("CHEFE");
						break;
					}
					
					/*Intent intent = new Intent(ReceitaActivity.this, IngredientesActivity.class);

					Bundle bundle = new Bundle();
					bundle.putSerializable("receita",receita);

					intent.putExtras(bundle);

					startActivity(intent);*/
			}
		  }
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.receita, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void preencherTipoReceita() {
		TipoReceitaDao tipoReceitaDao = TipoReceitaDao
				.getInstance(getApplicationContext());
		List<TipoReceita> tipoReceitas = new ArrayList<TipoReceita>();

		try {
			tipoReceitas = tipoReceitaDao.recuperarTodos();
			tipoReceitaArray = new String[tipoReceitas.size()];
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (!tipoReceitas.isEmpty()) {
			int index = 0;
			for (TipoReceita tipoReceita : tipoReceitas) {
				tipoReceitaArray[index] = tipoReceita.getNome();
				index++;
			}
		}

		final Spinner combo = (Spinner) findViewById(R.id.tipoReceita);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, tipoReceitaArray);

		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
		combo.setAdapter(adaptador);
	}
}
