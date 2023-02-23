import java.util.Arrays;

public class Gasto
{
    private String nombre;
    private int monto;
    
    public Gasto(String nombre, int monto){
        this.nombre = nombre;
        this.monto = monto;
    }
    
    public Gasto(String cadena){
        cadena = cadena.substring(2, cadena.length());
        String[] cadenas = cadena.split(" ");
        this.monto = Integer.parseInt(cadenas[cadenas.length - 1]);
        this.nombre = String.join(" ", Arrays.copyOfRange(cadenas, 0, cadenas.length - 1));
    }

    public String getNombre(){
        return nombre;
    }

    public int getMonto(){
        return monto;
    }
}
