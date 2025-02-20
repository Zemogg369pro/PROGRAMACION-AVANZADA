package aloproo;
import java.io.*;
		import java.util.ArrayList;
		import java.util.List;
		
	
		public class PresistenciaDatos<T> {
		    public void guardarCSV(List<T> datos, String filePath) throws IOException {
		        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
		            for (T dato : datos) {
		                if (dato instanceof Producto) {
		                    Producto producto = (Producto) dato;
		                    String linea = String.format("%s,%s,%.2f,%d",
		                        producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
		                    bw.write(linea);
		                    bw.newLine();
		                }
		            }
		        }
		    }

		    public List<T> cargarCSV(String filePath, Class<T> type) throws IOException {
		        List<T> datos = new ArrayList<>();
		        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		            String linea;
		            while ((linea = br.readLine()) != null) {
		                if (type == Producto.class) {
		                    String[] partes = linea.split(",");
		                    Producto producto = new Producto(partes[0], partes[1], Double.parseDouble(partes[2]), Integer.parseInt(partes[3]));
		                    datos.add(type.cast(producto));
		                }
		            }
		        }
		        return datos;
		    }
		}