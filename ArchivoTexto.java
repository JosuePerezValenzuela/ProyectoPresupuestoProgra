import java.io.*;
import java.util.Scanner;

public class ArchivoTexto{
   private File archivo;
   private String nombre;
   private boolean escribo = false;
   public ArchivoTexto(){
      
    }
   public boolean crearArchivo(String nombre){
      boolean resp = false;
      this.nombre = nombre;
      try{
       archivo = new File("./" + nombre + ".txt");
       if(archivo.createNewFile()){
           System.out.println("El archivo se creo");
           resp = true;
        }else{
           resp = false;
        }
      } catch (Throwable e){
        System.err.println("No se puedo crear el archivo" + e);
        resp = false;
      } 
      return resp;
    }
   public String escribirArchivo(String nombre, String contenido){
      String resp = "";
      try{
          if(escribo == false){
            FileWriter escritor = new FileWriter("./" + nombre + ".txt");
            escritor.write(contenido);
            escritor.close();
            escribo = true;
            resp = "Contenido escrito correctamente";
          }else{
            FileWriter escritor = new FileWriter("./" + nombre + ".txt", escribo);
            escritor.write("\r\n" + contenido);
            escritor.close();
            resp = "Se añadio el nuevo texto";
            }
        }catch (IOException e){
          resp = "Error al escribir en el archivo";
        }
      return resp;
    }
   public String leerArchivo(String nombre){
      String resp = "";
      try{
          archivo = new File("./" + nombre + ".txt");
          Scanner lector = new Scanner(archivo);
          while(lector.hasNextLine()){
              resp += lector.nextLine() + "\n";
            }
          lector.close();  
        } catch(FileNotFoundException e){
          resp = "Error al leer el archivo";
      }
      return resp;
    }
   public void setEscribo(boolean valor){
      this.escribo = valor;
    }
   public boolean getEscribo(){
      return escribo;
    }
}
