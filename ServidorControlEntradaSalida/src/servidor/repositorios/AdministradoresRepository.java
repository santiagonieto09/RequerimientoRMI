package servidor.repositorios;

import java.util.ArrayList;
import servidor.DTO.AdministradorUsuarios;

public class AdministradoresRepository implements AdministradoresRepositoryInt {

    private final ArrayList<AdministradorUsuarios> misUsuarios;

    public AdministradoresRepository() {
        this.misUsuarios = new ArrayList();
        this.misUsuarios.add(new AdministradorUsuarios("JANIER123", "12345678"));
        this.misUsuarios.add(new AdministradorUsuarios("SANTIAGO", "01234567"));
    }

    @Override
    public boolean existeAdministrador(String usuario, String pwd) {
        for (AdministradorUsuarios admin : misUsuarios) {
            if (admin.getUsuario().equals(usuario) && admin.getPwd().equals(pwd)) {
                return true;
            }
        }
        return false;
    }
}
