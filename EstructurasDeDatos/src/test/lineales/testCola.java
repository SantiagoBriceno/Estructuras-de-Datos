package test.lineales;
/**
 *
 * @author Santiago B. 
 * 
 */

 import Estructuras_Lineales.Cola;

 public class testCola {
    public static void main(String[] args) {
        Cola cola = new Cola();
        Cola clon = new Cola();
        
        System.out.println("//////////////////////// INT ///////////////////////////////");
        System.out.println("\nPongo 3, 5, 7");
        cola.poner(3);
        cola.poner(5);
        cola.poner(7);
        
        clon = test(cola, clon);
        System.out.println("\n////////////////////// S T R I N G /////////////////////////");
        System.out.println("Pongo 'hola', 'como', 'estas', 'hoy'");
        cola.poner("hola");
        cola.poner("como");
        cola.poner("estas");
        cola.poner("hoy");
        
        test(cola, clon);
    }
    public static Cola test(Cola cola, Cola clon) {
        System.out.println("\n------------------- Muestra en String --------------------");
        System.out.println(cola.toString());
        System.out.println("\nEl frente es: " +cola.obtenerFrente());
        
        System.out.println("\n--------------------- Saco el frente (3) ------------------------");
        cola.sacar();
        System.out.println("\nEsta vacía? " +cola.esVacia());
        System.out.println("El frente es: " +cola.obtenerFrente());
        
        System.out.println("\n---------------- Clono y muestro clon ------------------");
        clon = cola.clone();
        System.out.println(clon.toString());
        
        System.out.println("\n--------------- Vacío la original y muestro -------------");
        cola.vaciar();
        System.out.println("Esta vacía? " + cola.esVacia());
        
        System.out.println("\n--------------- Intento quitar de la vacía ---------------");
        System.out.println("Se pudo sacar el frente? " +cola.sacar());
        System.out.println("El frente es: " +cola.obtenerFrente()+ ". (En este hay una precondicion).");
        System.out.println("La pila resultante es: " +cola.toString());
        
        System.out.println("\n------------------ Muestro el clon -----------------------");
        System.out.println(clon.toString());
        
        return clon;
    }
}