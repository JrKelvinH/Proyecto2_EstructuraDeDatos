/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * "Clase auxiliar para AVLTree"
 * Nodo auxiliar para el árbol AVL
 * @author KelvinH
 * @version corregido 19/11/25
 */
public class NodoAVL {
    
    private String clave; // Con esto obtengo el autor o la palabra clave requerida
    private Object dato; // Para la lista de resumenes
    private int altura;
    private NodoAVL left, right;
    
    public NodoAVL(String clave, Object dato) {
        this.clave = clave;
        this.dato = dato;
        this.altura = 1;
        this.left = this.right = null;
    }
    
    public String getClave() {
        return clave;
    }
    
    public Object getDato() {
        return dato;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    public int getAltura() {
        return altura;
    }
    
    public NodoAVL getLeft() {
        return left;
    }
    
    public void setLeft(NodoAVL left) {
        this.left = left;
    }
    
    public NodoAVL getRight() {
        return right;
    }
    
    public void setRight(NodoAVL right) {
        this.right = this.right;
    
    }
}
