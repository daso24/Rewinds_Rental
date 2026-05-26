package models;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinarioBusqueda {
    
    private static class Nodo {
        String clave; 
        List<Object[]> listaDatos; 
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(String clave, Object[] datos) {
            this.clave = clave;
            this.listaDatos = new ArrayList<>();
            this.listaDatos.add(datos);
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    private Nodo raiz;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    public void insertar(String clave, Object[] datos) {
        raiz = insertarRecursivo(raiz, clave, datos);
    }

    private Nodo insertarRecursivo(Nodo actual, String clave, Object[] datos) {
        if (actual == null) {
            return new Nodo(clave, datos);
        }
        if (clave.compareToIgnoreCase(actual.clave) < 0) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, clave, datos);
        } else if (clave.compareToIgnoreCase(actual.clave) > 0) {
            actual.derecho = insertarRecursivo(actual.derecho, clave, datos);
        } else {
            actual.listaDatos.add(datos);
        }
        return actual;
    }

    public List<Object[]> buscarParcial(String terminoBusqueda) {
        List<Object[]> resultados = new ArrayList<>();
        buscarRecursivoParcial(raiz, terminoBusqueda.toLowerCase(), resultados);
        return resultados;
    }

    private void buscarRecursivoParcial(Nodo actual, String termino, List<Object[]> resultados) {
        if (actual != null) {
            buscarRecursivoParcial(actual.izquierdo, termino, resultados);
            
            if (actual.clave.toLowerCase().contains(termino)) {
                resultados.addAll(actual.listaDatos);
            }
            
            buscarRecursivoParcial(actual.derecho, termino, resultados);
        }
    }

    public void eliminar(String clave) {
        raiz = eliminarRecursivo(raiz, clave);
    }

    private Nodo eliminarRecursivo(Nodo actual, String clave) {
        if (actual == null) return null;
        if (clave.compareToIgnoreCase(actual.clave) < 0) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, clave);
        } else if (clave.compareToIgnoreCase(actual.clave) > 0) {
            actual.derecho = eliminarRecursivo(actual.derecho, clave);
        } else {
            if (actual.izquierdo == null) return actual.derecho;
            else if (actual.derecho == null) return actual.izquierdo;
            
            Nodo sucesor = encontrarMinimo(actual.derecho);
            actual.clave = sucesor.clave;
            actual.listaDatos = sucesor.listaDatos;
            actual.derecho = eliminarRecursivo(actual.derecho, sucesor.clave);
        }
        return actual;
    }

    private Nodo encontrarMinimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual;
    }
}