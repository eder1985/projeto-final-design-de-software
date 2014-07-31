package com.example.e_receitas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


@SuppressLint("ShowToast")
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Chamando os objetos
        final EditText login = (EditText) findViewById(R.id.login);
        final EditText senha = (EditText) findViewById(R.id.senha);
        Button entrar = (Button) findViewById(R.id.entrar);
        Button limpar = (Button) findViewById(R.id.limpar);
        
        entrar.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String logincerto = "demo";
				String senhacerta = "demo";
				String strlogin = login.getText().toString();
				String strSenha = senha.getText().toString();
				
				if (strlogin.equals(logincerto) && strSenha.equals(senhacerta) ){
					Toast.makeText(MainActivity.this, "Login com sucesso!", Toast.LENGTH_SHORT);
				}else {
					Toast.makeText(MainActivity.this, "Erro! Login ou senha estão incorretos!", Toast.LENGTH_SHORT);
				}
			}
		});
        
        limpar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login.setText("");
				senha.setText("");
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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