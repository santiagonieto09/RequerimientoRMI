package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.repositorios.AdministradoresRepositoryInt;
import servidor.repositorios.EntradasRepositoryInt;

public class ControladorUsuariosDentroImpl extends UnicastRemoteObject implements ControladorUsuariosDentroInt {

    private final EntradasRepositoryInt objEntradasRepository;
    private final AdministradoresRepositoryInt objAdministradoresRepository;

    public ControladorUsuariosDentroImpl(EntradasRepositoryInt objEntradasRepository, AdministradoresRepositoryInt objAdministradoresRepository) throws RemoteException {
        super();
        this.objEntradasRepository = objEntradasRepository;
        this.objAdministradoresRepository = objAdministradoresRepository;
    }

    @Override
    public List<UsuarioEntradaSalidaDTO> listarUsuariosDentro() throws RemoteException {
        return objEntradasRepository.listarUsuariosDentro();
    }

    @Override
    public int consultarCantidadUsuariosDentro() throws RemoteException {
        return objEntradasRepository.consultarCantidadUsuariosDentro();
    }

    @Override
    public boolean existeAdministrador(String usuario, String pwd) throws RemoteException {
        return objAdministradoresRepository.existeAdministrador(usuario, pwd);
    }

}
