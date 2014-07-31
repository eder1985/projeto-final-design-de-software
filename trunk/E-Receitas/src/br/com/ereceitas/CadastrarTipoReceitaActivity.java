package br.com.ereceitas;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.ereceitas.dao.TipoReceitaDao;
import br.com.ereceitas.model.TipoReceita;

import com.example.e_receitas.R;

public class CadastrarTipoReceitaActivity extends Activity {

	EditText nome;
	EditText descricao;
	Button cadastrar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_tipo_receita);
		
		nome = (EditText) findViewById(R.id.nome);
		descricao = (EditText) findViewById(R.id.descricao);
		
		cadastrar = (Button) findViewById(R.id.cadastrarTipoReceita);
		cadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String nomeTipoReceita = nome.getText().toString();
				String descricaoTipoReceita = descricao.getText().toString();
				
				if(nomeTipoReceita.trim().equals("") || nomeTipoReceita == null){
					Toast.makeText(CadastrarTipoReceitaActivity.this, "O campo nome precisa ser preenchido", 
							Toast.LENGTH_LONG).show();
				}else if (descricaoTipoReceita.trim().equals("") || descricaoTipoReceita == null){
					Toast.makeText(CadastrarTipoReceitaActivity.this, "O campo descrição precisa ser preenchido", 
							Toast.LENGTH_LONG).show();
				}else{
					TipoReceita tipoReceita = new TipoReceita();
					tipoReceita.setNome(nomeTipoReceita);
					tipoReceita.setDescricao(descricaoTipoReceita);
					
					TipoReceitaDao dao = TipoReceitaDao.getInstance(getApplicationContext());
					
					try {
						dao.salvar(tipoReceita);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					Toast.makeText(CadastrarTipoReceitaActivity.this, "Tipo de Receita Cadastrado", Toast.LENGTH_LONG).show();
					finish();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_tipo_receita, menu);
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
