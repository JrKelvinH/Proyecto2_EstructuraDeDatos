package proyecto2;


public class NodoHash {
    
    private final String clave;
    private final Resumen resumen;

    public NodoHash(String clave, Resumen resumen) {
        this.resumen = resumen;
        this.clave = clave;
    }
    
    public Resumen getResumen() {
        return resumen;
    }
    
    public String getClave() {
        return clave;
    }
          
    
}
