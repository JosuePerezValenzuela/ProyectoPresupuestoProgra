import java.util.ArrayList;
import java.util.Arrays;

public class Categoria{
    private String nombre;
    private int presupuesto;
    private int gastoTotal;
    private ArrayList<Gasto> gastos;

    public Categoria(String nombre, int presupuesto){
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.gastoTotal = 0;
        this.gastos = new ArrayList();
    }
    
    public Categoria(String cadena){
        String[] lineas = cadena.split("\n");
        String cabecera = lineas[0];
        String[] cadenasCabecera = cabecera.split(" ");
        
        String[] nombreArray = Arrays.copyOfRange(cadenasCabecera, 0, cadenasCabecera.length - 1);
        String nombre = String.join(" ", nombreArray );
        
        String presupuestoCad = cadenasCabecera[cadenasCabecera.length - 1]; // (.../###)
        presupuestoCad = presupuestoCad.substring(1, presupuestoCad.length() - 1); // .../###
        presupuestoCad = presupuestoCad.split("/")[1]; // ###
        int presupuesto = Integer.parseInt(presupuestoCad);

        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.gastoTotal = 0;
        this.gastos = new ArrayList();
        
        cadenaAGastos(Arrays.copyOfRange(lineas, 2, lineas.length));
    }
    
    private void cadenaAGastos(String[] gastosCadena){
        for(int i = 0; i < gastosCadena.length; i++){
            agregarGasto(new Gasto(gastosCadena[i]));
        }
    }

    public String getNombre(){
        return nombre;
    }

    public int getPresupuesto(){
        return presupuesto;
    }

    public int getGastoTotal(){
        return gastoTotal;
    }

    public ArrayList<Gasto> getGastos(){
        return gastos;
    }

    public int getPresupuestoRestante (){
        return presupuesto - gastoTotal;
    }

    public void agregarGasto(Gasto gasto){
        gastoTotal += gasto.getMonto();
        gastos.add(gasto);
    }

    public String eliminarGasto(String nombreGasto){
        int index = buscarGasto(nombreGasto);

        if(index == -1){
            return "Gasto no encontrado";
        }

        gastos.remove(index);
        return nombreGasto;
    }

    private int buscarGasto(String nombreGasto){
        int index = -1;
        for(int i = 0; i < gastos.size(); i++){
            if(nombreGasto == gastos.get(i).getNombre()){
                index = i;
            }
        }
        return index;
    }

    public String obtenerInforme(){
        String informe = nombre + " (" + getGastoTotal() + "/" + presupuesto + ")\n";
        if(getPresupuestoRestante() < 0){
            informe += "Restante de categoría: " + getPresupuestoRestante() + " (!!deuda)\n";
        } else {
            informe += "Restante de categoría: " + getPresupuestoRestante() + "\n";
        }

        for(int i = 0; i < gastos.size(); i++){
            Gasto gastoAct = gastos.get(i);
            informe += "* " + gastoAct.getNombre() + " " + gastoAct.getMonto() + "\n";
        }

        informe += "---\n";

        return informe;
    }
}