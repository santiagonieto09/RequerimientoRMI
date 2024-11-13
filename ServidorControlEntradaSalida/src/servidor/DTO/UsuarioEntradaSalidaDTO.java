package servidor.DTO;

import java.io.Serializable;
import java.util.Date;

public class UsuarioEntradaSalidaDTO implements Serializable {

    private int identificacion;
    private String nombres;
    private String apellidos;
    private Rol rol;
    private Date fechaEntrada;

    public UsuarioEntradaSalidaDTO(int identificacion, String nombres, String apellidos, Rol rol) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rol = rol;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
}
