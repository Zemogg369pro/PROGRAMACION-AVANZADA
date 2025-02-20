package ejercicio_unidad1;
import java.util.ArrayList;
public class prog2 {

	
	    public static void main(String[] args) {
	        // Usar ArrayList como pila
	        ArrayList<Integer> pilas = new ArrayList<>();

	        // Agregar elementos (push)
	        pilas.add(10);
	        pilas.add(20);
	        pilas.add(30);

	        // Imprimir pila
	        System.out.println("Pila actual: " + pilas);

	        // Simular pop (remover el último elemento)
	        int elemento = pilas.remove(pilas.size() - 1);
	        System.out.println("Elemento eliminado: " + elemento);

	        // Imprimir pila después del pop
	        System.out.println("Pila después del pop: " + pilas);
	    }
	}
