package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.DTO.UsuarioEntradaSalidaDTO;

public interface ControladorGestorUsuariosEntradaSalidaInt extends Remote {

    public boolean registrarUsuarioEntradaSalida(UsuarioEntradaSalidaDTO objUsuario) throws RemoteException;

    public boolean eliminarUsuarioEntradaSalida(int idUsuario) throws RemoteException;

    public UsuarioEntradaSalidaDTO consultarUsuarioEntradaSalida(int identificacion) throws RemoteException;

    public boolean existeAdministrador(String usuario, String pwd) throws RemoteException;

}
