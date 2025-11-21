/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Esta clase es para representar un Artículo
 * Es decir representar el resumen científico anexado
 * @author KelvinH
 * @version corregido 19/11/25
 */

public class Resumen {
    
    private String titulo;
    private List<String> autores;
    private String cuerpo;
    private List<String> palabrasClave;
    Map<String, Integer> frecuenciaPalabras;
    public Resumen(String titulo, List<String> autores, String cuerpo, List<String> palabrasClave) {
        this.titulo = titulo;
        this.autores = autores;
        this.cuerpo = cuerpo;
        this.palabrasClave = palabrasClave;
        this.frecuenciaPalabras = new HashMap<>();
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public List<String> getAutores() {
        return autores;
    }
    
    public String getCuerpo() {
        return cuerpo;
    }
    
    public List<String> getPalabrasClave() {
        return palabrasClave;
    }
    
    /**
     * Analiza frecuencias de palabras clave en el cuerpo.
     */
    public void analizarFrecuencias(List<String> repoPalabras) {
        for (String palabra : repoPalabras) {
            int count = cuerpo.toLowerCase().split(palabra.toLowerCase()).length - 1;
            frecuenciaPalabras.put(palabra, count);
        }
    }
    
    @Override
    public String toString() {
        return "Título: " + titulo + "\nAutores: " + autores + "\nPalabras Clave: " + palabrasClave;
    }    
}
