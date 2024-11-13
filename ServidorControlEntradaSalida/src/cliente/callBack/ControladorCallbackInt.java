package cliente.callBack;

import cliente.DTO.EventoDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControladorCallbackInt extends Remote {

    public void notificar(EventoDTO objEvento) throws RemoteException;
}
