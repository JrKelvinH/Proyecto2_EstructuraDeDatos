package proyecto2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class extraerDatos {
    private final HashTable resumenesTable;
    private final AVLTree autoresIndex;
    private final AVLTree palabrasIndex;
    public extraerDatos() {
        // Inicializar aquí las estructuras
        resumenesTable = new HashTable(); 
        autoresIndex = new AVLTree();
        palabrasIndex = new AVLTree();
    }
    
    public Resumen extraerDatosDeArchivo(File file) throws FileNotFoundException {
        String linea;
        String titulo = null;
        List<String> autores = new ArrayList<>();
        List<String> palabrasClave = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {

boolean leyendoAutores = false;


while (scanner.hasNextLine()) {
    linea = scanner.nextLine().trim();
    if (linea.isEmpty()) continue;
    if (titulo == null) {
        titulo = linea;
        continue;
    }

    if (linea.equals("Autores")) {
        leyendoAutores = true;
        continue;
    }
    if (linea.equals("Resumen")) { 
        leyendoAutores = false;
        continue;
    }
    if (linea.startsWith("Palabras claves:")) {
        String clavesStr = linea.substring("Palabras claves:".length()).trim();
        String[] partes = clavesStr.split("[,;]");
        for (String clave : partes) {
            if (!clave.trim().isEmpty()) {
                palabrasClave.add(clave.trim()); 
            }
        }
        break; 
    }

    if (leyendoAutores) {
        autores.add(linea); 
    }
}
           
   if (titulo != null && !titulo.isEmpty()) { 
        return new Resumen(titulo, autores, palabrasClave); 
    }
    return null; 
     }
}
    
    public Resumen buscarResumenPorTitulo(String titulo) {
        return resumenesTable.buscar(titulo);
    }
    
    public String obtenerPalabrasClaveIndexadas(String titulo) {
        return "Palabra1, Palabra2, Palabra3 (datos reales)";
    }
    
    public boolean insertarYIndexarResumen(Resumen nuevoResumen) {
    String titulo = nuevoResumen.getTitulo();
        if (resumenesTable.buscar(titulo) != null) { 
        return false; 
    }
  
    resumenesTable.insertar(titulo, nuevoResumen); 
    for (String autor : nuevoResumen.getAutores()) { 
        resumenesTable.insertar(autor, nuevoResumen);
     
    }
    for (String clave : nuevoResumen.getPalabrasClave()) { 
        palabrasIndex.insertar(clave, titulo); 
    }
    return true; 
}
    public void inicializarResumenesDePrueba() {
    String titulo1 = "Arquitectura referencial para mecanismos de Internacionalización...";
    List<String> autores1 = List.of("Christian Guillén Drija", "Andrea Pérez", "Carlos Maldonado"); 
    List<String> claves1 = List.of("análisis de dominio", "ingeniería de dominio", "PHP","arquitectura referencial", "internacionalización", "localización"); 
    Resumen resumenPrueba1 = new Resumen(titulo1, autores1, claves1); 
    this.insertarYIndexarResumen(resumenPrueba1); 
    
    String titulo2 = "GraphQL vs REST: una comparación...";
    List<String> autores2 = List.of("Christian Guillén Drija", "Reynaldo Quintero", "Asher Kleiman"); 
    List<String> claves2 = List.of("REST", "GraphQL", "PHP","cliente-servidor", "mecanismo arquitectónico", "calidad de software"); 
    Resumen resumenPrueba2 = new Resumen(titulo2, autores2, claves2);
    this.insertarYIndexarResumen(resumenPrueba2); 
    
    String titulo3 = "Interacción inalámbrica con dispositivos de bajo costo...";
    List<String> autores3 = List.of("Rhadamés Carmona", "Marcos Ramírez"); 
    List<String> claves3 = List.of("rastreo de cabeza", "interacción humano-computador", "realidad virtual"); 
    Resumen resumenPrueba3 = new Resumen(titulo3, autores3, claves3); 
    this.insertarYIndexarResumen(resumenPrueba3); 
 
    }
}
            
            

    
    
    
