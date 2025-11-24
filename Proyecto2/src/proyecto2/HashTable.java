package proyecto2;

import java.util.ArrayList;
import java.util.List;

/**
 * Tabla de dispersión para almacenar resúmenes con clave basada en el título.
 * @author KelvinH
 * @version 20/11/25
 */
public class HashTable {
    private List<List<NodoHash>> tabla;
    private int size;
    private static final int TAMANIO_INICIAL = 100;
    
private int calcularIndice(String clave) {
    // Implementación simple de hash:
    return Math.abs(clave.hashCode() % tabla.size());
}
    
    public HashTable() {
        this.tabla = new ArrayList<>(TAMANIO_INICIAL);
        for (int i = 0; i < TAMANIO_INICIAL; i++) {
            tabla.add(new ArrayList<>());
        }
        this.size = 0;
    }
    

    public int hashFunction(String titulo) {
        int hash = 0;
        for (char c : titulo.toCharArray()) {
            hash += (int) c;
        }
        return hash % TAMANIO_INICIAL;
    }
    
    public boolean insertar(String clave, Resumen valor) { 
        if (buscar(clave) != null) { 
        return false;
    }
    int indice = calcularIndice(clave); 
    NodoHash nuevoNodo = new NodoHash(clave, valor);
    tabla.get(indice).add(nuevoNodo); 
    size++;
    return true;
}
    
    public Resumen buscar(String titulo) {
        int indice = calcularIndice(titulo);
        List<NodoHash> bucket = tabla.get(indice);
        for (NodoHash nodo : bucket) {
                if (nodo.getResumen().getTitulo().trim().equals(titulo.trim())) { 
                return nodo.getResumen();
                }
        }
        return null;
    }

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
    
    public List<String> listarTitulosOrdenados() {
        List<String> titulos = new ArrayList<>();
        for (List<NodoHash> bucket : tabla) {
            for (NodoHash nodo : bucket) {
                titulos.add(nodo.getResumen().getTitulo());
            }
        }
        titulos.sort(String::compareTo);
        return titulos;
    }
    
    public int getSize() {
        return size;
    }
}
