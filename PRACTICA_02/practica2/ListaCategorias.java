package practica2;

import javax.swing.DefaultListModel;
import java.util.ArrayList;
import java.util.List;

public class ListaCategorias {
    private List<Categoria> categorias;

    // Constructor
    public ListaCategorias() {
        categorias = new ArrayList<>();
    }

    // Agregar una categoría
    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    // Eliminar una categoría por ID
    public void eliminarCategoria(String id) {
        categorias.removeIf(c -> c.getId().equals(id));
    }

    // Buscar una categoría por ID
    public Categoria buscarCategoria(String id) {
        for (Categoria categoria : categorias) {
            if (categoria.getId().equals(id)) {
                return categoria;
            }
        }
        return null; // Si no se encuentra
    }

    // Buscar una categoría por nombre
    public Categoria buscarCategoriaPorNombre(String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombre)) {
                return categoria;
            }
        }
        return null; // Si no se encuentra
    }

    // Obtener todas las categorías
    public List<Categoria> getCategorias() {
        return categorias;
    }

    // Generar un DefaultListModel<String> para usar en JComboBox
    public DefaultListModel<String> generarModeloCategorias() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Categoria categoria : categorias) {
            model.addElement(categoria.getNombre()); // Agregar solo el nombre de la categoría
        }
        return model;
    }

    // Cargar categorías desde archivo
    public void cargarCategorias(List<String> datos) {
        for (String linea : datos) {
            String[] partes = linea.split(",");
            if (partes.length == 2) { // Suponemos que cada línea tiene "id,nombre"
                String id = partes[0].trim();
                String nombre = partes[1].trim();
                agregarCategoria(new Categoria(id, nombre));
            }
        }
    }

    // Guardar categorías en archivo
    public List<String> guardarCategorias() {
        List<String> datos = new ArrayList<>();
        for (Categoria categoria : categorias) {
            datos.add(categoria.getId() + "," + categoria.getNombre());
        }
        return datos;
    }
}