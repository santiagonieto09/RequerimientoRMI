package cliente.servicios;

import cliente.callBack.ControladorCallbackImpl;
import cliente.utilidades.UtilidadesConsola;
import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import servidor.controladores.ControladorGestorUsuariosEntradaSalidaInt;
import servidor.controladores.ControladorReferenciasInt;

import java.rmi.RemoteException;

public class ClienteDeObjetos {

    private static ControladorGestorUsuariosEntradaSalidaInt objRemoto1;
    private static ControladorReferenciasInt objRemoto2;

    public static void main(String[] args) {
        try {
            int numPuertoRMIRegistry1 = 0, numPuertoRMIRegistry2 = 0;
            String direccionIpRMIRegistry = "";

            System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
            direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry del servidor de usuarios");
            numPuertoRMIRegistry1 = cliente.utilidades.UtilidadesConsola.leerEntero();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry del servidor de entrada y salida");
            numPuertoRMIRegistry2 = cliente.utilidades.UtilidadesConsola.leerEntero();

            objRemoto1 = (ControladorGestorUsuariosEntradaSalidaInt) UtilidadesRegistroC.obtenerObjRemoto(
                    direccionIpRMIRegistry,
                    numPuertoRMIRegistry1,
                    "objServicioGestionUsuarios");
            objRemoto2 = (ControladorReferenciasInt) UtilidadesRegistroC.obtenerObjRemoto(
                    direccionIpRMIRegistry,
                    numPuertoRMIRegistry2,
                    "objServicioGestionReferencias");

            ControladorCallbackImpl objRemotoCallBack = new ControladorCallbackImpl();
            objRemoto2.registrarReferencia(objRemotoCallBack);

            boolean bandera = false;
            do {
                System.out.println("======Login======");
                System.out.println("Usuario:");
                String usuario = UtilidadesConsola.leerCadena().toUpperCase();
                System.out.println("Contraseña:");
                String pwd = UtilidadesConsola.leerCadena().toUpperCase();

                // Validar credenciales
                boolean credencialesValidas = UtilidadesConsola.validarLongitudCredenciales(usuario, pwd);
                if (!credencialesValidas) {
                    System.out.println("El usuario y la contraseña deben tener entre 8 y 15 caracteres.");
                    continue;
                }

                bandera = objRemoto1.existeAdministrador(usuario, pwd);
                if (!bandera) {
                    System.out.println("Credenciales Incorrectas...");
                }
            } while (!bandera);

            Menu objMenu = new Menu(objRemoto1);
            objMenu.ejecutarMenuPrincipal();
        } catch (RemoteException e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

}
