package br.com.ereceitas;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import br.com.ereceitas.R;
import br.com.ereceitas.dao.TipoReceitaDao;
import br.com.ereceitas.model.TipoReceita;


public class EditarTipoReceitaActivity extends Activity {
	
	EditText nomeEditar;
	EditText descricaoEditar;
	CheckBox despesaEditar;
	Button salvarTipoReceitaEditar;
	Button excluirTipoReceita;
	TipoReceita tipoReceita;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_tipo_receita);
		
		int posicao = getIntent().getExtras().getInt("posicao");
		TipoReceitaDao tipoReceitaDao = TipoReceitaDao.getInstance(getApplicationContext());
		tipoReceita = tipoReceitaDao.recuperarPelaPosicao(posicao);
		
		nomeEditar = (EditText) findViewById(R.id.nomeEditar);
		descricaoEditar = (EditText) findViewById(R.id.descricaoEditar);
		salvarTipoReceitaEditar = (Button) findViewById(R.id.salvarTipoReceitaEditar);
		excluirTipoReceita = (Button) findViewById(R.id.excluirTipoReceita);
		
		nomeEditar.setText(tipoReceita.getNome());
		descricaoEditar.setText(tipoReceita.getDescricao());
		
		salvarTipoReceitaEditar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				tipoReceita.setNome(nomeEditar.getText().toString());
				tipoReceita.setDescricao(descricaoEditar.getText().toString());
				try{					
					TipoReceitaDao tipoReceitaDao = TipoReceitaDao.getInstance(getApplicationContext());
					tipoReceitaDao.editar(tipoReceita);
				}catch (Exception e) {
				}
				Toast.makeText(EditarTipoReceitaActivity.this, "Tipo de Receita salvo", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		excluirTipoReceita.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try{			
					TipoReceitaDao tipoReceitaDao = TipoReceitaDao.getInstance(getApplicationContext());
					tipoReceitaDao.deletar(tipoReceita);
				}catch (Exception e) {
					// TODO: handle exception
				}
				Toast.makeText(EditarTipoReceitaActivity.this, "Tipo de Receita exclu�da!", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editar_tipo_conta, menu);
		return true;
	}

}
