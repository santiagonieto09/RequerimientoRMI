package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;

public interface ControladorUsuariosDentroInt extends Remote {

    public List<UsuarioEntradaSalidaDTO> listarUsuariosDentro() throws RemoteException;

    public int consultarCantidadUsuariosDentro() throws RemoteException;

    public boolean existeAdministrador(String usuario, String pwd) throws RemoteException;

}
