package Libreria;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Archivotxt {
    private String rutaArchivo;

    // Constructor
    public Archivotxt(String nombreArchivo) {
        this.rutaArchivo = "\"C:\\Users\\wlzem\\eclipse-workspace\\Pa_a2173287040_Practica2\\src\\Libreria\"" + nombreArchivo + ".txt";
    }

    // Método para verificar si el archivo existe
    public boolean existe() {
        File archivo = new File(rutaArchivo);
        return archivo.exists();
    }

    // Método para cargar datos desde el archivo
    public List<String> cargar() {
        List<String> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                datos.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

    // Método para guardar datos en el archivo
    public void guardar(List<String> datos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String dato : datos) {
                bw.write(dato);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}