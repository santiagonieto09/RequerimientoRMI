package cliente.vista;

import cliente.utilidades.UtilidadesConsola;

import java.rmi.RemoteException;
import java.util.Date;

import servidor.DTO.Rol;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.controladores.ControladorGestorUsuariosEntradaSalidaInt;

public class Menu {

    private final ControladorGestorUsuariosEntradaSalidaInt objRemoto;

    public Menu(ControladorGestorUsuariosEntradaSalidaInt objRemoto) {
        this.objRemoto = objRemoto;
    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("\n=========== Menu =========== ");
            System.out.println("1. Registrar un usuario");
            System.out.println("2. Borrar un usuario");
            System.out.println("3. Salir");
            System.out.println("=============================== ");
            System.out.print("\nDigite una opcion: ");
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
            System.out.println("==Registro del Usuario==");
            System.out.println("\nIngrese la identificacion: ");
            int identificacion = UtilidadesConsola.leerEntero();

            boolean identificacionValida = UtilidadesConsola.validarLongitudIdentificacion(identificacion);
            if (!identificacionValida) {
                System.out.println("La identificación debe tener exactamente 8 dígitos.");
                return;
            }

            UsuarioEntradaSalidaDTO objUsuarioDTO = objRemoto.consultarUsuarioEntradaSalida(identificacion);
            if (objUsuarioDTO != null) {
                System.out.println("El usuario ya se encuentra registrado...");
                return;
            }

            System.out.println("\nIngrese el nombre: ");
            String nombre = UtilidadesConsola.leerCadena();
            System.out.println("\nIngrese el apellido: ");
            String apellido = UtilidadesConsola.leerCadena();
            System.out.println("\nIngrese el rol: ");
            int opc;
            Rol rol = null;
            do {
                System.out.println("\n1. ADMINISTRADOR.");
                System.out.println("\n2. DOCENTE.");
                System.out.println("\n3. ESTUDIANTE.");
                System.out.println("\n Ingrese una opción:");
                opc = UtilidadesConsola.leerEntero();
                switch (opc) {
                    case 1:
                        rol = Rol.ADMINISTRADOR;
                        break;
                    case 2:
                        rol = Rol.DOCENTE;
                        break;
                    case 3:
                        rol = Rol.ESTUDIANTE;
                        break;
                    default:
                        System.out.println("Opcion no valida!");

                }
            } while (opc < 0 || opc > 3);

            // Crear un objeto Date para la fecha actual
            Date currentDate = new Date();

            UsuarioEntradaSalidaDTO objUsuarioEntradaSalidaDTO = new UsuarioEntradaSalidaDTO(identificacion, nombre, apellido, rol);

            boolean bandera = objRemoto.registrarUsuarioEntradaSalida(objUsuarioEntradaSalidaDTO);//invocación del método remoto
            if (bandera) {
                System.out.println("Registro realizado satisfactoriamente...");
            } else {
                System.out.println("No se pudo realizar el registro...");
            }
        } catch (RemoteException e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }

    private void Opcion2() {
        try {
            System.out.println("==Eliminar Usuario==");
            System.out.println("\nIngrese la identificacion: ");
            int identificacion = UtilidadesConsola.leerEntero();
            UsuarioEntradaSalidaDTO objUsuarioDTO = objRemoto.consultarUsuarioEntradaSalida(identificacion);
            if (objUsuarioDTO != null) {
                System.out.println("Esta seguro que desea eliminar el usuario?\n1)Si\n2)No");
                int opcion = UtilidadesConsola.leerEntero();
                switch (opcion) {
                    case 1:
                        boolean bandera = objRemoto.eliminarUsuarioEntradaSalida(identificacion);
                        if (bandera) {
                            System.out.println("Eliminacion realizada satisfactoriamente...");
                        } else {
                            System.out.println("No se pudo realizar la eliminacion...");
                        }
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Opcion no valida!");
                }
            }
        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
    }

}
