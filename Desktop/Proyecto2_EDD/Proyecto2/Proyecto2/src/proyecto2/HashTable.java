/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.util.ArrayList;
import java.util.List;

/**
 * Tabla de dispersión para almacenar resúmenes con clave basada en el título.
 * @author KelvinH
 * @version corregido 20/11/25
 */
public class HashTable {
    private List<List<NodoHash>> tabla;
    private int size;
    private static final int TAMANIO_INICIAL = 100;
    
    public HashTable() {
        this.tabla = new ArrayList<>(TAMANIO_INICIAL);
        for (int i = 0; i < TAMANIO_INICIAL; i++) {
            tabla.add(new ArrayList<>());
        }
        this.size = 0;
    }
    
// Función para convertir cada caracter a su valor numerico(ascii) 
// y sumar ese valor a hash
    public int hashFunction(String titulo) {
        int hash = 0;
        for (char c : titulo.toCharArray()) {
            hash += (int) c;
        }
        return hash % TAMANIO_INICIAL;
    }
    
// Para insertar un resumen, en caso de no existir
// Evita los duplicados
    public boolean insertar(Resumen resumen) {
        int index = hashFunction(resumen.getTitulo());
        List<NodoHash> bucket = tabla.get(index);
        for (NodoHash nodo : bucket) {
            if (nodo.getResumen().getTitulo().equals(resumen.getTitulo())) {
                return false; // Duplicado
            }
        }
        bucket.add(new NodoHash(resumen));
        size++;
        return true;
    }
    
// Busca un resumen por título
    public Resumen buscar(String titulo) {
        int index = hashFunction(titulo);
        List<NodoHash> bucket = tabla.get(index);
        for (NodoHash nodo : bucket) {
            if (nodo.getResumen().getTitulo().equals(titulo)) {
                return nodo.getResumen();
            }
        }
        return null;
    }
    
// Elimina un resumen por título
    public boolean eliminar(String titulo) {
        int index = hashFunction(titulo);
        List<NodoHash> bucket = tabla.get(index);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).getResumen().getTitulo().equals(titulo)) {
                bucket.remove(i);
                size--;
                return true;
            }
        }
        return false;
    }
    
// Lista los títulos ordenados alfabeticamente, use el método sort()
// (O(n))
    public List<String> listarTitulosOrdenados() {
        List<String> titulos = new ArrayList<>();
        for (List<NodoHash> bucket : tabla) {
            for (NodoHash nodo : bucket) {
                titulos.add(nodo.getResumen().getTitulo());
            }
        }
        titulos.sort(String::compareTo); // Orden alfabético simple
        return titulos;
    }
    
    public int getSize() {
        return size;
    }
}
