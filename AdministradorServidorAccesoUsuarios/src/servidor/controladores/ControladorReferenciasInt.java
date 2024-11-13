package servidor.controladores;

import cliente.callBack.ControladorCallbackInt;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControladorReferenciasInt extends Remote {

    public boolean registrarReferencia(ControladorCallbackInt referencia) throws RemoteException;

}
