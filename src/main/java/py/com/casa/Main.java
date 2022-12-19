package py.com.casa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static int getMaxElement(double[][] dTable, int pN, int pM) {
        double maxValue = dTable[0][0];
        int maxElement = 0;
        int i = 0, j = 0;
        for (int sn = 0; sn < pN; sn++) {
            for (int sm = 0; sm < pM; sm++) {
                if (dTable[sm][sn] > maxValue) {
                    maxValue = dTable[sm][sn];
                    i = sm;
                    j = sn;
                }
            }
        }
        dTable[i][j] = 0.0;
        return j;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Integer escanos = null;
        String url = null;

        EnumServicios en = EnumServicios.DIPUTADOS_ALTO_PARANA;
        switch (en) {
            case SENADORES:
//                url = "https://resultados.tsje.gov.py/publicacion/dinamics/divulgacion.ajax.php?codeleccion=29&candidatura=2";
                url = "https://resultados.tsje.gov.py/publicacion/dinamics/divulgacion.ajax.php?codeleccion=31&candidatura=2";
                escanos = 45;
                break;
            case DIPUTADOS_CAPITAL:
//                url = "https://resultados.tsje.gov.py/publicacion/dinamics/divulgacion.ajax.php?codeleccion=29&candidatura=3&departamento=0";
                url = "https://resultados.tsje.gov.py/publicacion/dinamics/divulgacion.ajax.php?codeleccion=31&candidatura=3&departamento=0";
                escanos = 7;
                break;
            case DIPUTADOS_CENTRAL:
//                url = "https://resultados.tsje.gov.py/publicacion/dinamics/divulgacion.ajax.php?codeleccion=29&candidatura=3&departamento=11";
                url = "https://resultados.tsje.gov.py/publicacion/dinamics/divulgacion.ajax.php?codeleccion=31&candidatura=3&departamento=11";
                escanos = 20;
                break;
            case DIPUTADOS_ALTO_PARANA:
//                url = "https://resultados.tsje.gov.py/publicacion/dinamics/divulgacion.ajax.php?codeleccion=29&candidatura=3&departamento=11";
                url = "https://resultados.tsje.gov.py/publicacion/dinamics/divulgacion.ajax.php?codeleccion=50&candidatura=3&departamento=10";
                escanos = 8;
                break;
        }

        TsjeCaller tsjeCaller = new TsjeCaller();
        DivulgacionItem item = tsjeCaller.obtenerDivulgacion(url);

        Map<Integer, Queue<DivulgacionItem.CandidatosDTO.CandidatosPrefDTO>> candidatosPriorizados = new HashMap<>();
        Map<Integer, String> partidos = new HashMap<>();


        double[] votos = new double[item.getCandidatos().size()];
        int[] partido = new int[item.getCandidatos().size()];
        int i = 0;
        for (DivulgacionItem.CandidatosDTO candidato : item.getCandidatos()) {

            votos[i] = candidato.getVotos();
            partido[i] = candidato.getOrden();

            Queue<DivulgacionItem.CandidatosDTO.CandidatosPrefDTO> queue = new ArrayDeque<>();
            for (DivulgacionItem.CandidatosDTO.CandidatosPrefDTO candidatosPrefDTO : candidato.getCandidatosPref()) {
                queue.add(candidatosPrefDTO);
            }
            candidatosPriorizados.put(candidato.getOrden(), queue);
            partidos.put(candidato.getOrden(), candidato.getDesPartido());
            i++;
        }


        int[] allocated = new int[votos.length];
        double[][] dhondtTable = new double[escanos][votos.length];

        for (int m = 0; m < escanos; m++) {
            for (int n = 0; n < votos.length; n++) {
                if (m == 0)
                    dhondtTable[m][n] = votos[n];
                else
                    dhondtTable[m][n] = dhondtTable[0][n] / (m + 1);
            }
        }

        StringBuilder cantidatos = new StringBuilder();
        int o = 0;
        for (int c = 1; c < escanos + 1; c++) {
            o = getMaxElement(dhondtTable, votos.length, escanos);
            allocated[o] = allocated[o] + 1;
            DivulgacionItem.CandidatosDTO.CandidatosPrefDTO candidato = candidatosPriorizados.get(partido[o]).poll();
            cantidatos.append(String.format("%d - %s (%s)", c, candidato.getNomCandidato(), candidato.getDesPartido())).append("\n");
        }

        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        out.println(cantidatos);

        StringBuilder resumen = new StringBuilder();

        for (int p = 0; p < votos.length; p++) {
            resumen.append(String.format("Partido: %s obtiene %d escaños", partidos.get(partido[p]), allocated[p])).append("\n");
        }
        out.println(resumen);


    }

}
