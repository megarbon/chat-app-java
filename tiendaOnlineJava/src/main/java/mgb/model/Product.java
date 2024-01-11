package mgb.model;

/**
 * Clase que representa un producto en la base de datos.
 */
public class Product {

    // Atributos
    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String marca;
    private String categoria;

    // Constructor
    public Product(int idProducto, String nombre, String descripcion, double precio, int stock, String marca, String categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.marca = marca;
        this.categoria = categoria;
    }

    public Product() {
    }

    // Métodos de acceso
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Faltan Otros métodos (por ejemplo, métodos para gestionar el stock, calcular descuentos, etc.)
}
