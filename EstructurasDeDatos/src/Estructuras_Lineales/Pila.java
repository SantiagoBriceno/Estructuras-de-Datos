package Estructuras_Lineales;


public class Pila {
    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;
    
    public Pila() {
        this.arreglo = new Object[TAMANIO]; 
        this.tope = -1;
    }
    
    public boolean apilar(Object nuevoElem) {
        //agrega un nuevo elemento al tope de la pila, retorna false si la pila esta llena
        boolean exito;
        
        if (this.tope+1 >= this.TAMANIO)
            //Error: pila llena
            exito = false;
        else {
            //pone el elemento en el tope de la fila e incrementa tope
            this.tope++;
            this.arreglo[tope] = nuevoElem;
            exito = true;
        }
        
        return exito;
    }
    
    public boolean desapilar() {
        //quita el elemento que esta en el tope de la pila, si esta vacia devuelve false
        boolean exito = false;
        
        if (!esVacia()) {
            //quita el elemento que esta en el tope y decrementa tope
            this.arreglo[tope] = null;
            this.tope--;
            exito = true;
        }
        
        return exito;
    }
    
    public Object obtenerTope() {
        //devuelve el objeto que esta en el tope de la pila
        
        Object salida;
        if (tope > -1) {
            salida = this.arreglo[tope];
        } else {
            salida = null;
        }
        return salida;
    }
    
    public boolean esVacia() {
        //devuelve true si esta vacia, false si no
        return (tope == -1);
    }
    
    public void vaciar() {
        //quita todos los elementos de la pila
        while (tope > -1) {
            arreglo[tope] = null;
            tope--;
        }
    }
    
    @Override
    public Pila clone() {
        //devuelve una pila con una copia de la original
        Pila clon = new Pila();
        int cont = 0;
        
        clon.tope = this.tope;
        while (cont <= tope) {
            clon.arreglo[cont] = this.arreglo[cont];
            cont++;
        }
        
        return clon;
    }
    
    @Override
    public String toString () {
        int i;
        String salida;
        
        if (this.tope == -1) {
            salida = "Pila vacia";
        } else {
            salida = "[";
        for (i = 0; i <= tope; i++) {
            salida += this.arreglo[i];
            if (i < tope) {
                salida += ", ";
            }
        }
        salida += "]";
        }
        
        
        return salida;
    }
    
}