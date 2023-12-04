/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author dawmi
 */
public class UserModel {

    // Atributos y métodos de la clase UserModel
    /**
     * Constructor para crear una nueva instancia de UserModel.
     *
     * @param userId El identificador único del usuario.
     * @param username El nombre de usuario del usuario.
     * @param firstName El primer nombre del usuario.
     * @param lastName El apellido del usuario.
     * @param photoUrl La URL de la foto del usuario.
     * @param email El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * *
     */
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private String email;
    private String password;

    public UserModel(String userId, String username, String firstName, String lastName, String photoUrl, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoUrl = photoUrl;
        this.email = email;
        this.password = password;
    }

    public UserModel(String userId, String firstName, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
