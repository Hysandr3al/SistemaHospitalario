package Sujetos;

import java.util.ArrayList;

public class HistoriaClinica {

    private Paciente paciente;
    private ArrayList<Episodio> episodios;

    public HistoriaClinica(Paciente paciente) {
        this.paciente = paciente;
        this.episodios = new ArrayList<>();
    }

    public void agregarEpisodio(Episodio e) {
        episodios.add(e);
    }

    public ArrayList<Episodio> getEpisodios() {
        return episodios;
    }

    @Override
    public String toString() {
        return "Historia clínica de " + paciente.nombres + " " + paciente.apellidos +
               " (" + episodios.size() + " episodios registrados)";
    }
}
