 
 
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
 
/**
   * Secuencia de prueba de métodos personalizados
 *
 * @author xusucheng
 * @create 2018-12-14
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MethodOrderTest {
    @Test
    public void testFirst() {
        System.out.println("test1");
    }
 
    @Test
    public void testSecond() {
        System.out.println("test2");
    }
 
    @Test
    public void testThree() {
        System.out.println("test3");
    }
}
