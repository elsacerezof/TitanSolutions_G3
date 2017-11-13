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

public class Database extends SQLiteOpenHelper {
    
    // Version
    private static final int VERSION_BASEDATOS = 1;

    // Nombre de nuestro archivo de base de datos
    private static final String NOMBRE_BASEDATOS = "database_g3.db";

    // Tablas
    private static final String TABLA_PARADAS = "CREATE TABLE paradas " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, ALIAS TEXT, NOTAS TEXT, NUMERO INT, IDENTIFICADOR INT)";

    private static final String TABLA_LINEAS = "CREATE TABLE lineas " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, NUMERO TEXT, NOMBRE TEXT, IDENTIFICADOR INT)";

    // Campos tabla
    private static final String NOMBRE ="NOMBRE";
    private static final String ALIAS ="ALIAS";
    private static final String NOTAS = "NOTAS";
    private static final String NUMERO ="NUMERO";
    private static final String IDENTIFICADOR ="IDENTIFICADOR";
    private static final String NOMBRE_PARADAS ="paradas";
    private static final String NOMBRE_LINEAS ="lineas";

    
    public Database(Context context) {
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

    public void reiniciar(){
        SQLiteDatabase db = getWritableDatabase();
        if(db != null) {
            db.execSQL("DROP TABLE IF EXISTS '" + NOMBRE_PARADAS + "'");
            db.execSQL("DROP TABLE IF EXISTS '" + NOMBRE_LINEAS + "'");
            db.execSQL(TABLA_PARADAS);
            db.execSQL(TABLA_LINEAS);
            db.close();
        }
    }

    public void insertarParada(String nombre, String alias, String notas, int numero, int codigo){
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            ContentValues valores = new ContentValues();
            valores.put(Database.NOMBRE, nombre);
            valores.put(Database.ALIAS, alias);
            valores.put(Database.NOTAS, notas);
            valores.put(Database.NUMERO, numero);
            valores.put(Database.IDENTIFICADOR, codigo);
            db.insert(NOMBRE_PARADAS, null, valores);
            db.close();
        }
    }

    public void insertarLinea(String numero, String nombre, int identificador){
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            ContentValues valores = new ContentValues();
            valores.put(Database.NUMERO, numero);
            valores.put(Database.NOMBRE, nombre);
            valores.put(Database.IDENTIFICADOR, identificador);
            db.insert(NOMBRE_LINEAS, null, valores);
            db.close();
        }
    }

    public void modificarLinea(String numero, String nombre,  int identificador){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Database.NUMERO, numero);
        valores.put(Database.NOMBRE, nombre);
        valores.put(Database.IDENTIFICADOR, identificador);
        db.insert(Database.NOMBRE_LINEAS, null, valores);
        db.close();
    }

    public void modificarParada(String nombre, String alias, String notas, int numero, int codigo){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Database.NOMBRE, nombre);
        valores.put(Database.ALIAS, alias);
        valores.put(Database.NOTAS, notas);
        valores.put(Database.NUMERO, numero);
        valores.put(Database.IDENTIFICADOR, codigo);
        db.insert(Database.NOMBRE_PARADAS, null, valores);
        db.close();
    }

    public void borrarParada(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Database.NOMBRE_PARADAS, Database.IDENTIFICADOR +id, null);
        db.close();
    }

    public void borrarLinea(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Database.NOMBRE_LINEAS, Database.IDENTIFICADOR +id, null);
        db.close();
    }

    public Parada recuperarParada(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valoresRecuperar = {"_id", Database.NOMBRE, Database.ALIAS, Database.NOTAS, Database.NUMERO, Database.IDENTIFICADOR};
        Cursor c = db.query(Database.NOMBRE_PARADAS, valoresRecuperar, Database.IDENTIFICADOR + id,
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

    public Linea recuperarLinea(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valoresRecuperar = {"_id", Database.NUMERO, Database.NOMBRE, Database.IDENTIFICADOR};
        Cursor c= db.query(Database.NOMBRE_LINEAS, valoresRecuperar, Database.IDENTIFICADOR + id,
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
        String[] valoresRecuperar = {"_id", Database.NOMBRE, Database.ALIAS, Database.NOTAS, Database.NUMERO, Database.IDENTIFICADOR};
        Cursor c = db.query(Database.NOMBRE_PARADAS, valoresRecuperar, null,
                null, null, null, null, null);
        if(c.moveToFirst()){
            do {
                Parada parada = new Parada(c.getString(1), c.getString(4), c.getInt(5));
                listaParadas.add(parada);
            } while (c.moveToNext());
        }
        db.close();
        c.close();
        return listaParadas;
    }

    public List<Linea> recuperarLineas() {
        SQLiteDatabase db = getReadableDatabase();
        List<Linea> listaLineas = new ArrayList<>();
        String[] valoresRecuperar = {"_id", Database.NUMERO, Database.NOMBRE, Database.IDENTIFICADOR};
        Cursor c = db.query(Database.NOMBRE_LINEAS, valoresRecuperar, null,
                null, null, null, null, null);
        if(c.moveToFirst()) {
            do {
                Linea linea = new Linea(c.getString(2), c.getString(1), c.getInt(3));
                listaLineas.add(linea);
            } while (c.moveToNext());
        }
        db.close();
        c.close();
        return listaLineas;
    }

    public void sincronizarParadas(List<Parada> paradasRemotas) {

        // Obtener listado paradas locales
        List<Parada> paradasLocales = recuperarParadas();

        // Sincronización de datos locales con remotos
        for (Parada parada : paradasRemotas) {
            // Si la parada remota no existe en las descargadas en la aplicación, se inserta
            if(!paradasLocales.contains(parada)){
                insertarParada(parada.getName(), null, null, Integer.parseInt(parada.getNumero()), parada.getIdentifier());
            }
        }

    }

    public void sincronizarLineas(List<Linea> lineasRemotas) {

        // Obtener listado líneas locales
        List<Linea> lineasLocales = recuperarLineas();

        // Sincronización de datos locales con remotos
        for (Linea linea : lineasRemotas) {
            // Si la línea remota no existe en las descargadas en la aplicación, se inserta
            if(!lineasLocales.contains(linea)){
                insertarLinea(linea.getNumero(), linea.getName(), linea.getIdentifier());
            }
        }

    }

}