import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PredictorMarkovSimple {
    public static void main(String[] args) throws IOException {

        // Leer el archivo y guardar todas las palabras en una lista, en el orden en que aparecen
        List<String> palabras = new ArrayList<>();
        BufferedReader lector = new BufferedReader(new FileReader("src/texto.txt"));
        String linea;
        while ((linea = lector.readLine()) != null) {
            linea = linea.toLowerCase().replaceAll("[.,;:!?\"()]", "");
            for (String palabra : linea.split("\\s+")) {
                if (!palabra.isEmpty()) {
                    palabras.add(palabra);
                }
            }
        }
        lector.close();

        // Elegir una palabra inicial al azar entre todas las del texto
        String actual = palabras.get((int) (Math.random() * palabras.size()));
        StringBuilder textoGenerado = new StringBuilder(actual);

        // Generar 20 palabras más, una por vuelta
        for (int contador = 0; contador < 50; contador++) {

            // Recorrer TODO el texto buscando cada vez que aparece "actual",
            // y guardar la palabra que la siguió en cada una de esas veces
            List<String> posiblesSiguientes = new ArrayList<>();
            for (int i = 0; i < palabras.size() - 1; i++) {
                if (palabras.get(i).equals(actual)) {
                    posiblesSiguientes.add(palabras.get(i + 1));
                }
            }

            // Si "actual" nunca apareció seguida de otra palabra, se corta acá
            if (posiblesSiguientes.isEmpty()) {
                break;
            }

            // Elegir una al azar entre las encontradas, y esa pasa a ser la nueva palabra actual
            String siguiente = posiblesSiguientes.get((int) (Math.random() * posiblesSiguientes.size()));
            textoGenerado.append(" ").append(siguiente);
            actual = siguiente;
        }

        System.out.println("Texto generado: " + textoGenerado);
    }
}
