package cliente.vista;

import java.rmi.RemoteException;
import cliente.utilidades.UtilidadesConsola;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.controladores.ControladorUsuariosDentroInt;

public class Menu {

    private final ControladorUsuariosDentroInt objRemoto;

    public Menu(ControladorUsuariosDentroInt objRemoto) {
        this.objRemoto = objRemoto;
    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("\n=========== Menu =========== ");
            System.out.println("1. Listar Usuarios Dentro de las instalaciones.");
            System.out.println("2. Salir");
            System.out.println("========================== ");
            System.out.println("\nDigite una Opcion: ");
            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    Opcion1();
                    break;
                case 2:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 2);
    }

    private void Opcion1() {
        try {
            List<UsuarioEntradaSalidaDTO> listUsuarios = objRemoto.listarUsuariosDentro();
            System.out.println("\nCodigo\t\t Hora Entrada \t\t Fecha Entrada");
            listUsuarios.forEach(usuario -> {
                System.out.println(usuario.getIdentificacion() + "\t  "
                        + UtilidadesConsola.formatearHora(usuario.getFechaEntrada()) + "\t\t" + UtilidadesConsola.formatearFecha(usuario.getFechaEntrada()));
            });
            int tamanio = objRemoto.consultarCantidadUsuariosDentro();
            System.out.println("\nCantidad de usuarios al interior de las instalaciones: " + tamanio);
        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
    }

}
