/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras_Lineales;

/**
 *
 * @author Usuario
 */
public class Cola {

    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    public Cola() {
        //Crea y devuelve una cola vacia
        this.arreglo = new Object[this.TAMANIO];
        this.frente = 0;
        this.fin = 0;
        //como son iguales entonces esta vacia
    }

    public boolean poner(Object nuevo) {
        //pone el elemento al final de la cola. Devuelve verdadero si el elemento
        //se pudo agregar a la estructura y falso en caso contrario

        boolean exito = false;
        if ((this.fin + 1) % this.TAMANIO != this.frente) {
            arreglo[this.fin] = nuevo;
            this.fin = (this.fin + 1) % this.TAMANIO;
            exito = true;
        }
        return exito;
    }

    public boolean sacar() {
        //Saca el elemento que esta en el frente de la cola. Devuelve verdadero
        //si el elemento se pudo sacar (la cola no estaba vacia)

        boolean exito = true;
        if (this.esVacia()) //la cola esta vacia, reporta error
        {
            exito = false;
        } else {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAMANIO;
        }
        return exito;

    }

    public Object obtenerFrente() {
        //devuelve el elemento que esta en el frente. Precondicion: la cola no esta vacia
        return this.arreglo[this.frente];
    }

    public boolean esVacia() {
        //devuelve verdadero si la cola no tiene elementos, falso en caso contrario
        return (this.frente == this.fin);
    }

    public void vaciar() {
        //Saca todos los elementos de la estructura
        while (arreglo[frente] != null) {
            arreglo[frente] = null;
            frente = (this.frente + 1) % this.TAMANIO;
        }
    }

    @Override
    public Cola clone() {
        //devuelve una copia exacta de los datos en la estructura original
        Cola clon = new Cola();
        int cont = frente;
        if (!esVacia()) {
            clon.frente = this.frente;
            clon.fin = this.fin;
            while (cont != fin) {
                clon.arreglo[cont] = this.arreglo[cont];
                cont = (cont + 1) % this.TAMANIO;
            }
        }
        return clon;
    }

    @Override
    public String toString() {
        //Crea y devuelve una cadena de caracteres formada por todos los elementos
        //de la cola
        int i = this.frente;
        String salida;

        if (esVacia()) {
            salida = "Cola vacia";
        } else {
            salida = "[";
            while (i != this.fin) {
                salida += this.arreglo[i];
                i = (i + 1) % this.TAMANIO;
                if (i != fin) {
                    salida += ", ";
                }
            }
            salida += "]";
        }
        return salida;
    }

}
