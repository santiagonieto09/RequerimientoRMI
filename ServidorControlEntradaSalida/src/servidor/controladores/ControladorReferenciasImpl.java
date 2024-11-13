package servidor.controladores;

import cliente.DTO.EventoDTO;
import cliente.callBack.ControladorCallbackInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class ControladorReferenciasImpl extends UnicastRemoteObject implements ControladorReferenciasInt {

    private LinkedList<ControladorCallbackInt> referencias;

    public ControladorReferenciasImpl() throws RemoteException {
        super();
        referencias = new LinkedList();
    }

    @Override
    public boolean registrarReferencia(ControladorCallbackInt referencia) throws RemoteException {
        return this.referencias.add(referencia);
    }

    public void notificar(EventoDTO objEvento) {
        referencias.forEach(
                ref -> {
                    try {
                        ref.notificar(objEvento); //aqui se presenta el callback
                    } catch (Exception e) {
                        System.out.println("Administrador no existe.");
                    }
                });
    }

}
