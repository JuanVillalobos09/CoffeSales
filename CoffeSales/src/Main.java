import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    try {
      Scanner sc = new Scanner(System.in);
      ServicioVentas s = new ServicioVentas("data/Coffe_sales.csv");
      int op;
      do {
        System.out.println("\n--- MENU CAFE ---");
        System.out.println("1. Metricas Basicas (Lectura, limpieza, distintos)");
        System.out.println("2. Filtros Avanzados (Latte + Mañana)");
        System.out.println("3. Indicadores Financieros (Sum, Avg, Max)");
        System.out.println("4. Agrupaciones Comerciales (Pagos)");
        System.out.println("0. Salir");
        op = sc.nextInt();
        if(op==1) s.metricasBasicas();
        else if(op==2) s.filtrosAvanzados();
        else if(op==3) s.indicadoresFinancieros();
        else if(op==4) s.agrupaciones();
      } while(op != 0);
    } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
  }
}