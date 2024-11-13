package cliente.callBack;

import cliente.DTO.EventoDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ControladorCallbackImpl extends UnicastRemoteObject implements ControladorCallbackInt {

    public ControladorCallbackImpl() throws RemoteException {
        super();
    }

    @Override
    public void notificar(EventoDTO objEvento) throws RemoteException {
        System.out.println("\n-----NUEVA NOTIFICACION-----");
        System.out.println("-Mensaje:" + objEvento.getMensaje());
        if (objEvento.getObjUsuarioEvento() != null) {
            System.out.println("Datos del Usuario:");
            System.out.println("-Identificacion: " + objEvento.getObjUsuarioEvento().getIdentificacion());
            System.out.println("-Nombre(s): " + objEvento.getObjUsuarioEvento().getNombres());
            System.out.println("-Apellido(s): " + objEvento.getObjUsuarioEvento().getApellidos());
            System.out.println("-ROL: " + objEvento.getObjUsuarioEvento().getRol());
        }
        System.out.println("-Acción:" + objEvento.getAccion());
        System.out.println("----------------------------");
    }
}
