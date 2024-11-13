package cliente.servicios;

import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import servidor.controladores.ControladorGestionarEntradaSalidaInt;

public class ClienteDeObjetos {

    private static ControladorGestionarEntradaSalidaInt objRemoto;

    public static void main(String[] args) {
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
        direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry del servidor de Entrada y Salida");
        numPuertoRMIRegistry = cliente.utilidades.UtilidadesConsola.leerEntero();

        //paso 4
        objRemoto = (ControladorGestionarEntradaSalidaInt) UtilidadesRegistroC.obtenerObjRemoto(
                direccionIpRMIRegistry,
                numPuertoRMIRegistry,
                "objServicioEntrada");
        Menu objMenu = new Menu(objRemoto);
        objMenu.ejecutarMenuPrincipal();

    }

}
