
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ArchivoTextoTest{
    private ArchivoTexto archivo;
    private boolean creado;
    private ArchivoTexto archivoMocked;
    private String contenido = "¿Hola como estan?";
    private String nuevoContenido = "Todo bien y tu?";
    /**
     * Default constructor for test class ArchivoTextoTest
     */
    public ArchivoTextoTest()
    {
        
    }
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        archivo = new ArchivoTexto();
        creado = archivo.crearArchivo("Prueba1");
        archivoMocked = mock(ArchivoTexto.class);
    }
    @Test
    public void AcrearArchivo(){  
      assertEquals(true, creado);
    }
    @Test 
    public void BcrearArchivoCuandoYaExiste(){
      assertEquals(false, creado);
    }
    @Test 
    public void CescribirArchivo(){
      String resp = archivo.escribirArchivo("Prueba1", contenido);
      when(archivoMocked.leerArchivo("Prueba1")).thenReturn(contenido + "\n");
      assertEquals(archivoMocked.leerArchivo("Prueba1"), contenido + "\n");
      assertEquals("Contenido escrito correctamente", resp);
    }
    @Test
    public void DcontinuarEscribiendoArchivo(){
      archivo.setEscribo(true);
      String resp = archivo.escribirArchivo("Prueba1", nuevoContenido);
      when(archivoMocked.leerArchivo("Prueba1")).thenReturn("¿Hola como estan?\nTodo bien y tu?\n");
      assertEquals(archivoMocked.leerArchivo("Prueba1"), "¿Hola como estan?\nTodo bien y tu?\n");
      assertEquals("Se añadio el nuevo texto", resp);
    }
    @Test
    public void EcambioDeEscribo(){
      archivo.setEscribo(true);
      boolean nuevoValor = archivo.getEscribo();
      assertEquals(true, nuevoValor);
    }
    @Test 
    public void FleerArchivo(){
      //Sabemos que en los test anteriores ya creamos y escribimos en nuestro archivo "Prueba1"
      String resp = archivo.leerArchivo("Prueba1");
      String contenidoDelArchivo = "" + 
      "¿Hola como estan?\n" + 
      "Todo bien y tu?\n";
      assertEquals(resp, contenidoDelArchivo);
    }
    @Test 
    public void GescribirArchivo(){
      String resp = archivo.escribirArchivo("Prueba1", contenido);
      assertEquals(archivo.leerArchivo("Prueba1"), contenido + "\n");
      assertEquals("Contenido escrito correctamente", resp);
    }
    @Test
    public void HcontinuarEscribiendoArchivo(){
      archivo.setEscribo(true);
      String resp = archivo.escribirArchivo("Prueba1", nuevoContenido);
      assertEquals(archivo.leerArchivo("Prueba1"), "¿Hola como estan?\nTodo bien y tu?\n");
      assertEquals("Se añadio el nuevo texto", resp);
    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

}
