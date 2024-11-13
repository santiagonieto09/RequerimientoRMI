package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.repositorios.AdministradoresRepositoryInt;
import servidor.repositorios.UsuariosRepositoryInt;

public class ControladorGestorUsuariosEntradaSalidaIml extends UnicastRemoteObject implements ControladorGestorUsuariosEntradaSalidaInt {

    private final UsuariosRepositoryInt objUsuariosRepository;
    private final AdministradoresRepositoryInt objAdministradoresRepository;

    public ControladorGestorUsuariosEntradaSalidaIml(UsuariosRepositoryInt objUsuariosRepository, AdministradoresRepositoryInt objAdministradoresRepository) throws RemoteException {
        super();
        this.objUsuariosRepository = objUsuariosRepository;
        this.objAdministradoresRepository = objAdministradoresRepository;
    }

    @Override
    public boolean registrarUsuarioEntradaSalida(UsuarioEntradaSalidaDTO objUsuario) throws RemoteException {
        return this.objUsuariosRepository.registrarUsuario(objUsuario);
    }

    @Override
    public boolean eliminarUsuarioEntradaSalida(int idUsuario) throws RemoteException {
        return this.objUsuariosRepository.eliminarUsuario(idUsuario);
    }

    @Override
    public UsuarioEntradaSalidaDTO consultarUsuarioEntradaSalida(int identificacion) throws RemoteException {
        return this.objUsuariosRepository.consultarUsuario(identificacion);
    }

    @Override
    public boolean existeAdministrador(String usuario, String pwd) throws RemoteException {
        return objAdministradoresRepository.existeAdministrador(usuario, pwd);
    }

}
