import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class GastoTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GastoTest
{
    /**
     * Default constructor for test class GastoTest
     */
    public GastoTest()
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

    @Test
    public void crearGasto(){
        String nombre = "carne";
        int monto = 100;
        Gasto registrarGasto = new Gasto(nombre, monto);

        assertEquals(nombre, registrarGasto.getNombre());
        assertEquals(monto, registrarGasto.getMonto());
    }
    
    @Test
    public void crearGastoPorCadena(){
        Gasto gasto = new Gasto("* Agua 120");
        
        assertEquals("Agua", gasto.getNombre());
        assertEquals(120, gasto.getMonto());
    }
    
    @Test
    public void crearGastoPorCadenas(){
        Gasto gasto = new Gasto("* Consumo de agua 120");
        
        assertEquals("Consumo de agua", gasto.getNombre());
        assertEquals(120, gasto.getMonto());
    }
}
