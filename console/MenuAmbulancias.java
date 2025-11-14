package hospital.c.console;

import java.util.*;

public class MenuAmbulancias {

    static class Run {
        String id, priority, vehicle, status = "CREATED";
        Run(String id, String priority, String vehicle){ this.id=id; this.priority=priority; this.vehicle=vehicle; }
        void dispatch(){ status = "DISPATCHED"; }
        void arrive(){ status = "ARRIVED"; }
        @Override public String toString(){ return id+" | "+priority+" | "+vehicle+" | "+status; }
    }

    private final Map<String, Run> runs = new LinkedHashMap<>();
    private final Scanner in = new Scanner(System.in);

    public static void main(String[] args){ new MenuAmbulancias().start(); }

    void start(){
        int op;
        do{
            System.out.println("=== Ambulancias (Módulo C) ===");
            System.out.println("1) Crear corrida");
            System.out.println("2) Marcar llegada");
            System.out.println("3) Listar corridas");
            System.out.println("4) Salir");
            System.out.print("Opción: ");
            op = readInt();
            switch(op){
                case 1 -> crear();
                case 2 -> llegar();
                case 3 -> listar();
                case 4 -> System.out.println("Adiós");
                default -> System.out.println("Inválida");
            }
            System.out.println();
        } while(op!=4);
    }

    void crear(){
        System.out.print("ID corrida: "); String id = in.nextLine().trim();
        System.out.print("Prioridad (A-E): "); String pr = in.nextLine().trim().toUpperCase();
        System.out.print("Placa vehículo: "); String vh = in.nextLine().trim();
        Run r = new Run(id, pr, vh); r.dispatch(); runs.put(id, r);
        System.out.println("Corrida creada y despachada.");
    }

    void llegar(){
        System.out.print("ID corrida: "); String id = in.nextLine().trim();
        Run r = runs.get(id);
        if(r==null){ System.out.println("No existe"); return; }
        r.arrive(); System.out.println("Marcada como ARRIVED.");
    }

    void listar(){ runs.values().forEach(System.out::println); }

    int readInt(){ while(true){ try{ return Integer.parseInt(in.nextLine().trim()); }catch(Exception e){ System.out.print("Número inválido: "); } } }
}
