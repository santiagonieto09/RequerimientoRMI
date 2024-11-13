package servidor.DTO;

public class AdministradorUsuarios {

    private String usuario;
    private String pwd;

    public AdministradorUsuarios(String usuario, String pwd) {
        this.usuario = usuario;
        this.pwd = pwd;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPwd() {
        return pwd;
    }

}
