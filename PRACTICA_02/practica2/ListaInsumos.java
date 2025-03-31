package practica2;

import java.util.ArrayList;
import java.util.List;

public class ListaInsumos {
    private List<Insumo> insumos;

    // Constructor
    public ListaInsumos() {
        insumos = new ArrayList<>();
    }

    // Agregar un insumo
    public void agregarInsumo(Insumo insumo) {
        insumos.add(insumo);
    }

    // Eliminar un insumo por índice
    public void eliminarInsumo(int index) {
        if (index >= 0 && index < insumos.size()) {
            insumos.remove(index);
        }
    }

    // Obtener todos los insumos
    public List<Insumo> getInsumos() {
        return insumos;
    }

    // Cargar insumos desde archivo
    public void cargarInsumo(List<String> datos) {
        for (String linea : datos) {
            String[] partes = linea.split(",");
            if (partes.length == 3) {
                String idProducto = partes[0].trim();
                String producto = partes[1].trim();
                String idCategoria = partes[2].trim();
                agregarInsumo(new Insumo(idProducto, producto, idCategoria));
            }
        }
    }

    // Guardar insumos en archivo
    public List<String> guardarInsumos() {
        List<String> datos = new ArrayList<>();
        for (Insumo insumo : insumos) {
            datos.add(insumo.getIdProducto() + "," + insumo.getProducto() + "," + insumo.getIdCategoria());
        }
        return datos;
    }
}