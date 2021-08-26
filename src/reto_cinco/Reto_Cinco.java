
package reto_cinco;

import Controlador.Controlador;
import Modelo.Empleados;
import Vista.Ventana;

public class Reto_Cinco {

    
    public static void main(String[] args) {
        // TODO code application logic here
        Empleados modelo = new Empleados();
        Ventana vista = new Ventana();
        
        Controlador ctrl = new Controlador(modelo,vista);
        ctrl.iniciar();
    }
    
}
