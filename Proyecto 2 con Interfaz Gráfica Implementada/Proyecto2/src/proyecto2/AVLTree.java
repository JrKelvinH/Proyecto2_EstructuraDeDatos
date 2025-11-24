package proyecto2;




import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Arbol AVL para los Autores y las Palabras Claves.
 * Uso de la libreria Collator para las comparaciones
 * @author KelvinH
 * @version 20/11/25
 */
public class AVLTree {
    private NodoAVL root;
    private Collator collator;

    public AVLTree() {
        this.root = null;
        this.collator = Collator.getInstance(new Locale("es", "ES"));  // español con los acentos
        collator.setStrength(Collator.PRIMARY);
    }
    
// Insertar un nodo y balancea el arbol
    public void insertar(String clave, Object dato) {
        root = insertarRec(root, clave, dato);
    }
    
    private NodoAVL insertarRec(NodoAVL nodo, String clave, Object dato) {
        if (nodo == null) {
            return new NodoAVL(clave, dato);
        }
        int cmp = collator.compare(clave, nodo.getClave());
        if (cmp < 0) {
            nodo.setLeft(insertarRec(nodo.getLeft(), clave, dato));
        } else if (cmp > 0) {
            nodo.setRight(insertarRec(nodo.getRight(), clave, dato));
        } else {
            // Actualiza dato si la clave existe
            nodo = new NodoAVL(clave, dato); 
        }
        actualizarAltura(nodo);
        return balancear(nodo);
    }
    
// Busca un nodo por la clave.
    public Object buscar(String clave) {
        NodoAVL nodo = buscarRec(root, clave);
        return nodo != null ? nodo.getDato() : null;
    }  
    
    private NodoAVL buscarRec(NodoAVL nodo, String clave) {
        if (nodo == null) return null;
        int cmp = collator.compare(clave, nodo.getClave());
        if (cmp == 0) return nodo;
        return cmp < 0 ? buscarRec(nodo.getLeft(), clave) : buscarRec(nodo.getRight(), clave);
    }
    
// Elimina un nodo.
    public void eliminar(String clave) {
        root = eliminarRec(root, clave);
    }
    
    private NodoAVL eliminarRec(NodoAVL nodo, String clave) {
        if (nodo == null) return null;
        int cmp = collator.compare(clave, nodo.getClave());
        if (cmp < 0) {
            nodo.setLeft(eliminarRec(nodo.getLeft(), clave));
        } else if (cmp > 0) {
            nodo.setRight(eliminarRec(nodo.getRight(), clave));
        } else {
            if (nodo.getLeft() == null) return nodo.getRight();
            if (nodo.getRight() == null) return nodo.getLeft();
            NodoAVL temp = minValueNode(nodo.getRight());
            nodo = new NodoAVL(temp.getClave(), temp.getDato());
            nodo.setRight(eliminarRec(nodo.getRight(), temp.getClave()));
        }
        actualizarAltura(nodo);
        return balancear(nodo);
    }

// Recorrido en Inorden para la lista ordenada
     public List<String> inorden() {
        List<String> lista = new ArrayList<>();
        inordenRec(root, lista);
        return lista;
    }
    
    private void inordenRec(NodoAVL nodo, List<String> lista) {
        if (nodo != null) {
            inordenRec(nodo.getLeft(), lista);
            lista.add(nodo.getClave());
            inordenRec(nodo.getRight(), lista);
        }
    }
    
// Métodos de rotación y balanceo.
    public NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.getLeft();
        NodoAVL T2 = x.getRight();
        x.setRight(y);
        y.setLeft(T2);
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }
    
    public NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.getRight();
        NodoAVL T2 = y.getLeft();
        y.setLeft(x);
        x.setRight(T2);
        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }
    
    public NodoAVL rotacionIzquierdaDerecha(NodoAVL nodo) {
        nodo.setLeft(rotacionIzquierda(nodo.getLeft()));
        return rotacionDerecha(nodo);
    }
    
    public NodoAVL rotacionDerechaIzquierda(NodoAVL nodo) {
        nodo.setRight(rotacionDerecha(nodo.getRight()));
        return rotacionIzquierda(nodo);
    }
    
     public int getFactorBalance(NodoAVL nodo) {
        return nodo == null ? 0 : getAltura(nodo.getLeft()) - getAltura(nodo.getRight());
    }
     
    public void actualizarAltura(NodoAVL nodo) {
        nodo.setAltura(1 + Math.max(getAltura(nodo.getLeft()), getAltura(nodo.getRight())));
    }
    
    private int getAltura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.getAltura();
    }
    
    private NodoAVL balancear(NodoAVL nodo) {
        int balance = getFactorBalance(nodo);
        if (balance > 1) {
            if (getFactorBalance(nodo.getLeft()) < 0) {
                return rotacionIzquierdaDerecha(nodo);
            } else {
                return rotacionDerecha(nodo);
            }
        }
        if (balance < -1) {
            if (getFactorBalance(nodo.getRight()) > 0) {
                return rotacionDerechaIzquierda(nodo);
            } else {
                return rotacionIzquierda(nodo);
            }
        }
        return nodo;
    }
    
    private NodoAVL minValueNode(NodoAVL nodo) {
        NodoAVL current = nodo;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    } 
}