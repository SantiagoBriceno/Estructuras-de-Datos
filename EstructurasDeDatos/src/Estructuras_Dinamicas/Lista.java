
package Estructuras_Dinamicas;

/**
 *
 * @author Santiago B.
 */
public class Lista {
    private Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }
    
    /*METODO TRADICIONAL DE INSERCION DE ELEMENTOS EN LISTAS ORDENADAS.
    CON LA MODIFICACION DE QUE EL ELEMENTO INGRESADO NO SEA IGUAL A ALGUNO QUE CONTENGA LA LISTA
    */
    public boolean insertar(Object nuevoElem, int pos) {
        //detecta y reporta error posicion invalida
        //o si el elemento ya fue ingresado.
        boolean exito = true;

        if ((pos < 1) || (pos > this.longitud() + 1) /*|| this.existe(nuevoElem)*/) {
            exito = false;
        } else {
            if (pos == 1) { //crea un nuevo nodo y se enlaza en la cabecera
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
            } else { //avanza hasta el elemento en posicion pos - 1
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                //crea el nodo y lo enlaza
                Nodo nuevo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }
        return exito;
    }

    //METODO TRADICIONAL DE ELMINACION POR POSICION EN LISTA ORDENADA.
    public boolean eliminar(int pos) {
        //Borra el elemento de la posicion pos. La lista no debe estar
        //vacia, y la posicion recibida debe ser 1 <= pos <= len(lista)
        boolean exito = true;
        if (esVacia()) {
            exito = false;
        } else {
            if (pos < 1 || pos > this.longitud() + 1) {
                exito = false;
            } else {
                if (pos == 1) { //se actualiza la cabecera para que referencie al segundo nodo
                    this.cabecera = this.cabecera.getEnlace();
                } else {
                    Nodo aux = this.cabecera; //se avanza hasta la posicion pos - 1
                    int i = 1;
                    while (i < pos - 1) {
                        aux = aux.getEnlace();
                        i++;
                    } //se reconecta el nodo aux al que sigue del que sigue
                    aux.setEnlace(aux.getEnlace().getEnlace());
                }

            }
        }
        return exito;
    }
    
    //METODO TRADICIONAL DE RECUPERACION DE UN ELEMENTO POR POSICION INDICADA.
    public Object recuperar(int pos) {
        //Devuelve el elemento de la posicion pos
        Object s;
        if (pos < 1 || pos > this.longitud()) {
            //si la posicion no es valida, devuelve null
            s = null;
        } else {
            Nodo aux = this.cabecera; //se avanza hasta la posicion pos
            int i = 1;
            while (i < pos) {
                aux = aux.getEnlace();
                i++;
            }
            s = aux.getElem();
        }
        return s;
    }

    //METODO QUE RETORNA LA POSICION EXACTA DEL OBJETO INDICADO
    public int localizar(Object elem) {
        //En caso de no encontrarlo devuelve  -1.
        int pos = -1;
        int cont = 1;
        Nodo aux = this.cabecera;

        if (!esVacia()) {
            while (aux != null && pos == -1) {
                if (aux.getElem() == elem) {
                    pos = cont;
                }
                aux = aux.getEnlace();
                cont++;
            }
        }
        return pos;
    }
    
    //METODO AGREGADO A LOS TRADICIONALES DE LISTA ORDENADA
    //QUE RETORNA UN BOOLEANO SI EL ELEMENTO SE ENCUENTRA EN LA LISTA.
    public boolean existe(Object elem){
        boolean result = false;
        Nodo aux = this.cabecera;
        //precondición: la lista no debe ser vacía
        if (!esVacia()) {
            while (aux != null && result == false) {
                if (aux.getElem() == elem) {
                    result = true;
                }
                aux = aux.getEnlace();
            }
        }
        return result;
    }

    //METODO QUE RETORNA LA LONGITUD DE LA LISTA. PUDO HABERSE TRABAJADO COMO
    //ATRIBUTO DEL OBJETO LISTA.
    public int longitud() {
        int pos = 1;
        Nodo aux = this.cabecera;

        if (esVacia()) {
            pos = 0;
        } else {
            while (aux.getEnlace() != null) {
                aux = aux.getEnlace();
                pos++;
            }
        }
        return pos;
    }

    public boolean esVacia() {
        //chequear
        return (this.cabecera == null);
    }

    public void vaciar() {
        this.cabecera = null;
    }

    //METODO QUE CLONA POR VALOR AL OBJETO
    @Override
    public Lista clone() {
        Lista clon = new Lista();

        if (!this.esVacia()) {
            Nodo copia = new Nodo(this.cabecera.getElem(), null);
            clon.cabecera = copia;
            Nodo original = this.cabecera.getEnlace();
            Nodo nuevo;

            while (original != null) {
                nuevo = new Nodo(original.getElem(), null);
                copia.setEnlace(nuevo);
                copia = copia.getEnlace();
                original = original.getEnlace();
            }
        }
        return clon;

    }

    //METODO QUE RETORNA UN STRING PREDICEÑADO DE LA LISTA ORDENADA
    @Override
    public String toString() {
        String s = "Lista vacia";
        Nodo aux = this.cabecera;

        if (!esVacia()) {
            s = "[";
            while (aux != null) {
                s += aux.getElem();
                aux = aux.getEnlace();

                if (aux != null) {
                    s += ", ";
                }
            }
            s += "]";
        }
        return s;
    }
    
    //METODO QUE CARGA UNA LISTA MEDIANTE EL INGRESO POR PARAMETROS DE UN ARREGLO
    public void preCargar(Object[] arreglo) {
        for (int i = 0; i < arreglo.length; i++) {
            this.insertar(arreglo[i], i + 1);
        }
    }

    public Lista obtenerMultiplos(int num) {
        int i = 1;
        Lista lis = new Lista();
        boolean primera = false;

        if (!esVacia()) {
            Nodo copia = null;
            Nodo nuevo;
            Nodo aux = this.cabecera;

            while (aux != null) {
                if ((i % num) == 0) {
                    nuevo = new Nodo(aux.getElem(), null);
                    if (!primera) {
                        primera = true;
                        lis.cabecera = nuevo;
                        copia = new Nodo(aux.getElem(), null);
                    }
                    copia.setEnlace(nuevo);
                    copia = copia.getEnlace();
                }
                aux = aux.getEnlace();
                i++;
            }
        }
        return lis;
    }

    public void eliminarApariciones(Object x) {
        Nodo aux = this.cabecera;

        if (!esVacia()) {
            while (aux != null) {
                if (aux.getElem().equals(x)) {
                    this.cabecera = this.cabecera.getEnlace();
                } else {
                    if ((aux.getEnlace() != null) && aux.getEnlace().getElem().equals(x)) {
                        aux.setEnlace(aux.getEnlace().getEnlace());

                    }
                }
                aux = aux.getEnlace();
            }
        }
    }

    public boolean insertarPosSiguiente(Object elem1, Object elem2) {
        boolean exito = false;

        if (this.cabecera != null) {
            Nodo aux = this.cabecera;
            if (this.cabecera.equals(elem1)) {
                Nodo cabecera = new Nodo(elem2, this.cabecera);
                this.cabecera = cabecera;
            }
            while (aux != null) {
                if (aux.getElem().equals(elem1)) {
                    Nodo nuevo = new Nodo(elem2, aux.getEnlace());
                    aux.setEnlace(nuevo);
                }
                aux = aux.getEnlace();
            }
            exito = true;
        }
        return exito;
    }

}
