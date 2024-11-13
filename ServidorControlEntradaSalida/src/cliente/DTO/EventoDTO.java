package cliente.DTO;

import java.io.Serializable;
import servidor.DTO.UsuarioEntradaSalidaDTO;

public class EventoDTO implements Serializable {

    private final String mensaje;
    private final String accion;
    private final UsuarioEntradaSalidaDTO objUsuarioEvento;

    public EventoDTO(String mensaje, String accion, UsuarioEntradaSalidaDTO objUsuarioEvento) {
        this.mensaje = mensaje;
        this.accion = accion;
        this.objUsuarioEvento = objUsuarioEvento;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getAccion() {
        return accion;
    }

    public UsuarioEntradaSalidaDTO getObjUsuarioEvento() {
        return objUsuarioEvento;
    }

}
