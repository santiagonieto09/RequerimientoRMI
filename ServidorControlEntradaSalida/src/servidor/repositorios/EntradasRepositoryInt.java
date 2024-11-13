package servidor.repositorios;

import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;

public interface EntradasRepositoryInt {

    public boolean registrarEntrada(UsuarioEntradaSalidaDTO objUsuario);

    public boolean eliminarEntrada(UsuarioEntradaSalidaDTO objUsuario);

    public UsuarioEntradaSalidaDTO consultarUsuarioDentro(int identificacion);

    public int consultarCantidadUsuariosDentro();

    public List<UsuarioEntradaSalidaDTO> listarUsuariosDentro();

    public boolean existeRegistradaIdentificacion(int identificacion);
}
