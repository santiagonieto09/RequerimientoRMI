package servidor.repositorios;

import servidor.DTO.UsuarioEntradaSalidaDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntradasRepositoryImpl implements EntradasRepositoryInt {

    private final ArrayList<UsuarioEntradaSalidaDTO> usuariosDentro;

    public EntradasRepositoryImpl() {
        this.usuariosDentro = new ArrayList();
    }

    @Override
    public boolean registrarEntrada(UsuarioEntradaSalidaDTO objUsuario) {
        System.out.println("Registrando entrada de usuario con identificacion " + objUsuario.getIdentificacion());
        objUsuario.setFechaEntrada(new Date());
        return this.usuariosDentro.add(objUsuario);
    }

    @Override
    public boolean eliminarEntrada(UsuarioEntradaSalidaDTO objUsuario) {
        System.out.println("Eliminando entrada de usuario con identificacion " + objUsuario.getIdentificacion());
        boolean bandera = false;
        for (int i = 0; i < this.usuariosDentro.size(); i++) {
            if (this.usuariosDentro.get(i).getIdentificacion() == objUsuario.getIdentificacion()) {
                this.usuariosDentro.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    @Override
    public boolean existeRegistradaIdentificacion(int identificacion) {
        System.out.println("Consultando entrada de usuario con identificacion " + identificacion);
        boolean bandera = false;
        for (int i = 0; i < this.usuariosDentro.size(); i++) {
            if (this.usuariosDentro.get(i).getIdentificacion() == identificacion) {
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    @Override
    public int consultarCantidadUsuariosDentro() {
        return usuariosDentro.size();
    }

    @Override
    public List<UsuarioEntradaSalidaDTO> listarUsuariosDentro() {
        return usuariosDentro;
    }

    @Override
    public UsuarioEntradaSalidaDTO consultarUsuarioDentro(int identificacion) {
        System.out.println("Consultar usuario " + identificacion);
        UsuarioEntradaSalidaDTO objUsuario = null;

        for (int i = 0; i < this.usuariosDentro.size(); i++) {
            if (this.usuariosDentro.get(i).getIdentificacion() == identificacion) {
                objUsuario = this.usuariosDentro.get(i);
                break;
            }
        }
        return objUsuario;
    }
}
