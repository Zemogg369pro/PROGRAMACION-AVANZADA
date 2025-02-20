package aloproo;
	import java.util.ArrayList;
		import java.util.List;

public class Inventario {
		    private List<Producto> productos;

		    public Inventario() {
		        this.productos = new ArrayList<>();
		    }

		    public void agregarProducto(Producto producto) {
		        productos.add(producto);
		    }

		    public Producto buscarProducto(String id) {
		        for (Producto producto : productos) {
		            if (producto.getId().equals(id)) {
		                return producto;
		            }
		        }
		        return null;
		    }

		    public boolean eliminarProducto(String id) {
		        return productos.removeIf(p -> p.getId().equals(id));
		    }

		    public List<Producto> listarProductos() {
		        return productos;
		    }

		    @Override
		    public String toString() {
		        StringBuilder salida = new StringBuilder();
		        for (Producto producto : productos) {
		            salida.append(producto.toString()).append("\n");
		        }
		        return salida.toString();
		    }
		}