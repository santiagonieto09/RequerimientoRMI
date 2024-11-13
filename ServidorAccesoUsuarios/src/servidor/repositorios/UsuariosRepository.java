package servidor.repositorios;

import java.util.ArrayList;
import servidor.DTO.UsuarioEntradaSalidaDTO;

public class UsuariosRepository implements UsuariosRepositoryInt {

    private final ArrayList<UsuarioEntradaSalidaDTO> misUsuarios;

    public UsuariosRepository() {
        this.misUsuarios = new ArrayList();
    }

    @Override
    public boolean registrarUsuario(UsuarioEntradaSalidaDTO objUsuario) {
        System.out.println("Entrando a registrar usuario -> " + objUsuario.getNombres() + " " + objUsuario.getApellidos());
        boolean bandera = false;
        objUsuario.setNombres(objUsuario.getNombres().toUpperCase());
        objUsuario.setApellidos(objUsuario.getApellidos().toUpperCase());
        if (this.misUsuarios.size() < 5) {
            bandera = this.misUsuarios.add(objUsuario);
        }

        return bandera;
    }

    @Override
    public boolean eliminarUsuario(int idUsuario) {
        System.out.println("Eliminando entrada " + idUsuario);
        boolean bandera = false;
        for (int i = 0; i < this.misUsuarios.size(); i++) {
            if (this.misUsuarios.get(i).getIdentificacion() == idUsuario) {
                this.misUsuarios.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    @Override
    public UsuarioEntradaSalidaDTO consultarUsuario(int identificacion) {
        System.out.println("Consultar usuario " + identificacion);
        UsuarioEntradaSalidaDTO objUsuario = null;

        for (int i = 0; i < this.misUsuarios.size(); i++) {
            if (this.misUsuarios.get(i).getIdentificacion() == identificacion) {
                objUsuario = this.misUsuarios.get(i);
                break;
            }
        }
        return objUsuario;
    }

}
