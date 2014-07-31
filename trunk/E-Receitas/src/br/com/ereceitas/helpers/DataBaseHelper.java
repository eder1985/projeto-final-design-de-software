package br.com.ereceitas.helpers;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.ereceitas.dao.TipoReceitaDao;

public class DataBaseHelper extends SQLiteOpenHelper{
	public static final String NOME_BANCO = "ereceitas";
	public static final int VERSAO = 1;

	public static final SimpleDateFormat formatoData = new SimpleDateFormat(
			"dd/MM/yyyy");
	public static final SimpleDateFormat formatoDataHora = new SimpleDateFormat(
			"yyyy/MM/dd");

	private static DataBaseHelper instance;

	private DataBaseHelper(Context context) {
		super(context, NOME_BANCO, null, VERSAO);
	}

	public static DataBaseHelper getInstance(Context context) {
		if (instance == null)
			instance = new DataBaseHelper(context);

		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TipoReceitaDao.SCRIPT_CRIACAO_TABELA_TIPO_RECEITA);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(TipoReceitaDao.SCRIPT_DELECAO_TABELA);
		onCreate(db);
	}

	public Date retornarDatedoBanco(String data) {
		if (data != null) {
			String d[] = data.split("_");
			Date retorno;
			if (d.length == 3) {
				try {
					retorno = new Date(Integer.parseInt(d[0]) - 1900,
							Integer.parseInt(d[1]) - 1, Integer.parseInt(d[2]));
				} catch (Exception dfe) {
					return null;
				}
				return retorno;
			}

		}
		return null;
	}

	public String formatarData(String data) {
		String[] dataTemp = data.split("/");
		data = dataTemp[2] + "-" + dataTemp[1] + "-" + dataTemp[0];
		Log.i("DATA FORMATO", data);
		return data;
	}
}
