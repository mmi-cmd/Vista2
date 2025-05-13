
package controller;
import DTO.PersonaDTO;
import DTO.ResponseDTO;
import util.JsonFile;

public class PersonaController { 
    public ResponseDTO guardarDatos(PersonaDTO persona){ //Método para enviar datos a la vista
        System.out.println ("...::::SE INCIA EL CONTROLADOR GUARDAR DATOS...::::");
        JsonFile jsonFile = new JsonFile(); // Instancia la clase JsonFile

        boolean res = jsonFile.saveData(persona); // Llama al método saveData de la clase JsonFile

        ResponseDTO responseDTO = new ResponseDTO(); 
        responseDTO.setStatus(res); // Establece el estado de la respuesta
        responseDTO.setMsn(res ? "Exitoso" : "Fallido"); // Establece el mensaje de respuesta
        return responseDTO; // Devuelve la respuesta
    }
}
