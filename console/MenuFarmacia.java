package hospital.c.console;

import java.time.LocalDate;
import java.util.*;

// Menú simple de farmacia para probar inventario en consola
public class MenuFarmacia {

    // Catálogo: GTIN -> nombre
    private final Map<String, String> catalog = new HashMap<>();
    // Stock por ubicación: ubicación -> lista de registros
    private final Map<String, List<Stock>> stockByLocation = new HashMap<>();
    private final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        new MenuFarmacia().start();
    }

    public MenuFarmacia() {
        // Productos de ejemplo
        catalog.put("09506000123457", "Catéter 18G");
        catalog.put("09506000123464", "Guantes Nitrilo M");
    }

    public void start() {
        int op;
        do {
            System.out.println("=== Farmacia (Módulo C) ===");
            System.out.println("1) Ver catálogo");
            System.out.println("2) Ver stock por ubicación");
            System.out.println("3) Ingresar stock");
            System.out.println("4) Reservar stock");
            System.out.println("5) Salir");
            System.out.print("Opción: ");
            op = readInt();

            switch (op) {
                case 1 -> verCatalogo();
                case 2 -> verStock();
                case 3 -> ingresarStock();
                case 4 -> reservarStock();
                case 5 -> System.out.println("Adiós");
                default -> System.out.println("Opción inválida");
            }
            System.out.println();
        } while (op != 5);
    }

    private void verCatalogo() {
        catalog.forEach((gtin, name) -> System.out.println(gtin + " | " + name));
    }

    private void verStock() {
        System.out.print("Ubicación: ");
        String loc = in.nextLine().trim();
        var list = stockByLocation.getOrDefault(loc, List.of());
        if (list.isEmpty()) {
            System.out.println("Sin stock registrado en " + loc);
            return;
        }
        list.forEach(s -> System.out.println(s));
    }

    private void ingresarStock() {
        System.out.print("GTIN: ");
        String gtin = in.nextLine().trim();
        if (!catalog.containsKey(gtin)) { System.out.println("GTIN no existe en catálogo"); return; }

        System.out.print("Ubicación: ");
        String loc = in.nextLine().trim();
        System.out.print("Lote: ");
        String lot = in.nextLine().trim();
        System.out.print("Vence (YYYY-MM-DD): ");
        LocalDate exp = LocalDate.parse(in.nextLine().trim());
        System.out.print("Cantidad: ");
        int qty = readInt();

        var list = new ArrayList<>(stockByLocation.getOrDefault(loc, List.of()));
        list.add(new Stock(gtin, lot, exp, qty));
        stockByLocation.put(loc, list);
        System.out.println("Ingreso registrado.");
    }

    private void reservarStock() {
        System.out.print("GTIN: ");
        String gtin = in.nextLine().trim();
        System.out.print("Ubicación: ");
        String loc = in.nextLine().trim();
        System.out.print("Cantidad: ");
        int qty = readInt();

        var list = new ArrayList<>(stockByLocation.getOrDefault(loc, List.of()));
        for (int i = 0; i < list.size(); i++) {
            var s = list.get(i);
            if (s.gtin.equals(gtin) && s.quantity >= qty) {
                s.quantity -= qty;
                list.set(i, s);
                stockByLocation.put(loc, list);
                System.out.println("Reserva OK");
                return;
            }
        }
        System.out.println("Sin stock suficiente.");
    }

    private int readInt() {
        while (true) {
            try { return Integer.parseInt(in.nextLine().trim()); }
            catch (Exception e) { System.out.print("Número inválido, intenta otra vez: "); }
        }
    }

    // Registro simple de stock para consola
    static class Stock {
        final String gtin;
        final String lot;
        final LocalDate expiry;
        int quantity;
        Stock(String gtin, String lot, LocalDate expiry, int quantity) {
            this.gtin = gtin; this.lot = lot; this.expiry = expiry; this.quantity = quantity;
        }
        @Override public String toString() {
            return gtin + " | lote " + lot + " | vence " + expiry + " | qty " + quantity;
        }
    }
}
