package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.PersonaDTO;


public class JsonFile {
    
    private final String FOLDER = "db"; 
    private final String FILE = File.separator + "persona.json"; //File.separator es para que funcione en cualquier sistema operativo

    
    public boolean crearCarpeta(){
        
        File folder = new File(FOLDER);
        
        if (folder.exists()){
            return true;
        } 
        return folder.mkdir();
    }
    
    
    public boolean crearArchivo() {
    // Asegurar que la carpeta exista
    if (!crearCarpeta()) {
        System.out.println("Error -> No se pudo crear la carpeta");
        return false;
    }

    File file = new File(FOLDER + File.separator + "persona.json");
    System.out.println("Ruta completa del archivo: " + file.getAbsolutePath());

    try {
        if (file.exists()) {
            System.out.println("El archivo ya existe.");
        } else {
            boolean creado = file.createNewFile(); // Intenta crear el archivo
            if (creado) {
                FileWriter writer = new FileWriter(file);
                writer.write("[]"); // Inicializa el archivo JSON con arreglo vacío
                writer.close();
                System.out.println("Archivo creado correctamente.");
            } else {
                System.out.println("No se pudo crear el archivo.");
            }
        }
        return true;
    } catch (IOException ex) {
        System.out.println("Error -> " + ex.getMessage());
        return false;
    }
}


    
    public boolean saveData(PersonaDTO personaDTO){ //Se le pasa el objeto personaDTO
        try{
            crearArchivo(); //Crea el archivo si no existe
            RandomAccessFile rf = new RandomAccessFile(FOLDER + FILE, "rw"); //Las comillas le indican cual de los 3 permisos R, W o X
            long leght = rf.length(); //longitud del archivo
            rf.seek(leght - 1); //Se ubica en la posicion indicada 
            if(leght > 2){
                rf.writeBytes(",");
            }
            
            Map<String, Object> personaMap = this.objectToMap(personaDTO); //Convierte el objeto a un mapa
            StringBuilder sb = new StringBuilder(); //StringBuilder es una clase que permite concatenar cadenas de texto
            sb.append("{"); //Abre el objeto
            int count = 0;
            for(Map.Entry<String, Object> entry :personaMap.entrySet()){ //Entry permite recorrer tanto clave como valor
                sb.append("\"").append(entry.getKey()).append("\"").append(":"); //Patrón de diseño builder
                if (entry.getValue() instanceof String) { // Si el valor es un String
                    sb.append("\"").append(entry.getValue()).append("\""); // Se agrega comillas
                } else {
                    sb.append(entry.getValue()); 
                }
                count++;
                if (count < personaMap.size()){ // Si no es el último elemento
                    sb.append(",");
                }
            }
            sb.append("}"); //Cierra el objeto
            rf.writeBytes(sb.toString()); //Convierte el StringBuilder a String
            rf.writeBytes("]"); //Cierra el array


            return true;
        } catch(Exception e){
            System.out.println("Error -> " + e.getMessage());
            return false;
        }
    }

    private Map <String, Object> objectToMap (PersonaDTO persona){  //Object es un tipo generico, cuando no se sabe el tipo de dato
        Map<String, Object> personaMap = new HashMap<>();
        
        personaMap.put("nombre", persona.getNombre());
        personaMap.put("salario", persona.getSalario());

        return personaMap;
    }
}
