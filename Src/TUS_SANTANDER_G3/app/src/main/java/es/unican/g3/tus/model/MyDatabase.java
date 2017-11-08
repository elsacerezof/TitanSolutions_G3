package es.unican.g3.tus.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Base de datos
 */

public class MyDatabase extends SQLiteOpenHelper {
    
    // Version
    private static final int VERSION_BASEDATOS = 1;
    // Nombre de nuestro archivo de base de datos
    private static final String NOMBRE_BASEDATOS = "database_g3.db";

    private static final String TABLA_PARADAS = "CREATE TABLE paradas" +
            "(_id INT PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, ALIAS TEXT, NOTAS TEXT, NUMERO INT, IDENTIFICADOR INT)";

    private static final String TABLA_LINEAS = "CREATE TABLE lineas" +
            "(_id INT PRIMARY KEY AUTOINCREMENT, NUMERO TEXT, NOMBRE TEXT, IDENTIFICADOR INT)";

    private static final String NOMBRE ="NOMBRE";
    private static final String ALIAS ="ALIAS";
    private static final String NOTAS = "NOTAS";
    private static final String NUMERO ="NUMERO";
    private static final String IDENTIFICADOR ="IDENTIFICADOR";
    private static final String NOMBRE_PARADAS ="paradas";
    private static final String NOMBRE_LINEAS ="lineas";

    
    public MyDatabase(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_PARADAS);
        sqLiteDatabase.execSQL(TABLA_LINEAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLA_PARADAS+ "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLA_LINEAS+ "'");
        onCreate(sqLiteDatabase);
    }

    public void insertarParada(String nombre, String alias, String notas, int numero, int codigo){
        SQLiteDatabase db =getWritableDatabase();
        if(db != null){
            ContentValues valores = new ContentValues();
            valores.put(MyDatabase.NOMBRE, nombre);
            valores.put(MyDatabase.ALIAS, alias);
            valores.put(MyDatabase.NOTAS, notas);
            valores.put(MyDatabase.NUMERO, numero);
            valores.put(MyDatabase.IDENTIFICADOR, codigo);
            db.insert(NOMBRE_PARADAS, null, valores);
            db.close();
        }
    }

    public void insertarLinea(String numero, String nombre,  int identificador){
        SQLiteDatabase db =getWritableDatabase();
        if(db != null){
            ContentValues valores = new ContentValues();
            valores.put(MyDatabase.NUMERO, numero);
            valores.put(MyDatabase.NOMBRE, nombre);
            valores.put(MyDatabase.IDENTIFICADOR, identificador);
            db.insert(NOMBRE_LINEAS, null, valores);
            db.close();
        }
    }

    public void modificarLinea(String numero, String nombre,  int identificador){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(MyDatabase.NUMERO, numero);
        valores.put(MyDatabase.NOMBRE, nombre);
        valores.put(MyDatabase.IDENTIFICADOR, identificador);
        db.insert(MyDatabase.NOMBRE_LINEAS, null, valores);
        db.close();
    }

    public void modificarParada(String nombre, String alias, String notas, int numero, int codigo){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(MyDatabase.NOMBRE, nombre);
        valores.put(MyDatabase.ALIAS, alias);
        valores.put(MyDatabase.NOTAS, notas);
        valores.put(MyDatabase.NUMERO, numero);
        valores.put(MyDatabase.IDENTIFICADOR, codigo);
        db.insert(MyDatabase.NOMBRE_PARADAS, null, valores);
        db.close();
    }

    public void borrarParada(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(MyDatabase.NOMBRE_PARADAS, MyDatabase.IDENTIFICADOR +id, null);
        db.close();
    }

    public void borrarLinea(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(MyDatabase.NOMBRE_LINEAS, MyDatabase.IDENTIFICADOR +id, null);
        db.close();
    }

    public Parada recuperarParada(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valoresRecuperar = {"_id", MyDatabase.NOMBRE, MyDatabase.ALIAS, MyDatabase.NOTAS, MyDatabase.NUMERO, MyDatabase.IDENTIFICADOR};
        Cursor c = db.query(MyDatabase.NOMBRE_PARADAS, valoresRecuperar, MyDatabase.IDENTIFICADOR + id,
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
            Parada parada = new Parada(c.getString(1), c.getString(4), c.getInt(5));
            db.close();
            c.close();
            return parada;
        }else{
            return null;
        }
    }

    public Linea recuperarLineas(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valoresRecuperar = {"_id", MyDatabase.NUMERO, MyDatabase.NOMBRE, MyDatabase.IDENTIFICADOR};
        Cursor c= db.query(MyDatabase.NOMBRE_LINEAS, valoresRecuperar, MyDatabase.IDENTIFICADOR + id,
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
            Linea linea = new Linea(c.getString(1), c.getString(2), c.getInt(3));
            db.close();
            c.close();
            return linea;
        }else{
            return null;
        }
    }

    public List<Parada> recuperarParadas() {
        SQLiteDatabase db = getReadableDatabase();
        List<Parada> listaParadas = new ArrayList<>();
        String[] valoresRecuperar = {"_id", MyDatabase.NOMBRE, MyDatabase.ALIAS, MyDatabase.NOTAS, MyDatabase.NUMERO, MyDatabase.IDENTIFICADOR};
        Cursor c = db.query(MyDatabase.NOMBRE_PARADAS, valoresRecuperar, null,
                null, null, null, null, null);
        c.moveToFirst();
        do {
            Parada parada = new Parada(c.getString(1), c.getString(4), c.getInt(5));
            listaParadas.add(parada);
        } while (c.moveToNext());
        db.close();
        c.close();
        return listaParadas;
    }

    public List<Linea> recuperarLineas() {
        SQLiteDatabase db = getReadableDatabase();
        List<Linea> listaLineas = new ArrayList<>();
        String[] valoresRecuperar = {"_id", MyDatabase.NUMERO, MyDatabase.NOMBRE, MyDatabase.IDENTIFICADOR};
        Cursor c = db.query(MyDatabase.NOMBRE_LINEAS, valoresRecuperar, null,
                null, null, null, null, null);
        c.moveToFirst();
        do {
            Linea linea = new Linea(c.getString(1), c.getString(2), c.getInt(3));
            listaLineas.add(linea);
        } while (c.moveToNext());
        db.close();
        c.close();
        return listaLineas;
    }
}