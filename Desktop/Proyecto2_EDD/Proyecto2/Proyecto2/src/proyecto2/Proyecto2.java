/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2;

import java.util.Arrays;
import java.util.List;

/**
 * Clase Main
 * @author KelvinH
 * @version corregido 20/11/25
 */
public class Proyecto2 {
    private static HashTable hashTable = new HashTable();
    private static AVLTree avlAutores = new AVLTree();
    private static AVLTree avlPalabrasClave = new AVLTree();

    public static void main(String[] args) {
        System.out.println("=== Iniciando pruebas por consola ===");
        

        // Crear resúmenes de ejemplo (adapta con datos del Anexo 1)
        
        Resumen res1 = new Resumen("Inteligencia Artificial en la Medicina", 
            Arrays.asList("Dr. Ana García", "Dr. Juan Pérez"), 
            "La IA revoluciona el diagnóstico médico con algoritmos avanzados.", 
            Arrays.asList("IA", "medicina", "diagnóstico"));
        
        Resumen res2 = new Resumen("Big Data para Análisis Científico", 
            Arrays.asList("Dra. María López"), 
            "El big data permite analizar grandes volúmenes de datos científicos.", 
            Arrays.asList("big data", "análisis", "ciencia"));

        // Prueba 1: Insertar en HashTable
        System.out.println("\n--- Prueba: Insertar en HashTable ---");
        boolean insertado1 = hashTable.insertar(res1);
        boolean insertado2 = hashTable.insertar(res2);
        System.out.println("Resumen 1 insertado: " + insertado1 + " | Tamaño actual: " + hashTable.getSize());
        System.out.println("Resumen 2 insertado: " + insertado2 + " | Tamaño actual: " + hashTable.getSize());

        // Prueba duplicado
        boolean duplicado = hashTable.insertar(res1);
        System.out.println("Intento de duplicado: " + duplicado);

        // Prueba 2: Buscar en HashTable
        System.out.println("\n--- Prueba: Buscar en HashTable ---");
        Resumen encontrado = hashTable.buscar("Inteligencia Artificial en la Medicina");
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado.getTitulo());
        } else {
            System.out.println("No encontrado");
        }

        // Prueba 3: Listar títulos ordenados
        System.out.println("\n--- Prueba: Listar Títulos Ordenados ---");
        List<String> titulos = hashTable.listarTitulosOrdenados();
        System.out.println("Títulos ordenados: " + titulos);

        // Prueba 4: Insertar en AVLTree (autores y palabras clave)
        System.out.println("\n--- Prueba: Insertar en AVLTree ---");
        avlAutores.insertar("Dr. Ana García", Arrays.asList(res1));
        avlAutores.insertar("Dra. María López", Arrays.asList(res2));
        avlPalabrasClave.insertar("IA", 5);
        avlPalabrasClave.insertar("big data", 3);
        System.out.println("Insertados autores y palabras clave en AVL.");

        // Prueba 5: Buscar en AVLTree
        System.out.println("\n--- Prueba: Buscar en AVLTree ---");
        Object datoAutor = avlAutores.buscar("Dr. Ana García");
        if (datoAutor != null) {
            System.out.println("Autor encontrado: Dr. Ana García");
        }
        Object datoPalabra = avlPalabrasClave.buscar("IA");
        System.out.println("Frecuencia de 'IA': " + datoPalabra);

        // Prueba 6: Recorrido inorden (orden alfabético)
        System.out.println("\n--- Prueba: Recorrido Inorden en AVL ---");
        List<String> autoresOrdenados = avlAutores.inorden();
        System.out.println("Autores ordenados: " + autoresOrdenados);
        List<String> palabrasOrdenadas = avlPalabrasClave.inorden();
        System.out.println("Palabras clave ordenadas: " + palabrasOrdenadas);

        // Prueba 7: Analizar frecuencias en Resumen
        System.out.println("\n--- Prueba: Analizar Frecuencias en Resumen ---");
        List<String> repoPalabras = Arrays.asList("IA", "medicina", "big data");
        res1.analizarFrecuencias(repoPalabras);
        System.out.println("Frecuencias en Resumen 1: " + res1.frecuenciaPalabras);

        // Prueba 8: Eliminar
        System.out.println("\n--- Prueba: Eliminar ---");
        boolean eliminado = hashTable.eliminar("Inteligencia Artificial en la Medicina");
        System.out.println("Eliminado: " + eliminado + " | Tamaño actual: " + hashTable.getSize());

        System.out.println("\n=== Pruebas Completadas ===");
    }

    // Métodos auxiliares (si necesitas)
    public static void precargarDatos() {
        // Llama aquí si quieres precargar más datos
    }
}
