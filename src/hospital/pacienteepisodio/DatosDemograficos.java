package hospital.pacienteepisodio;

public class DatosDemograficos {
    private String nombre;
    private int edad;
    private String dni;
    private String direccion;

    public DatosDemograficos(String nombre, int edad, String dni, String direccion) {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
        this.direccion = direccion;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getDni() { return dni; }
    public String getDireccion() { return direccion; }

    @Override
    public String toString() {
        return nombre + " (" + dni + ")";
    }
}
