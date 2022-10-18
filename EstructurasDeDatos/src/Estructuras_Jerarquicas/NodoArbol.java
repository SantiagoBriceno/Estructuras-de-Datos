/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras_Jerarquicas;

/**
 *
 * @author Usuario
 */

public class NodoArbol {
    Object elem;
    NodoArbol izquierdo;
    NodoArbol derecho;
    
    public NodoArbol(Object elem, NodoArbol izq, NodoArbol der) {
        this.elem = elem;
        this.izquierdo = izq;
        this.derecho = der;
    }
    
    public NodoArbol(Object elem) {
        this.elem = elem;
        this.izquierdo = null;
        this.derecho = null;
    }
    
    public Object getElem() {
        return elem;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }
    
    
    
}
