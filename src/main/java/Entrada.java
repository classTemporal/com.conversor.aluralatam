import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class Entrada {
    private Entrada() {
    }

    private static Object[] possibleValues = {"Conversor de monedas", "Conversor de temperatura"};

    private static void dialogoFinalizado() {
        JOptionPane.showMessageDialog(
                null,
                "Programa finalizado.",
                "Conversor",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void dialogoContinuar() throws IOException {
        if (JOptionPane.showConfirmDialog(null, "Desea continuar?", "Conversor", JOptionPane.YES_NO_OPTION) == 0) {
            startConversor();
        } else {
            dialogoFinalizado();
        }
    }

    private static void getResultadoHaciaPeso(Double x, Double z) throws IOException {
        double tasaCambioMXN = getMoneda("MXN");
        double resultado = z / (x / tasaCambioMXN);

        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        decimalFormat.setGroupingUsed(true);

        String resultadoFormateado = decimalFormat.format(resultado);

        JOptionPane.showMessageDialog(
                null,
                "Resultado de la conversión: " + resultadoFormateado,
                "Conversor",
                JOptionPane.INFORMATION_MESSAGE);
        dialogoContinuar();
    }

    private static void getResultadoDePeso(Double x, Double z) throws IOException {
        double tasaCambioMXN = getMoneda("MXN");
        double resultado = z * (x / tasaCambioMXN);

        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        decimalFormat.setGroupingUsed(true);

        String resultadoFormateado = decimalFormat.format(resultado);

        JOptionPane.showMessageDialog(
                null,
                "Resultado de la conversión: " + resultadoFormateado,
                "Conversor",
                JOptionPane.INFORMATION_MESSAGE);
        dialogoContinuar();
    }

    private static void setMoneda(Double x) throws IOException {
        Map<String, String> conversiones = new LinkedHashMap<>();
        conversiones.put("MXN a USD", "USD");
        conversiones.put("MXN a EUR", "EUR");
        conversiones.put("MXN a GBP", "GBP");
        conversiones.put("MXN a JPY", "JPY");
        conversiones.put("MXN a KRW", "KRW");
        conversiones.put("USD a MXN", "USD");
        conversiones.put("EUR a MXN", "EUR");
        conversiones.put("GBP a MXN", "GBP");
        conversiones.put("JPY a MXN", "JPY");
        conversiones.put("KRW a MXN", "KRW");

        String[] monedas = conversiones.keySet().toArray(new String[0]);

        Object seleccion = JOptionPane.showInputDialog(
                null,
                "Selecciona moneda a cambiar",
                "Conversor",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                monedas,
                monedas[0]);

        String monedaDestino = conversiones.get(seleccion);
        if (seleccion != null && monedaDestino != null) {
            if (seleccion.toString().startsWith("MXN")) {
                getResultadoDePeso(getMoneda(monedaDestino), x);
            } else {
                getResultadoHaciaPeso(getMoneda(monedaDestino), x);
            }
        }
    }

    private static Double getMoneda(String x) throws IOException {
        String urlStr = "https://v6.exchangerate-api.com/v6/5dfd0f04d33d9039288e9296/latest/USD";

        try {

            // Making Request
            URL url = new URL(urlStr);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Convert to JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();
            JsonObject conversion = jsonobj.getAsJsonObject("conversion_rates");
            // Accessing object
            return conversion.get(x).getAsDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // or handle the exception in another way
        }
    }

    private static void cambioTemperatura() throws IOException {
        String[] tipo = {"Celsius a Fahrenheit", "Fahrenheit a Celsius"};

        try {
            String valor = JOptionPane.showInputDialog("Introduce el valor a convertir", "30");

            if (valor != null) {
                Double valorStr = Double.parseDouble(valor);
                Object seleccion = JOptionPane.showInputDialog(
                        null,
                        "Selecciona moneda a cambiar",
                        "Conversor",
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        tipo,
                        tipo[0]);
                if (seleccion == "Celsius a Fahrenheit") {
                    double resultado = valorStr * 9 / 5 + 32;
                    DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
                    decimalFormat.setGroupingUsed(true);

                    String resultadoFormateado = decimalFormat.format(resultado);
                    JOptionPane.showMessageDialog(
                            null,
                            "Resultado: " + resultadoFormateado + " fahrenheit.",
                            "Conversor",
                            JOptionPane.INFORMATION_MESSAGE);
                } else if (seleccion == "Fahrenheit a Celsius") {
                    double resultado = (valorStr - 32) * 5 / 9;
                    DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
                    decimalFormat.setGroupingUsed(true);

                    String resultadoFormateado = decimalFormat.format(resultado);
                    JOptionPane.showMessageDialog(
                            null,
                            "Resultado: " + resultadoFormateado + " celsius.",
                            "Conversor",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    dialogoFinalizado();
                }
                dialogoContinuar();
            } else {
                dialogoFinalizado();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Caracter(es) no valido(s). Asegurate de solo insertar numeros, no simbolos ni letras.",
                    "Conversor",
                    JOptionPane.INFORMATION_MESSAGE);
            System.err.println("Caracter no valido. Asegurarse de que sea solo numeros sin simbolos ni letras.");
            cambioTemperatura();
        }

    }

    private static void cambioMoneda() throws IOException {
        try {
            String valorStr = JOptionPane.showInputDialog("Por favor introduce el valor a convertir", "1");
            if (valorStr != null) {
                setMoneda(Double.parseDouble(valorStr));
            } else {
                dialogoFinalizado();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Caracter(es) no valido(s). Asegurate de solo insertar numeros, no simbolos ni letras.",
                    "Conversor",
                    JOptionPane.INFORMATION_MESSAGE);
            System.err.println("Caracter no valido. Asegurarse de que sea solo numeros sin simbolos ni letras.");
            cambioMoneda();
        }
    }

    public static void startConversor() throws IOException {
        Object seleccion = JOptionPane.showInputDialog(null,
                "Selecciona uno", "Conversor",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);

        if (seleccion == "Conversor de monedas") {
            cambioMoneda();
        } else if (seleccion == "Conversor de temperatura") {
            cambioTemperatura();
        } else {
            dialogoFinalizado();
        }
    }
}
