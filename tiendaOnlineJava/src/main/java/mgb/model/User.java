package mgb.model;

/**
 * Clase que representa a un usuario en la base de datos.
 */
public class User {

    private int idCliente;  // Cambiado para reflejar el nombre de la columna en la base de datos
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private String apellidos;
    private String direccion;
    private String codigoPostal;
    private String numeroTarjeta;
    private String fotoPerfilURL;

    public User(int idCliente, String nombre, String correoElectronico, String contrasena,
                String apellidos, String direccion, String codigoPostal, String numeroTarjeta,
                String fotoPerfilURL) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.numeroTarjeta = numeroTarjeta;
        this.fotoPerfilURL = fotoPerfilURL;
    }

    public User(int idCliente, String nombre, String correoElectronico, String contrasena, String apellidos) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.apellidos = apellidos;
    }
    
    

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getFotoPerfilURL() {
        return fotoPerfilURL;
    }

    public void setFotoPerfilURL(String fotoPerfilURL) {
        this.fotoPerfilURL = fotoPerfilURL;
    }
}
