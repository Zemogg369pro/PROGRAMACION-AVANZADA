package practica2;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Libreria.Archivotxt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Practica02_a extends JFrame implements ActionListener {

    // Declaración de objetos para el manejo de categorías y productos
    private ListaCategorias listacategorias;
    private ListaInsumos listainsumos;

    // Archivos de texto
    private Archivotxt archivocategorias;
    private Archivotxt archivoinsumos;

    // Objetos de los controles
    private JTextField Tid, Tinsumo;
    private JComboBox<String> Ccategoria;
    private JButton Bagregar, Beliminar, Bsalir;
    private JPanel panelFormulario;
    private JTable TareaProductos;

    // Modelos de datos
    private DefaultTableModel modeloInsumos;

    public Practica02_a() {
        // Inicializar listacategorias
        listacategorias = new ListaCategorias();

        // Cargar categorías desde archivo (opcional)
        archivocategorias = new Archivotxt("categorias");
        if (archivocategorias.existe()) {
            List<String> datos = archivocategorias.cargar();
            listacategorias.cargarCategorias(datos);
        } else {
            // Agregar categorías iniciales manualmente
            listacategorias.agregarCategoria(new Categoria("001", "Materiales"));
            listacategorias.agregarCategoria(new Categoria("002", "Mano de Obra"));
        }

        // Inicializar listainsumos
        listainsumos = new ListaInsumos();

        // Cargar insumos desde archivo (opcional)
        archivoinsumos = new Archivotxt("insumos");
        if (archivoinsumos.existe()) {
            List<String> datos = archivoinsumos.cargar();
            listainsumos.cargarInsumo(datos);
        }

        // Configuración inicial de la ventana
        setTitle("Administración de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializar componentes
        inicializarComponentes();
        inicializarEventos();

        // Mostrar la ventana
        setVisible(true);
    }

    private void inicializarComponentes() {
        // Crear paneles
        panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(5, 2));

        // Etiquetas y campos de entrada
        JLabel labelId = new JLabel("ID:");
        JLabel labelInsumo = new JLabel("Insumo:");
        JLabel labelCategoria = new JLabel("Categoría:");

        Tid = new JTextField(20);
        Tinsumo = new JTextField(20);

        // ComboBox para categorías
        DefaultListModel<String> modeloCategorias = listacategorias.generarModeloCategorias();
        Ccategoria = new JComboBox<>();

        // Agregar elementos al JComboBox
        for (int i = 0; i < modeloCategorias.getSize(); i++) {
            Ccategoria.addItem(modeloCategorias.getElementAt(i));
        }

        // Botones
        Bagregar = new JButton("Agregar");
        Beliminar = new JButton("Eliminar");
        Bsalir = new JButton("Salir");

        // Agregar componentes al panel
        panelFormulario.add(labelId);
        panelFormulario.add(Tid);
        panelFormulario.add(labelInsumo);
        panelFormulario.add(Tinsumo);
        panelFormulario.add(labelCategoria);
        panelFormulario.add(Ccategoria);
        panelFormulario.add(Bagregar);
        panelFormulario.add(Beliminar);
        panelFormulario.add(Bsalir);

        // Tabla para mostrar datos
        TareaProductos = new JTable();
        JScrollPane scrollPane = new JScrollPane(TareaProductos);
        add(scrollPane, BorderLayout.CENTER);

        // Agregar el panel de formulario
        add(panelFormulario, BorderLayout.NORTH);

        // Actualizar la tabla
        actualizarTabla();
    }

    private void inicializarEventos() {
        // Asignar ActionListener a los botones
        Bagregar.addActionListener(this);
        Beliminar.addActionListener(this);
        Bsalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == Bagregar) {
            // Lógica para agregar producto
            String id = Tid.getText().trim();
            String insumo = Tinsumo.getText().trim();
            String categoriaSeleccionada = (String) Ccategoria.getSelectedItem();

            if (!id.isEmpty() && !insumo.isEmpty() && categoriaSeleccionada != null) {
                Categoria categoria = listacategorias.buscarCategoriaPorNombre(categoriaSeleccionada);
                if (categoria != null) {
                    Insumo nuevoInsumo = new Insumo(id, insumo, categoria.getId());
                    listainsumos.agregarInsumo(nuevoInsumo);

                    // Guardar cambios en el archivo
                    archivoinsumos.guardar(listainsumos.guardarInsumos());

                    // Actualizar la tabla y limpiar campos
                    actualizarTabla();
                    limpiarCampos();

                    JOptionPane.showMessageDialog(this, "Producto agregado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Categoría no encontrada.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            }
        } else if (source == Beliminar) {
            // Lógica para eliminar producto
            int selectedRow = TareaProductos.getSelectedRow();
            if (selectedRow != -1) {
                listainsumos.eliminarInsumo(selectedRow);

                // Guardar cambios en el archivo
                archivoinsumos.guardar(listainsumos.guardarInsumos());

                // Actualizar la tabla
                actualizarTabla();

                JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
            }
        } else if (source == Bsalir) {
            // Guardar categorías en el archivo antes de salir
            archivocategorias.guardar(listacategorias.guardarCategorias());

            // Salir de la aplicación
            System.exit(0);
        }
    }

    private void limpiarCampos() {
        Tid.setText("");
        Tinsumo.setText("");
    }

    private void actualizarTabla() {
        // Actualizar el modelo de la tabla
        modeloInsumos = getmodelo(listacategorias);
        TareaProductos.setModel(modeloInsumos);
    }

    private DefaultTableModel getmodelo(ListaCategorias listacategorias) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Las celdas no son editables
            }
        };

        // Definir columnas
        modelo.addColumn("ID");
        modelo.addColumn("Insumo");
        modelo.addColumn("Categoría");

        // Agregar filas
        for (Insumo insumo : listainsumos.getInsumos()) {
            String[] info = new String[3];
            info[0] = insumo.getIdProducto();
            info[1] = insumo.getProducto();
            info[2] = listacategorias.buscarCategoria(insumo.getIdCategoria()).getNombre();
            modelo.addRow(info);
        }

        return modelo;
    }

    public static void main(String[] args) {
        new Practica02_a();
    }
}