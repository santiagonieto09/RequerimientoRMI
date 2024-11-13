package servidor.servicios;

import servidor.repositorios.EntradasRepositoryImpl;
import servidor.controladores.ControladorGestionarEntradaSalidaImpl;
import servidor.controladores.ControladorGestorUsuariosEntradaSalidaInt;
import servidor.controladores.ControladorReferenciasImpl;
import servidor.utilidades.UtilidadesRegistroC;
import servidor.utilidades.UtilidadesRegistroS;
import servidor.utilidades.UtilidadesConsola;

import java.rmi.RemoteException;
import servidor.controladores.ControladorUsuariosDentroImpl;
import servidor.repositorios.AdministradoresRepository;

public class ServidorDeObjetos {

    private static ControladorGestorUsuariosEntradaSalidaInt objRemotoC;

    public static void main(String args[]) throws RemoteException {

        int numPuertoRMIRegistry1 = 0, numPuertoRMIRegistry2 = 0;
        String direccionIpRMIRegistry = "";

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry del servidor de entrada y salida");
        numPuertoRMIRegistry1 = UtilidadesConsola.leerEntero();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry del servidor de usuarios");
        numPuertoRMIRegistry2 = UtilidadesConsola.leerEntero();

        ControladorReferenciasImpl objRemoto1
                = new ControladorReferenciasImpl();
        ControladorGestorUsuariosEntradaSalidaInt objRemotoServidorUsuarios = obtenerReferenciaServidorUsuarios(direccionIpRMIRegistry, numPuertoRMIRegistry2, "objServicioGestionUsuarios");
        EntradasRepositoryImpl objRepositorio = new EntradasRepositoryImpl();//se leasigna el puerto de escucha del objeto remoto
        AdministradoresRepository objRepositorioAdmin = new AdministradoresRepository();
        ControladorGestionarEntradaSalidaImpl objRemoto2
                = new ControladorGestionarEntradaSalidaImpl(objRepositorio, objRemotoServidorUsuarios, objRemoto1);
        ControladorUsuariosDentroImpl objRemoto3
                = new ControladorUsuariosDentroImpl(objRepositorio, objRepositorioAdmin);

        try {
            UtilidadesRegistroS.
                    arrancarNS(numPuertoRMIRegistry1);
            UtilidadesRegistroS.
                    RegistrarObjetoRemoto(objRemoto1, direccionIpRMIRegistry, numPuertoRMIRegistry1, "objServicioGestionReferencias");
            UtilidadesRegistroS.
                    RegistrarObjetoRemoto(objRemoto2, direccionIpRMIRegistry, numPuertoRMIRegistry1, "objServicioEntrada");
            UtilidadesRegistroS.
                    RegistrarObjetoRemoto(objRemoto3, direccionIpRMIRegistry, numPuertoRMIRegistry1, "objServicioUsuariosDentro");

        } catch (Exception e) {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" + e.getMessage());
        }

    }

    public static ControladorGestorUsuariosEntradaSalidaInt obtenerReferenciaServidorUsuarios(String dirIPNS, int puertoNS, String idObjetoRemoto) {
        return (ControladorGestorUsuariosEntradaSalidaInt) UtilidadesRegistroC.obtenerObjRemoto(dirIPNS, puertoNS, idObjetoRemoto);
    }
}
