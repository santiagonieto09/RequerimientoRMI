package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.util.Date;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.controladores.ControladorGestionarEntradaSalidaInt;

public class Menu {

    private final ControladorGestionarEntradaSalidaInt objRemoto;

    public Menu(ControladorGestionarEntradaSalidaInt objRemoto) {
        this.objRemoto = objRemoto;
    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("\n=========== Menu =========== ");
            System.out.println("1. Entrar a las instalaciones");
            System.out.println("2. Salir de las instalaciones");
            System.out.println("3. Salir");
            System.out.println("=============     ============= ");
            System.out.println("\nDigite una Opcion: ");
            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    Opcion1();
                    break;
                case 2:
                    Opcion2();
                    break;
                case 3:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 3);
    }

    private void Opcion1() {
        try {
            System.out.println("Digite la identificacion: ");
            int identificacion = UtilidadesConsola.leerEntero();

            boolean identificacionValida = UtilidadesConsola.validarLongitudIdentificacion(identificacion);
            if (!identificacionValida) {
                System.out.println("La identificación debe tener exactamente 8 dígitos.");
                return;
            }

            int resultado = objRemoto.registrarEntrada(identificacion);
            UsuarioEntradaSalidaDTO objUsuario = objRemoto.consultarUsuarioDentro(identificacion);
            switch (resultado) {
                case 1:
                    System.out.println("Error, el usuario no existe.");
                    break;
                case 2:
                    System.out.println("Error, el usuario existe y esta dentro.");
                    break;
                case 3:
                    System.out.println("\n\tAcceso Concedido");
                    System.out.println(objUsuario.getRol());
                    System.out.println(objUsuario.getNombres() + " " + objUsuario.getApellidos());
                    System.out.println("Hora y fecha de acceso: " + UtilidadesConsola.formatearHora(objUsuario.getFechaEntrada()) + ", " + UtilidadesConsola.formatearFecha(objUsuario.getFechaEntrada()));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
    }

    private void Opcion2() {
        try {
            System.out.println("Digite la identificacion: ");
            int identificacion = UtilidadesConsola.leerEntero();

            boolean identificacionValida = UtilidadesConsola.validarLongitudIdentificacion(identificacion);
            if (!identificacionValida) {
                System.out.println("La identificación debe tener exactamente 8 dígitos.");
                return;
            }
            UsuarioEntradaSalidaDTO objUsuario = objRemoto.consultarUsuarioDentro(identificacion);
            int resultado = objRemoto.registrarSalida(identificacion);
            switch (resultado) {
                case 1:
                    System.out.println("Error, el usuario no existe.");
                    break;
                case 2:
                    System.out.println("Error, el usuario existe y no esta dentro.");
                    break;
                case 3:
                    Date currentDate = new Date();
                    System.out.println("\n\tSalida Concedida");
                    System.out.println(objUsuario.getRol());
                    System.out.println(objUsuario.getNombres() + " " + objUsuario.getApellidos());
                    System.out.println("Hora y fecha de salida: " + UtilidadesConsola.formatearHora(currentDate) + ", " + UtilidadesConsola.formatearFecha(currentDate));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
    }

}
