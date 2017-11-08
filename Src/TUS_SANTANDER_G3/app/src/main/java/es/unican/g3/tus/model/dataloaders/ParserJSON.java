package es.unican.g3.tus.model.dataloaders;

import android.util.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import es.unican.g3.tus.model.Parada;


/**
 * Created by alejandro on 27/09/16.
 * Clase que contiene los metodos necesarios para parsear el JSON que devuelve el servicio "REST" con
 * los diferentes datos del TUS de Santander
 */
public class ParserJSON{

    private ParserJSON(){}

    /**
     * Metodo para obtener todas las paradas de buses
     * @param in InputStream del JSON con las paradas de buses
     * @return Lista con todas las paradas
     * @throws IOException excepcion de entrada/salida
     */
    public static List<Parada> readArrayParadasBus (InputStream in) throws IOException {
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            List<Parada> listParadasBus = new ArrayList<>();
            reader.beginObject(); //summary y resources
            while (reader.hasNext()){
                    String name = reader.nextName();
                    if(name.equals ("resources")){
                        reader.beginArray(); //cada elemento del array es un object
                        while(reader.hasNext())
                            listParadasBus.add(readParada(reader));
                    }else{
                        reader.skipValue();
                    }
            }
            return listParadasBus;
        }


    /**
     * Lee una Parada
     * @param reader lector
     * @return parada a leer
     * @throws IOException excepcion de entrada/salida
     */
    private static Parada readParada (JsonReader reader) throws IOException {
        reader.beginObject(); //Leemos un object
        String name ="";
        String numero="";
        int identifier=-1;
        while(reader.hasNext()) {
            String n = reader.nextName();
            if (n.equals("ayto:numero")) {
                numero = reader.nextString();
            } else if (n.equals("ayto:parada")) {
                name = reader.nextString();
            } else if (n.equals("dc:identifier")) {
                identifier = reader.nextInt();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Parada(name,numero,identifier);
    }
}//ParserJSON
