/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * "Clase Auxiliar"
 * Nodo auxiliar para la tabla de dispersión
 * @author KelvinH
 * @version corregido 19/11/25
 */

public class NodoHash {
    
    private final Resumen resumen;
    private NodoHash next;

    public NodoHash(Resumen resumen) {
        this.resumen = resumen;
        this.next = null;
    }
    
    public Resumen getResumen() {
        return resumen;
    }
    public void setNext(NodoHash next) {
        this.next = next;
    }
    public NodoHash getNext() {
        return next;
    
    
        
    }
}
