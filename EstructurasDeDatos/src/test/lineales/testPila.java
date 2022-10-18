package test.lineales;

import Estructuras_Lineales.Pila;

/**
 *
 * @author Santiago B.
 */


public class testPila {
    public static void main(String[] args) {
        Pila pila = new Pila();
        Pila clon = new Pila();
        
        System.out.println("//////////////////////// INT ///////////////////////////////");
        System.out.println("\nApilo 3, 5, 7");
        pila.apilar(3);
        pila.apilar(5);
        pila.apilar(7);
        
        clon = test(pila, clon);
        /*
        System.out.println("\n----------------- Metodo capicua -------------------------");
        System.out.println("El clon es capicua? " +esCapicua(clon));
        System.out.println("\n--Le agrego un 3 a la pila de clon--");
        clon.apilar(3);
        System.out.println("La pila resultante es: " +clon.toString());
        System.out.println("Es capicua? " +esCapicua(clon));
        */
        
        System.out.println("\n////////////////////// S T R I N G /////////////////////////");
        System.out.println("Apilo 'hola', 'como', 'estas', 'hoy'");
        pila.apilar("hola");
        pila.apilar("como");
        pila.apilar("estas");
        pila.apilar("hoy");
        
        test(pila, clon);
    
    }
    
    public static Pila test(Pila pila, Pila clon) {
        System.out.println("\n------------------- Muestra en String --------------------");
        System.out.println(pila.toString());
        System.out.println("\nEl tope es: " +pila.obtenerTope());
        
        System.out.println("\n--------------------- Desapilo 7 ------------------------");
        pila.desapilar();
        System.out.println("\nEsta vacía? " +pila.esVacia());
        System.out.println("El tope es: " +pila.obtenerTope());
        
        System.out.println("\n---------------- Clono y muestro clon ------------------");
        clon = pila.clone();
        System.out.println(clon.toString());
        
        System.out.println("\n--------------- Vacío la original y muestro -------------");
        pila.vaciar();
        System.out.println("Esta vacía? " + pila.esVacia());
        
        System.out.println("\n--------------- Intento desapilar la vacía ---------------");
        System.out.println("Se pudo desapilar? " +pila.desapilar());
        System.out.println("El tope es: " +pila.obtenerTope());
        System.out.println("La pila resultante es: " +pila.toString());
        
        System.out.println("\n------------------ Muestro el clon -----------------------");
        System.out.println(clon.toString());
        
        return clon;
    }
    
    public static boolean esCapicua(Pila original) {
        //verifica si una pila de int es capicua
        Pila clon = original.clone();
        int[] arr = new int[30];
        int cont = 0, tope = arr.length-1;
        boolean capicua = false;
        
        while (clon.obtenerTope() != null && cont <= tope) {
            //cargo el arreglo con los topes, es decir, queda invertido
            arr[cont] = (int) clon.obtenerTope();
            clon.desapilar();
            cont++;
        }
        
        for (cont = 0; cont <= tope; cont++) {
            //compara un elemento del arreglo con el inverso(?)
            capicua = (arr[cont] == arr[tope - cont]);
        }
        
        return capicua;
    }
}