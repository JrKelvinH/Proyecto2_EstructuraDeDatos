package proyecto2;

import java.util.ArrayList;
import java.util.List;

public class Resumen {
    
    private String titulo;
    private List<String> autores;
    private List<String> palabrasClave;
    
    public Resumen(String titulo, List<String> autores, List<String> palabrasClave) {
        this.titulo = titulo;
        this.autores = autores;
        this.autores = new ArrayList<>(autores); 
        this.palabrasClave = new ArrayList<>(palabrasClave);
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public List<String> getAutores() {
        return autores;
    }
    
    
    public List<String> getPalabrasClave() {
        return palabrasClave;
    }
    
    @Override
    public String toString() {
        return "Título: " + titulo + "\nAutores: " + autores + "\nPalabras Clave: " + palabrasClave;
    }    
}
