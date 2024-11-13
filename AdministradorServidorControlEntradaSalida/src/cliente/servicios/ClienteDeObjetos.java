package cliente.servicios;

import cliente.utilidades.UtilidadesConsola;
import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import java.rmi.RemoteException;
import servidor.controladores.ControladorUsuariosDentroInt;

public class ClienteDeObjetos {

    private static ControladorUsuariosDentroInt objRemoto;

    public static void main(String[] args) throws RemoteException {
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
        direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry del servidor de Entrada y Salida");
        numPuertoRMIRegistry = cliente.utilidades.UtilidadesConsola.leerEntero();

        //paso 4
        objRemoto = (ControladorUsuariosDentroInt) UtilidadesRegistroC.obtenerObjRemoto(
                direccionIpRMIRegistry,
                numPuertoRMIRegistry,
                "objServicioUsuariosDentro");

        boolean bandera = false;
        do {
            System.out.println("======Login======");
            System.out.println("Usuario:");
            String usuario = UtilidadesConsola.leerCadena().toUpperCase();
            System.out.println("Contraseña:");
            String pwd = UtilidadesConsola.leerCadena().toUpperCase();

            boolean longitudValida = UtilidadesConsola.validarLongitudCredenciales(usuario, pwd);
            if (!longitudValida) {
                System.out.println("El usuario y la contraseña deben tener entre 8 y 15 caracteres.");
                continue;
            }

            bandera = objRemoto.existeAdministrador(usuario, pwd);
            if (!bandera) {
                System.out.println("Credenciales Incorrectas...");
            }
        } while (!bandera);

        Menu objMenu = new Menu(objRemoto);
        objMenu.ejecutarMenuPrincipal();

    }

}
