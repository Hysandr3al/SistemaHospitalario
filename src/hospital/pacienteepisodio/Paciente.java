package hospital.pacienteepisodio;

public class Paciente {
    private DatosDemograficos datos;
    private HistoriaClinica historiaClinica;

    public Paciente(String nombre, int edad, String dni, String direccion) {
        this.datos = new DatosDemograficos(nombre, edad, dni, direccion);
        this.historiaClinica = new HistoriaClinica(this);
    }

    public DatosDemograficos getDatos() {
        return datos;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    @Override
    public String toString() {
        return "Paciente: " + datos.toString();
    }
}
