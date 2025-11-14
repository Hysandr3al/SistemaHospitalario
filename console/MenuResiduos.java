package hospital.c.console;

import java.util.*;

public class MenuResiduos {

    enum Stream { INFECTIOUS, SHARPS, CHEMICAL, PHARMACEUTICAL }

    static class Container {
        String code; Stream stream; int level; // 0-100
        Container(String code, Stream stream, int level){ this.code=code; this.stream=stream; this.level=cap(level); }
        void setLevel(int lvl){ level = cap(lvl); }
        boolean needsPickup(){ return level >= 80; }
        static int cap(int v){ return Math.max(0, Math.min(100, v)); }
        @Override public String toString(){ return code+" | "+stream+" | "+level+"%"+(needsPickup()?" | PICKUP":""); }
    }

    private final Map<String, Container> containers = new LinkedHashMap<>();
    private final Scanner in = new Scanner(System.in);

    public static void main(String[] args){ new MenuResiduos().start(); }

    public MenuResiduos(){
        containers.put("R-01", new Container("R-01", Stream.INFECTIOUS, 75));
        containers.put("R-02", new Container("R-02", Stream.SHARPS, 20));
    }

    void start(){
        int op;
        do{
            System.out.println("=== Residuos (Módulo C) ===");
            System.out.println("1) Listar contenedores");
            System.out.println("2) Actualizar nivel (%)");
            System.out.println("3) Salir");
            System.out.print("Opción: ");
            op = readInt();
            switch(op){
                case 1 -> listar();
                case 2 -> actualizar();
                case 3 -> System.out.println("Adiós");
                default -> System.out.println("Inválida");
            }
            System.out.println();
        } while(op!=3);
    }

    void listar(){ containers.values().forEach(System.out::println); }

    void actualizar(){
        System.out.print("Código: "); String code = in.nextLine().trim();
        Container c = containers.get(code);
        if(c==null){ System.out.println("No existe"); return; }
        System.out.print("Nuevo nivel (0-100): "); int lvl = readInt();
        c.setLevel(lvl);
        System.out.println("Actualizado: "+c);
        if(c.needsPickup()) System.out.println("Aviso: programar retiro (>=80%).");
    }

    int readInt(){ while(true){ try{ return Integer.parseInt(in.nextLine().trim()); }catch(Exception e){ System.out.print("Número inválido: "); } } }
}
