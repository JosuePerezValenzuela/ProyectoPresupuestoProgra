
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class PresupuestoFamiliarTest
{
    private ArchivoTexto archivo;
    private PresupuestoFamiliar presupuestoFamiliar;
    private Categoria categoria1;
    private Categoria categoria2;
    public PresupuestoFamiliarTest()
    {
    }
    @BeforeEach
    public void setUp()
    {
        archivo = new ArchivoTexto();
        presupuestoFamiliar = new PresupuestoFamiliar();
        presupuestoFamiliar.ingresarMontoMensual(1500);
        categoria1 = new Categoria("Servicios Basicos", 500);
        categoria1.agregarGasto(new Gasto("Electricidad", 200));
        categoria1.agregarGasto(new Gasto("Agua", 120));
        presupuestoFamiliar.agregarCategoria(categoria1);
        categoria2 = new Categoria("Entretenimiento", 200);
        categoria2.agregarGasto(new Gasto("Netflix", 120));
        categoria2.agregarGasto(new Gasto("Spotify", 50));
        presupuestoFamiliar.agregarCategoria(categoria2);
    }

    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void ingresarMontoMensual(){
        PresupuestoFamiliar presupuestoFamiliar = new PresupuestoFamiliar();
        int montoMensual = 2500;

        presupuestoFamiliar.ingresarMontoMensual(montoMensual);

        assertEquals(presupuestoFamiliar.obtenerMontoMensual(), montoMensual);
    }

    @Test 
    public void agregarCategoria(){
        PresupuestoFamiliar presupuestoFamiliar = new PresupuestoFamiliar();
        Categoria categoria1 = new Categoria("Servicios Basicos", 500);

        presupuestoFamiliar.agregarCategoria(categoria1);

        assertEquals(presupuestoFamiliar.getCategorias().size() , 1);
    }

    @Test
    public void calcularMontoRestanteCategoriaTest(){
        PresupuestoFamiliar presupuestoFamiliar = new PresupuestoFamiliar();
        presupuestoFamiliar.ingresarMontoMensual(700);

        Categoria categoria1 = new Categoria("Servicios Basicos", 500);
        Categoria categoria2 = new Categoria("Educación", 100);

        presupuestoFamiliar.agregarCategoria(categoria1);
        presupuestoFamiliar.agregarCategoria(categoria2);

        int montoRestante = presupuestoFamiliar.calcularMontoRestanteCategorias();
        int montoRestanteEsperado = 700 - 500 - 100;

        assertEquals(montoRestante, montoRestanteEsperado);
    }

    @Test
    public void calcularMontoRestanteTest(){
        PresupuestoFamiliar presupuestoFamiliar = new PresupuestoFamiliar();
        presupuestoFamiliar.ingresarMontoMensual(700);
        Categoria categoria1 = new Categoria("Servicios Basicos", 500);
        Categoria categoria2 = new Categoria("Educación", 100);
        presupuestoFamiliar.agregarCategoria(categoria1);
        presupuestoFamiliar.agregarCategoria(categoria2);
        Gasto gasto = new Gasto("electricidad", 200);
        categoria1.agregarGasto(gasto);
        Gasto curso1 = new Gasto("Curso basico", 30);
        Gasto curso2 = new Gasto("Curso avanzado", 70);
        categoria2.agregarGasto(curso1);
        categoria2.agregarGasto(curso2);

        int montoRestante = presupuestoFamiliar.calcularMontoRestante();
        int montoRestanteEsperado = 700 - 200 - 30 - 70;

        assertEquals(montoRestante, montoRestanteEsperado);
    }

    @Test
    public void elimiarCategoriaPorNombre(){
        PresupuestoFamiliar presupuestoFamiliar = new PresupuestoFamiliar();

        String nombreCategoria = "Servicios Basicos";
        Categoria categoria = new Categoria(nombreCategoria, 500);
        presupuestoFamiliar.agregarCategoria(categoria);

        String categoriaEliminada = presupuestoFamiliar.eliminarCategoria(nombreCategoria);

        assertEquals(nombreCategoria, categoriaEliminada);
    }

    @Test
    public void elimiarCategoriaPorNombreCuandoNoExiste(){
        PresupuestoFamiliar presupuestoFamiliar = new PresupuestoFamiliar();

        String nombreCategoria = "Servicios Basicos";
        Categoria categoria = new Categoria(nombreCategoria, 500);
        presupuestoFamiliar.agregarCategoria(categoria);

        String categoriaEliminada = presupuestoFamiliar.eliminarCategoria("Comida");

        assertEquals("Categoria no encontrada", categoriaEliminada);
    }

    @Test
    public void eliminarGastoPorCateogoria(){
        PresupuestoFamiliar presupuestoFamiliar = new PresupuestoFamiliar();

        Categoria categoria = new Categoria("Servicios Basicos", 500);
        presupuestoFamiliar.agregarCategoria(categoria);
        categoria.agregarGasto(new Gasto("Electricidad", 200));
        String gastoEliminado = presupuestoFamiliar.eliminarGasto("Servicios Basicos", "Electricidad");

        assertEquals(gastoEliminado, "Electricidad");
    }

    @Test
    public void obtenerInforme(){
        PresupuestoFamiliar presupuestoFamiliar = new PresupuestoFamiliar();
        presupuestoFamiliar.ingresarMontoMensual(1500);

        Categoria categoria1 = new Categoria("Servicios Basicos", 500);
        categoria1.agregarGasto(new Gasto("Electricidad", 200));
        categoria1.agregarGasto(new Gasto("Agua", 120));
        presupuestoFamiliar.agregarCategoria(categoria1);

        Categoria categoria2 = new Categoria("Entretenimiento", 200);
        categoria2.agregarGasto(new Gasto("Netflix", 120));
        categoria2.agregarGasto(new Gasto("Spotify", 50));
        presupuestoFamiliar.agregarCategoria(categoria2);

        String reporte = presupuestoFamiliar.obtenerInforme();
        String reporteEsperado = 
        "Presupuesto mensual: (490/1500)\n"+
        "Restante total: 1010\n"+
        "Restante por categorias: 800\n" +
        "--------------------------------------\n"+
        categoria1.obtenerInforme()+
        categoria2.obtenerInforme();
        assertEquals(reporte,reporteEsperado);
    }

    @Test
    public void guardarInformeEnArchivo(){
        archivo.crearArchivo("Informe");
        presupuestoFamiliar.obtenerInforme();
        String resultado = archivo.leerArchivo("Informe");
        String esperado =
        "Presupuesto mensual: (490/1500)\n" +
        "Restante total: 1010\n" +
        "Restante por categorias: 800\n" +
        "--------------------------------------\n" +
        "Servicios Basicos (320/500)\n" +
        "Restante de categoría: 180\n" +
        "* Electricidad 200\n" +
        "* Agua 120\n" +
        "---\n" +
        "Entretenimiento (170/200)\n" +
        "Restante de categoría: 30\n" +
        "* Netflix 120\n" +
        "* Spotify 50\n" +
        "---\n";
        System.out.print(resultado);
        assertEquals(esperado,resultado);
    }
    
    
    @Test
    public void crearPresupuestoFamiliarPorCadena(){
        ArchivoTexto archivo = new ArchivoTexto();
        String informe = archivo.leerArchivo("informe");
        
        PresupuestoFamiliar presupuestoFamiliarDeCadena = new PresupuestoFamiliar(informe);
        
        assertEquals(presupuestoFamiliarDeCadena.obtenerMontoMensual(), 1500);
        assertEquals(presupuestoFamiliarDeCadena.getCategorias().size(), 2);
        assertEquals(presupuestoFamiliarDeCadena.obtenerInforme(),informe);
        System.out.println(presupuestoFamiliarDeCadena.obtenerInforme());
        
    }
}
