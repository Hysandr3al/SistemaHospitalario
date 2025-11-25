package Sujetos;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Sistema Hospitalario: Módulo Paciente y Episodio ===");

        Paciente p1 = new Paciente(
                "Ana", "López", "987654321",
                "12345678", "Femenino", 32,
                "O+", "Ninguna"
        );

        p1.getHistoriaClinica().agregarEpisodio(new CitaAmbulatoria("Consulta general"));
        p1.getHistoriaClinica().agregarEpisodio(new Hospitalizacion("Ingreso por neumonía", 203));
        p1.getHistoriaClinica().agregarEpisodio(new Cirugia("Apendicectomía", "General"));
        p1.getHistoriaClinica().agregarEpisodio(new Urgencia("Dolor torácico", "Rojo"));

        System.out.println(p1);
        System.out.println(p1.getHistoriaClinica());

        for (Episodio e : p1.getHistoriaClinica().getEpisodios()) {
            System.out.println(" - " + e);
        }
    }
}
