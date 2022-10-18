/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras_Jerarquicas;
import Estructuras_Dinamicas.*;
import Estructuras_Lineales.*;

/**
 *
 * @author Santiago B
 * SUBIR A GITHUB
 * IMPORTANTE 
 * falta pila
 * diseñando Isomorfo y balanceado
 */
public class ArbolBin {

    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        /* Inserta elemNuevo como hijo del primer nodo encontrado en preorden igual
        a elemPadre, como hijo izquierdo (I) o derecho (D), segun lo indique el
        parametro lugar
         */
        boolean exito = true;

        if (this.raiz == null) {
            //si el arbol esta vacio, ponemos el elem nuevo en la raiz
            this.raiz = new NodoArbol(elemNuevo);
        } else {
            //si no esta vacio, se busca al padre
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {
                if (lugar == 'I' && nodoPadre.getIzquierdo() == null) {
                    //si el padre existe y no tiene HI se lo agrega
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo));
                } else {
                    if (lugar == 'D' && nodoPadre.getDerecho() == null) {
                        //si el padre existe y no tiene HD se lo agrega
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo));
                    } else {
                        //si el padre no existe o ya tiene los 2 hijos
                        exito = false;
                    }
                }
            } else {
                exito = false;
            }
        }
        return exito;

    }
    
    public boolean insertar(Object elemNuevo){
        boolean result = false;
        if(raiz == null){
            raiz = new NodoArbol(elemNuevo);
            result = true;
        }
        return result;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        /*metodo privado que busca un elemento y devuelve el nodo que
        lo contiene. Si no se encuentra devuelve null*/

        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                //si el resultado es n, lo devuelve
                resultado = n;
            } else {
                //no es el buscado, busca primero en el hijo izq
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                //si no lo encuentra en el hijo izquierdo, busca en el der
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public int altura() {
        //devuelve la altura del arbol, es decir, la long
        //del camino mas largo desde la raiz hasta una hoja
        //Nodo vacio: -1, hoja: 0

        int res;
        if (this.raiz == null) {
            //el arbol esta vacio
            res = -1;
        } else {
            res = alturaAux(this.raiz);
        }
        return res;
    }

    private int alturaAux(NodoArbol nodo) {
        int izq, der, res = 0;
        if (!esHoja(nodo) && nodo != null) {
            izq = alturaAux(nodo.getIzquierdo()) + 1;
            der = alturaAux(nodo.getDerecho()) + 1;

            if (izq >= der) {
                res = izq;
            } else {
                res = der;
            }
        }

        return res;
    }

    public int nivel(Object elem) {
        //Devuelve el nivel de elem en el arbol.
        //Si no existe devuelve -1
        int nivel = -1;

        if (this.raiz != null) {
            if (elem == this.raiz.getElem()) {
                nivel = 0;
            } else {
                nivel = nivelAux(elem, this.raiz);
            }
        }

        return nivel;

    }

    private int nivelAux(Object elem, NodoArbol n) {
        int res = -1;

        if (n != null) {
            if (n.getElem().equals(elem)) {
                res = 0;

            } else {
                res = nivelAux(elem, n.getIzquierdo());
                //si no lo encuentra en el hijo izquierdo, busca en el der
                if (res == -1) {
                    res = nivelAux(elem, n.getDerecho());
                }
                if (res != -1) {
                    res++;
                }
            }
        }
        return res;

    }
    
    public int profundidadDelElem(Object buscado){
        int result = -1;
        if(!esVacio() && obtenerNodo(this.raiz, buscado) != null){
            result = profundidadDelElemAux(this.raiz, buscado);
        }
        return result;
    }
    
    private int profundidadDelElemAux(NodoArbol n, Object buscado){
        int result = -1;
        if(n != null){
            if(n.getElem().equals(buscado)){
                result = 0;
            }else{
                result = profundidadDelElemAux(n.getIzquierdo(), buscado) + 1;
                if(result > -1){
                    result = profundidadDelElemAux(n.getDerecho(), buscado) + 1;
                }
            }
        }
        return result;
    }

    /*
    NUEVO PADRE (Falta diseñar) 
    public Object padre(Object elem) {
        Object padre = null;
        
        if (this.raiz != null) {
        if (elem == this.raiz.getElem()) {
            padre = this.raiz.getElem();
        } else {
            if (obtenerNodo(this.raiz, elem) != null) {
                padre = auxPadre(this.raiz, elem).getElem();
            }
        }
        }
        return padre;
    }
    private NodoArbol auxPadre(NodoArbol n, Object hijo) {
        NodoArbol resultado = null;
        // caso base, si la raiz es hoja o hijo derecho es igual a buscado o hijo izquierdo es igual a buscado 
        if (n != null && n.getDerecho() != null) {
        if (esHoja(n) || n.getDerecho().getElem() == hijo || n.getIzquierdo().getElem() == hijo) {
            resultado = n;
        } else { // si no
            resultado = auxPadre(n.getIzquierdo(), hijo); //busca en hijo izquierdo 
            if (resultado != null) {
                resultado = auxPadre(n.getDerecho(), hijo);
            }
        }
        }
        return resultado;
    }*/
    public Object padre(Object elemento) {
        Object resultado;
        resultado = null;   // En caso de que el elemento a buscar sea el elemento raiz retorna null
        if (this.raiz != null) {
            if (!this.raiz.getElem().equals(elemento)) {   // Si el elemento buscado no está en la raíz lo busca
                resultado = padreAux(this.raiz, elemento);
            }
        }
        return resultado;
    }

    private Object padreAux(NodoArbol nodo, Object buscado) {
        // Metodo privado recursivo porque recibe un nodo de la estructura. Ademas recibe un elemento de tipo Object del cual
        // debe buscar su elemento padre en la estructura.
        // Zona de declaracion de variables
        Object resultado;
        NodoArbol nodoIzq, nodoDer;
        // Zona de inicializacion de variable
        resultado = null;

        if (nodo != null) {
            nodoIzq = nodo.getIzquierdo();
            nodoDer = nodo.getDerecho();

            if (nodoIzq != null && nodoIzq.getElem().equals(buscado) || nodoDer != null && nodoDer.getElem().equals(buscado)) {
                // Si encontro el elemento buscado en el nodo hijo izquierdo o en el derecho, retorna el elemento padre
                resultado = nodo.getElem();
            } else {
                // Sino, busca por los hijos de la izquierda
                resultado = padreAux(nodoIzq, buscado);
                if (resultado == null) {   // Si no tiene mas hijos izquierdos por recorrer, busca hacia la derecha
                    resultado = padreAux(nodoDer, buscado);
                }
            }
        }
        return resultado;
    }
    
    public void vaciar() {
        //Quita todos los elementos de la estructura
        this.raiz = null;
    }

    @Override
    public String toString() {
        //Devuelve una cadena de caracteres que indica cual es la raiz
        //del arbol y quienes son los hijos de cada nodo        
        String cad;
        if (this.raiz == null) {
            cad = "Arbol vacio";
        } else {
            cad = toStringAux(this.raiz, "");
        }
        return cad;
    }

    private String toStringAux(NodoArbol nodo, String cad) {
        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                //si existe el hijo izquierdo, lo agrega a la cadena
                cad += "\n" + nodo.getElem() + "     HI: " + nodo.getIzquierdo().getElem();
            } else {
                cad += "\n" + nodo.getElem() + "     HI: - ";
            }
            if (nodo.getDerecho() != null) {
                cad += "     HD: " + nodo.getDerecho().getElem();
            } else {
                cad += "      HD:  - ";
            }

            if (nodo.getIzquierdo() != null) {
                cad = toStringAux(nodo.getIzquierdo(), cad);
            }
            if (nodo.getDerecho() != null) {
                cad = toStringAux(nodo.getDerecho(), cad);
            }
        }
        return cad;
    }
    
    public boolean sonIsomorfos(ArbolBin b){
        boolean result = false;
        
        if(!esVacio() && !b.esVacio()){
            if(this.altura() == b.altura()){
                if(esBalanceado() && b.esBalanceado() || !esBalanceado() && !b.esBalanceado()){
                    result = sonIsomorfosAux(this.raiz, b.raiz);
                }
            }
        }
        
        return result;
    }
    
    private boolean sonIsomorfosAux(NodoArbol a, NodoArbol b){
        int aIzq, aDer, bIzq, bDer;
        boolean result = true;
        
        if(a != null && b != null){
            
                aIzq = alturaAux(a.getIzquierdo());
                aDer = alturaAux(a.getDerecho());
                bIzq = alturaAux(b.getIzquierdo());
                bDer = alturaAux(b.getDerecho());
                
                result = aIzq == bIzq && aDer == bDer;
                           
        }
        return result;
    }
    
    @Override
    public ArbolBin clone() {
        //devuelve un clon
        ArbolBin clon = new ArbolBin();
        if (this.raiz != null) {
            clon.raiz = cloneAux(this.raiz);
        }

        return clon;
    }

    private NodoArbol cloneAux(NodoArbol n) {
        NodoArbol nuevo = null;

        if (n != null) {
            nuevo = new NodoArbol(n.getElem());
            if (n.getIzquierdo() != null) {
                nuevo.setIzquierdo(cloneAux(n.getIzquierdo()));
            }
            if (n.getDerecho() != null) {
                nuevo.setDerecho(cloneAux(n.getDerecho()));
            }
        }
        return nuevo;
    }

    
    /*------------------ LISTAR --------------------*/
    public Lista listarPreorden() {
        //retorna una lista con los elementos del arbol en preorden
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis) {
        //metodo recursivo privado

        if (nodo != null) {
            //visita el elemento en el nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1); //1

            //recorre a sus hijos en preorden
            listarPreordenAux(nodo.getIzquierdo(), lis);    //2
            listarPreordenAux(nodo.getDerecho(), lis);      //3
        }
    }

    public Lista listarInorden() {
        //devuelve una lista con los elementos en recorrido inorden
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

    public Lista listarPosorden() {
        //devuelve una lista con los elementos en recorrido posorden
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            listarPosordenAux(nodo.getIzquierdo(), lis);
            listarPosordenAux(nodo.getDerecho(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
        }
    }

    public Lista listarPorNiveles() {
        //devuelve una lista con los elementos en recorrido por niveles
        Cola q = new Cola();
        Lista res = new Lista();
        NodoArbol nodoActual;

        if (this.raiz != null) {
            q.poner(this.raiz);

            while (!q.esVacia()) {
                nodoActual = (NodoArbol) q.obtenerFrente();
                q.sacar();
                res.insertar(nodoActual.getElem(), res.longitud() + 1);

                if (nodoActual.getIzquierdo() != null) {
                    q.poner(nodoActual.getIzquierdo());
                    if (nodoActual.getDerecho() != null) {
                        q.poner(nodoActual.getDerecho());
                    }
                }
            }
        }
        return res;
    }

    
    public boolean esIsomorfo(ArbolBin arbol){
        boolean result = false;
        if(this.raiz != null && arbol.raiz != null){
            if(this.altura() == arbol.altura()){
                result = esIsomorfoAux(this.raiz, arbol.raiz);
            }
        }
        return result;
    }
    
    private boolean esIsomorfoAux(NodoArbol n1, NodoArbol n2){
        boolean result = true;
        
        if(n1 != null && n2 != null && result){
            if(esHoja(n1) == false && esHoja(n2) || esHoja(n1) && esHoja(n2) == false){
                result = false;
            }else if(n1.getDerecho() != null && n2.getDerecho() == null || n1.getDerecho() == null && n2.getDerecho() != null){
                result = false;
            }else if(n1.getIzquierdo() != null && n2.getIzquierdo() == null || n1.getIzquierdo() == null && n2.getIzquierdo() != null){
                result = false;
            }else{
                result = esIsomorfoAux(n1.getIzquierdo(), n2.getIzquierdo());
                if(result){
                    result = esIsomorfoAux(n1.getDerecho(), n2.getDerecho());
                }
            }
            
        }
        
        return result;
    }
    
    public boolean esBalanceado(){
        boolean result = true;
        if(!esVacio()){
            result = esBalanceadoAux(this.raiz) == 0;
        }
        return result;
    }
    
    private int esBalanceadoAux(NodoArbol nodo){
        int result = 0;
        int izq, der;
        if(nodo != null){
            izq = esBalanceadoAux(nodo.getIzquierdo()) + 1;
            der = esBalanceadoAux(nodo.getDerecho()) + 1;
            result = izq - der;
        }
        return result;
    }
    
       //---------------------- EXTRAS ---------------------------
    private boolean esHoja(NodoArbol nodo) {
        boolean exito = false;
        if (nodo != null) {
            exito = (nodo.getDerecho() == null && nodo.getIzquierdo() == null);
        }
        return exito;
    }

    public Lista frontera() {
        Lista lis = new Lista();
        auxFrontera(this.raiz, lis);
        return lis;
    }

    private void auxFrontera(NodoArbol n, Lista lis) {
        NodoArbol nodoIzq;
        NodoArbol nodoDer;

        if (n != null) {
            nodoIzq = n.getIzquierdo();
            nodoDer = n.getDerecho();
            if (nodoIzq == null && nodoDer == null) {
                lis.insertar(n.getElem(), lis.longitud() + 1);
            } else {
                auxFrontera(nodoIzq, lis);
                auxFrontera(nodoDer, lis);
            }
        }
    }
}
