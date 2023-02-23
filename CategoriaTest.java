

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CategoriaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CategoriaTest
{
    /**
     * Default constructor for test class CategoriaTest
     */
    public CategoriaTest()
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
    public void crearCategoria(){
        String nombre = "Servicios Basicos";
        int presupuesto = 500;
        Categoria categoria = new Categoria(nombre, presupuesto);
        
        assertEquals(categoria.getNombre(), nombre);
        assertEquals(categoria.getPresupuesto(), presupuesto);
    }
    
    @Test
    public void obtenerGastoTotalCategoria(){
        Categoria categoria = new Categoria("Servicios Basicos", 500);
        Gasto electricidad = new Gasto("Electricidad", 200);
        Gasto gas = new Gasto("gas", 25);
        int gastoTotalEsperado =  200 + 25;
        
        categoria.agregarGasto(electricidad);
        categoria.agregarGasto(gas);
        int gastoTotal = categoria.getGastoTotal();

        
        assertEquals(gastoTotal, gastoTotalEsperado);
    }
    
    @Test
    public void obtenerPresupuestoRestanteCategoria(){
        Categoria categoria = new Categoria("Servicios Basicos", 500);
        Gasto electricidad = new Gasto("Electricidad", 200);
        int presupuestoEsperado = 500 - 200;
        
        categoria.agregarGasto(electricidad);
        int presupuestoRestante = categoria.getPresupuestoRestante();

        
        assertEquals(presupuestoRestante, presupuestoEsperado);
    }
    
    @Test
    public void eliminarGastoPorNombre(){
        Categoria categoria = new Categoria("Servicios Basicos", 500);
        String nombreGasto = "Electricidad";
        Gasto electricidad = new Gasto(nombreGasto, 200);
        
        categoria.agregarGasto(electricidad);
        String nombreGastoEliminado = categoria.eliminarGasto(nombreGasto);
        
        assertEquals(nombreGastoEliminado, nombreGasto);
        assertEquals(categoria.getGastos().size(), 0);
    }
    
    @Test 
    public void eliminarGastoPorNombreCuandoNoExiste(){
        Categoria categoria = new Categoria("Servicios Basicos", 500);
        
        String nombreGastoEliminado = categoria.eliminarGasto("Agua");
        
        assertEquals(nombreGastoEliminado, "Gasto no encontrado");
    }
    
    @Test
    public void obtenerInformeGategoria(){
        Categoria categoria = new Categoria("Servicios Basicos", 500);
        categoria.agregarGasto(new Gasto("Electricidad", 120));
        categoria.agregarGasto(new Gasto("Agua", 200));
        
        String informe = categoria.obtenerInforme();
        String informeEsperado = 
        "Servicios Basicos (320/500)\n" +
        "Restante de categoría: 180\n" +
        "* Electricidad 120\n" +
        "* Agua 200\n"+
        "---\n";
        
        assertEquals(informe, informeEsperado);
    }
    
    @Test
    public void obtenerInformeGategoriaCuandoEsNegativo(){
        Categoria categoria = new Categoria("Servicios Basicos", 300);
        categoria.agregarGasto(new Gasto("Electricidad", 120));
        categoria.agregarGasto(new Gasto("Agua", 200));
        
        String informe = categoria.obtenerInforme();
        String informeEsperado = 
        "Servicios Basicos (320/300)\n" +
        "Restante de categoría: -20 (!!deuda)\n" +
        "* Electricidad 120\n" +
        "* Agua 200\n"+
        "---\n";

        assertEquals(informe, informeEsperado);
    }
    
    @Test
    public void crearCategoriaPorCadena(){
        String cadena = "Servicios Basicos (320/500)\n" +
        "Restante de categoría: 180\n" +
        "* Electricidad 120\n" +
        "* Agua 200\n";
        Categoria categoria = new Categoria(cadena);
        
        assertEquals(categoria.getNombre(), "Servicios Basicos");
        assertEquals(categoria.getPresupuesto(), 500);
    }
}
