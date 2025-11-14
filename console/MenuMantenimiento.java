package hospital.c.console;

import java.util.*;

public class MenuMantenimiento {

    static class WorkOrder {
        String id, assetTag, type, status = "OPEN";
        WorkOrder(String id, String assetTag, String type){ this.id=id; this.assetTag=assetTag; this.type=type; }
        void close(){ status = "CLOSED"; }
        @Override public String toString(){ return id+" | "+assetTag+" | "+type+" | "+status; }
    }

    private final Map<String, WorkOrder> orders = new LinkedHashMap<>();
    private final Scanner in = new Scanner(System.in);

    public static void main(String[] args){ new MenuMantenimiento().start(); }

    void start(){
        int op;
        do{
            System.out.println("=== Mantenimiento (Módulo C) ===");
            System.out.println("1) Crear OT (PM/CM)");
            System.out.println("2) Cerrar OT");
            System.out.println("3) Listar OT");
            System.out.println("4) Salir");
            System.out.print("Opción: ");
            op = readInt();
            switch(op){
                case 1 -> crear();
                case 2 -> cerrar();
                case 3 -> listar();
                case 4 -> System.out.println("Adiós");
                default -> System.out.println("Inválida");
            }
            System.out.println();
        } while(op!=4);
    }

    void crear(){
        System.out.print("ID OT: "); String id = in.nextLine().trim();
        System.out.print("Asset tag: "); String tag = in.nextLine().trim();
        System.out.print("Tipo (PM/CM): "); String type = in.nextLine().trim().toUpperCase();
        orders.put(id, new WorkOrder(id, tag, type));
        System.out.println("OT creada.");
    }

    void cerrar(){
        System.out.print("ID OT: "); String id = in.nextLine().trim();
        WorkOrder w = orders.get(id);
        if(w==null){ System.out.println("No existe"); return; }
        w.close(); System.out.println("OT cerrada.");
    }

    void listar(){ orders.values().forEach(System.out::println); }

    int readInt(){ while(true){ try{ return Integer.parseInt(in.nextLine().trim()); }catch(Exception e){ System.out.print("Número inválido: "); } } }
}
