package servidor.repositorios;

import servidor.DTO.UsuarioEntradaSalidaDTO;

public interface UsuariosRepositoryInt {

    public boolean registrarUsuario(UsuarioEntradaSalidaDTO objUsuario);

    public boolean eliminarUsuario(int idUsuario);

    public UsuarioEntradaSalidaDTO consultarUsuario(int identificacion);
}
