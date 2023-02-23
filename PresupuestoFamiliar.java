import java.util.ArrayList;

public class PresupuestoFamiliar
{
    private int montoMensual;
    private ArrayList<Categoria> categorias;
    private int montoRestante;
    private ArchivoTexto archivo;

    public PresupuestoFamiliar() {
        montoMensual = 0;
        categorias = new ArrayList();
        archivo = new ArchivoTexto();
    }
    
    public PresupuestoFamiliar(String cadena){
        String[] cadenas = cadena.split("--------------------------------------\n");
        String cabecera = cadenas[0];
        String categoriasCadena = cadenas[1];
        
        String titulo = cabecera.split("\n")[0];
        String[] cadenasTitulo = titulo.split(" ");
        
        String presupuestoCad = cadenasTitulo[cadenasTitulo.length - 1]; // (.../###)
        presupuestoCad = presupuestoCad.substring(1, presupuestoCad.length() - 1); // .../###
        presupuestoCad = presupuestoCad.split("/")[1]; // ###
        int presupuesto = Integer.parseInt(presupuestoCad);
        
        ingresarMontoMensual(presupuesto);
        this.categorias = new ArrayList();
        archivo = new ArchivoTexto();
        
        agregarCategoriasPorCadena(categoriasCadena);
    }
    
    private void agregarCategoriasPorCadena(String cadena){
        String[] categoriasCadenas = cadena.split("---\n");
        
        for(int i = 0; i < categoriasCadenas.length; i++){
            categorias.add(new Categoria(categoriasCadenas[i]));
        }
    }

    public void ingresarMontoMensual(int monto){
        montoMensual = monto;
    }

    public int obtenerMontoMensual(){
        return montoMensual;
    }
    
    public ArrayList<Categoria> getCategorias(){
        return categorias;
    }
    
    public void agregarCategoria(Categoria categoria){
      categorias.add(categoria);
    }
    
    public int calcularMontoRestante(){
        int res = montoMensual;
        
        for(int i = 0; i < categorias.size(); i++){
            res -= categorias.get(i).getGastoTotal();
        }
        return res;
    }
    
    public int calcularMontoRestanteCategorias(){
        int res = montoMensual;
        
        for(int i = 0; i < categorias.size(); i++){
            res -= categorias.get(i).getPresupuesto();
        }
        return res;
    }
    
    public String eliminarCategoria(String nombreCategoria){
        int index = buscarCategoria(nombreCategoria);
        
        if(index == -1){
            return "Categoria no encontrada";
        }
        
        categorias.remove(index);
        return nombreCategoria;
    }
    
    private int buscarCategoria(String nombreCategoria){
        int index = -1;
        for(int i = 0; i < categorias.size(); i++){
            if(nombreCategoria == categorias.get(i).getNombre()){
                index = i;
            }
        }
        return index;
    }
    
    public String eliminarGasto(String nombreCategoria, String nombreGasto){
        int index = buscarCategoria(nombreCategoria);
        
        if(index == -1){
            return "Categoria no encontrada";
        }
        
        return categorias.get(index).eliminarGasto(nombreGasto);
    }
    
    public String obtenerInforme(){
        int montoGastado = montoMensual - calcularMontoRestante();
        String informe = "Presupuesto mensual: (" + montoGastado + "/" + montoMensual + ")\n"+
        "Restante total: " + calcularMontoRestante() + "\n"+
        "Restante por categorias: " + calcularMontoRestanteCategorias() + "\n"+
        "--------------------------------------\n";
        
        for(int i = 0; i < categorias.size(); i++){
            informe += categorias.get(i).obtenerInforme();
        }
        archivo.setEscribo(false);
        archivo.escribirArchivo("Informe", informe);
        return informe;
    }
}
