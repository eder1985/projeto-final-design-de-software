package br.com.ereceitas.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.ereceitas.helpers.DataBaseHelper;
import br.com.ereceitas.model.TipoReceita;

public class TipoReceitaDao {
	
	public static final String NOME_TABELA = "tipo_receita";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_DESCRICAO = "descricao";
 
    public static final String SCRIPT_CRIACAO_TABELA_TIPO_RECEITA = "CREATE TABLE " + NOME_TABELA + "("
            + COLUNA_ID + " INTEGER PRIMARY KEY ," + COLUNA_NOME + " TEXT,"+ COLUNA_DESCRICAO + " TEXT" + ");";
 
    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;
 
 
    private SQLiteDatabase dataBase = null;
 
 
    private static TipoReceitaDao instance;
     
    public static TipoReceitaDao getInstance(Context context) {
        if(instance == null)
            instance = new TipoReceitaDao(context);
        return instance;
    }
 
    private TipoReceitaDao(Context context) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance(context);
        dataBase = dataBaseHelper.getWritableDatabase();
    }
 
    public void salvar(TipoReceita tipoReceita) {
        ContentValues values = gerarContentValeuesTipoReceita(tipoReceita);
        dataBase.insert(NOME_TABELA, null, values);
    }
 
    public List<TipoReceita> recuperarTodos() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<TipoReceita> tipoReceitas = construirTipoReceitaPorCursor(cursor);
 
        return tipoReceitas;
    }
    
    public TipoReceita recuperarPelaPosicao(int posicao) {
    	  String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
          Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
          List<TipoReceita> tipoReceitas = construirTipoReceitaPorCursor(cursor);
   
          return tipoReceitas.get(posicao);
    }
 
    public TipoReceita recuperarPeloId(int id) {
  	  String queryReturnAll = "SELECT * FROM " + NOME_TABELA + " WHERE ID = " + id;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<TipoReceita> tipoReceitas = construirTipoReceitaPorCursor(cursor);
        if(!tipoReceitas.isEmpty()){        	
        	return tipoReceitas.get(0);
        }else{
        	return new TipoReceita();
        }
  }
    
    public void deletar(TipoReceita TipoReceita) {
 
        String[] valoresParaSubstituir = {
                String.valueOf(TipoReceita.getId())
        };
 
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }
 
    public void editar(TipoReceita TipoReceita) {
        ContentValues valores = gerarContentValeuesTipoReceita(TipoReceita);
 
        String[] valoresParaSubstituir = {
                String.valueOf(TipoReceita.getId())
        };
 
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }
 
    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close(); 
    }
 
 
    private List<TipoReceita> construirTipoReceitaPorCursor(Cursor cursor) {
        List<TipoReceita> tipoReceitas = new ArrayList<TipoReceita>();
        if(cursor == null)
            return tipoReceitas;
         
        try {
 
            if (cursor.moveToFirst()) {
                do {
 
                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexNome = cursor.getColumnIndex(COLUNA_NOME);
                    int indexDescricao = cursor.getColumnIndex(COLUNA_DESCRICAO);
 
                    int id = cursor.getInt(indexID);
                    String nome = cursor.getString(indexNome);
                    String descricao = cursor.getString(indexDescricao);
 
                    TipoReceita tipoReceita = new TipoReceita();
                    tipoReceita.setId(id);
                    tipoReceita.setNome(nome);
                    tipoReceita.setDescricao(descricao);
                    tipoReceitas.add(tipoReceita);
 
                } while (cursor.moveToNext());
            }
             
        } finally {
            cursor.close();
        }
        return tipoReceitas;
    }
 
    private ContentValues gerarContentValeuesTipoReceita(TipoReceita TipoReceita) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, TipoReceita.getNome());
        values.put(COLUNA_DESCRICAO, TipoReceita.getDescricao());
 
        return values;
    }
    
    public TipoReceita findByDescricao(String descricao){
    	String queryReturnAll = "SELECT * FROM " + NOME_TABELA + " WHERE " + COLUNA_DESCRICAO + " = " + "'" +descricao+ "'";
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<TipoReceita> tipoReceitas = construirTipoReceitaPorCursor(cursor);
        if(!tipoReceitas.isEmpty()){        	
        	return tipoReceitas.get(0);
        }else{
        	return new TipoReceita();
        }
    }
}
