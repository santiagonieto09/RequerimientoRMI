package servidor.controladores;

import cliente.DTO.EventoDTO;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.repositorios.EntradasRepositoryInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class ControladorGestionarEntradaSalidaImpl extends UnicastRemoteObject implements ControladorGestionarEntradaSalidaInt {

    private final EntradasRepositoryInt objEntradasRepository;
    private final ControladorGestorUsuariosEntradaSalidaInt objRemotoServidorUsuarios;
    private final ControladorReferenciasImpl objRemoto2;

    public ControladorGestionarEntradaSalidaImpl(
            EntradasRepositoryInt objEntradasRepository,
            ControladorGestorUsuariosEntradaSalidaInt objRemotoServidorUsuarios,
            ControladorReferenciasImpl objRemoto2) throws RemoteException {
        super();
        this.objEntradasRepository = objEntradasRepository;
        this.objRemotoServidorUsuarios = objRemotoServidorUsuarios;
        this.objRemoto2 = objRemoto2;
    }

    @Override
    public int registrarEntrada(int identificacion) throws RemoteException {
        System.out.println("Usuario con identificacion " + identificacion + " esta intentando entrar.");
        int codigo = 0;

        EventoDTO objEventoDTO = null;
        UsuarioEntradaSalidaDTO usuario = objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(identificacion);
        if (usuario == null) {

            // el usuario no existe se retorna 1
            codigo = 1;
            objEventoDTO = new EventoDTO("Entrada fallida, el usuario no existe.", "Entrada", usuario);

        } else {

            if (objEntradasRepository.existeRegistradaIdentificacion(identificacion)) {

                //Si el usuario existe y está adentro se retorna 2
                codigo = 2;
                objEventoDTO = new EventoDTO("Entrada fallida, el usuario ya esta dentro.", "Entrada", usuario);

            } else {
                //Si el usuario existe y no está entry se retorna 3
                usuario.setFechaEntrada(new Date());
                objEntradasRepository.registrarEntrada(usuario);
                codigo = 3;
                objEventoDTO = new EventoDTO("Entrada exitosa.", "Entrada", usuario);
            }
        }
        this.objRemoto2.notificar(objEventoDTO);
        return codigo;
    }

    @Override
    public int registrarSalida(int identificacion) throws RemoteException {

        int codigo = 0;
        EventoDTO objEventoDTO = null;
        UsuarioEntradaSalidaDTO usuario = objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(identificacion);
        if (usuario == null) {

            // el usuario no existe se retorna 1
            codigo = 1;
            objEventoDTO = new EventoDTO("Salida fallida, el usuario no existe.", "Salida", usuario);

        } else {

            if (!objEntradasRepository.existeRegistradaIdentificacion(identificacion)) {

                //Si el usuario existe y no está adentro se retorna 2
                codigo = 2;
                objEventoDTO = new EventoDTO("Salida fallida, el usuario no esta dentro.", "Salida", usuario);

            } else {
                //Si el usuario existe y está adentro se retorna 3
                objEntradasRepository.eliminarEntrada(usuario);
                objEventoDTO = new EventoDTO("Salida exitosa.", "Salida", usuario);
                codigo = 3;
            }
        }
        this.objRemoto2.notificar(objEventoDTO);
        return codigo;
    }

    @Override
    public UsuarioEntradaSalidaDTO consultarUsuarioDentro(int identificacion) throws RemoteException {
        return objEntradasRepository.consultarUsuarioDentro(identificacion);
    }

}
