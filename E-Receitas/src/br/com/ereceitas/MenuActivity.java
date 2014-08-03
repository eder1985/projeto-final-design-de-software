package br.com.ereceitas;

import br.com.ereceitas.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MenuActivity extends Activity {
	
	ImageButton cadastrarTipoReceita;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
			
		cadastrarTipoReceita = (ImageButton) findViewById(R.id.imageButton2);
		cadastrarTipoReceita.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cadastrarTipoReceitaIntent = new Intent(MenuActivity.this,TipoReceitaActivity.class);
				startActivity(cadastrarTipoReceitaIntent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.menu, menu);*/
		return true;
	}

}
