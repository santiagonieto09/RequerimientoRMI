package cliente.utilidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UtilidadesConsola {

    public static int leerEntero() {
        String linea = "";
        int opcion = 0;
        boolean valido = false;
        do {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                linea = br.readLine();
                opcion = Integer.parseInt(linea);
                valido = true;
            } catch (Exception e) {
                System.out.println("Error intente nuevamente...");
                valido = false;
            }
        } while (!valido);

        return opcion;

    }

    public static String leerCadena() {
        String linea = "";
        boolean valido = false;
        do {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                linea = br.readLine();
                valido = true;
            } catch (Exception e) {
                System.out.println("Error intente nuevamente...");
                valido = false;
            }
        } while (!valido);

        return linea;

    }

    public static String formatearFecha(Date objFecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "CO"));
        String fechaFormateada = formatoFecha.format(objFecha);
        return fechaFormateada;
    }

    public static String formatearHora(Date objFecha) {
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a", new Locale("es", "ES"));
        String horaFormateada = formatoHora.format(objFecha);
        return horaFormateada;
    }

    public static boolean validarLongitudCredenciales(String usuario, String pwd) {
        boolean longitudValida = usuario.length() >= 8 && usuario.length() <= 15
                && pwd.length() >= 8 && pwd.length() <= 15;

        return longitudValida;
    }

    public static boolean validarLongitudIdentificacion(int identificacion) {
        String identificacionStr = String.valueOf(identificacion);
        return identificacionStr.length() == 8;
    }

}
