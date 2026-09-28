import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioVentas {
    private List<VentaCafe> v;

    public ServicioVentas(String arc) throws IOException {
        v = Files.lines(Paths.get(arc)).skip(1).map(l -> l.split(","))
                .filter(d -> d.length >= 7)
                .map(d -> new VentaCafe(Integer.parseInt(d[0]), d[1], Double.parseDouble(d[2]), d[3], d[4], d[5], d[6]))
                .collect(Collectors.toList());
    }

    public void metricasBasicas() {
        System.out.println("Total transacciones: " + v.size());
        System.out.println("\nCatálogo de cafés distintos vendidos:");
        v.stream().map(VentaCafe::getCafe).distinct().sorted().forEach(System.out::println);
    }

    public void filtrosAvanzados() {
        System.out.println("Top 5 compras de Lattes matutinos:");
        v.stream()
                .filter(x -> x.getCafe().equalsIgnoreCase("Latte") && x.getMomentoDia().equalsIgnoreCase("Morning"))
                .limit(5)
                .forEach(x -> System.out.println(x.getCafe() + " pagado con " + x.getTipoPago()));
    }

    public void indicadoresFinancieros() {
        System.out.println("Ingresos Totales: $" + v.stream().mapToDouble(VentaCafe::getDinero).sum());
        System.out.println("Ticket Promedio: $" + v.stream().mapToDouble(VentaCafe::getDinero).average().orElse(0));
        System.out.println("Venta Máxima: $" + v.stream().mapToDouble(VentaCafe::getDinero).max().orElse(0));
    }

    public void agrupaciones() {
        System.out.println("Ventas segmentadas por método de pago:");
        v.stream().collect(Collectors.groupingBy(VentaCafe::getTipoPago, Collectors.counting()))
                .forEach((pago, cant) -> System.out.println(pago + ": " + cant + " transacciones"));
    }
}