package servidor.servicios;

import servidor.controladores.ControladorGestorUsuariosEntradaSalidaIml;
import servidor.utilidades.UtilidadesRegistroS;
import servidor.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.repositorios.AdministradoresRepository;
import servidor.repositorios.UsuariosRepository;

public class ServidorDeObjetos {

    public static void main(String args[]) throws RemoteException {

        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry del servidor de Usuarios");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero();

        UsuariosRepository objRepositoryUsers = new UsuariosRepository();
        AdministradoresRepository objRepositoryAdmin = new AdministradoresRepository();
        ControladorGestorUsuariosEntradaSalidaIml objRemoto = new ControladorGestorUsuariosEntradaSalidaIml(objRepositoryUsers, objRepositoryAdmin);

        try {
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioGestionUsuarios");

        } catch (Exception e) {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" + e.getMessage());
        }

    }
}
