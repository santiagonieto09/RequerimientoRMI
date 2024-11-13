package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.DTO.UsuarioEntradaSalidaDTO;

public interface ControladorGestionarEntradaSalidaInt extends Remote {

    public int registrarEntrada(int identificacion) throws RemoteException;

    public int registrarSalida(int identificacion) throws RemoteException;

    public UsuarioEntradaSalidaDTO consultarUsuarioDentro(int identificacion) throws RemoteException;
}
