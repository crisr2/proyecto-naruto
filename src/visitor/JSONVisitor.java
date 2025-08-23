package visitor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import object.*;

public class JSONVisitor implements Visitor {

    private StringBuilder reporte = new StringBuilder();

    @Override
    public void visitarNinja(Ninja ninja) {
        reporte.append("{\n")
               .append("  \"nombre\": \"").append(ninja.getNombre()).append("\",\n")
               .append("  \"rango\": \"").append(ninja.getRango()).append("\",\n")
               .append("  \"aldea\": \"").append(ninja.getAldea().getNombre()).append("\",\n")
               .append("  \"ataque\": ").append(ninja.getAtaque()).append(",\n")
               .append("  \"defensa\": ").append(ninja.getDefensa()).append(",\n")
               .append("  \"chakra\": ").append(ninja.getChakra()).append(",\n")
               .append("  \"jutsus\": [\n");

        List<Jutsu> jutsus = ninja.getJutsus();
        for (int i = 0; i < jutsus.size(); i++) {
            Jutsu j = jutsus.get(i);
            reporte.append("    {\n")
                   .append("      \"nombre\": \"").append(j.getNombre()).append("\",\n")
                   .append("      \"tipo\": \"").append(j.getTipo()).append("\",\n")
                   .append("      \"poder\": ").append(j.getdaño()).append(",\n")
                   .append("      \"chakra\": ").append(j.getchakra()).append("\n")
                   .append("    }");
            if (i < jutsus.size() - 1) reporte.append(",");
            reporte.append("\n");
        }
        reporte.append("  ]\n}");
    }

    @Override
    public void visitarAldea(Aldea aldea) {
        reporte.append("{\n")
               .append("  \"nombre\": \"").append(aldea.getNombre()).append("\",\n")
               .append("  \"nacion\": \"").append(aldea.getNacion()).append("\",\n")
               .append("  \"ninjas\": [\n");

        List<Ninja> ninjas = aldea.getNinjas();
        for (int i = 0; i < ninjas.size(); i++) {
            ninjas.get(i).accept(this); // Usamos accept()
            if (i < ninjas.size() - 1) reporte.append(",");
            reporte.append("\n");
        }
        reporte.append("  ]\n}");
    }

    @Override
    public void visitarMision(Mision mision) {
        reporte.append("{\n")
               .append("  \"titulo\": \"").append(mision.getNombre()).append("\",\n")
               .append("  \"descripcion\": \"").append(mision.getDescripcion()).append("\",\n")
               .append("  \"dificultad\": \"").append(mision.getDificultad()).append("\",\n")
               .append("  \"rangoMinimo\": \"").append(mision.getRangoMinimo()).append("\",\n")
               .append("  \"recompensa\": ").append(mision.getRecompensa()).append(",\n")
               .append("  \"participantes\": [\n");

        List<Ninja> participantes = mision.getParticipantes();
        boolean hayJonin = participantes.stream().anyMatch(n -> n.getRango() == RangoNinja.Jonin);

        for (int i = 0; i < participantes.size(); i++) {
            Ninja n = participantes.get(i);
            reporte.append("    {\n")
                   .append("      \"nombre\": \"").append(n.getNombre()).append("\",\n")
                   .append("      \"rango\": \"");
            if (mision.getDificultad() == RangoMision.C && n.getRango() == RangoNinja.Genin && hayJonin) {
                reporte.append("Genin (Permitido por presencia de Jonin)");
            } else {
                reporte.append(n.getRango());
            }
            reporte.append("\"\n    }");
            if (i < participantes.size() - 1) reporte.append(",");
            reporte.append("\n");
        }

        reporte.append("  ]\n}");
    }

    public void exportarTodo(List<Aldea> aldeas, List<Mision> misiones) {
        reporte.setLength(0);
        reporte.append("{\n  \"aldeas\": [\n");

        for (int i = 0; i < aldeas.size(); i++) {
            aldeas.get(i).accept(this);
            if (i < aldeas.size() - 1) reporte.append(",");
            reporte.append("\n");
        }

        reporte.append("  ],\n  \"misiones\": [\n");

        for (int i = 0; i < misiones.size(); i++) {
            misiones.get(i).accept(this);
            if (i < misiones.size() - 1) reporte.append(",");
            reporte.append("\n");
        }

        reporte.append("  ]\n}");

        guardarEnArchivo("Exportaciones/Informe.json");
    }

    private void guardarEnArchivo(String ruta) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            File archivo = new File(ruta.replace(".json", "_" + timestamp + ".json"));
            archivo.getParentFile().mkdirs();

            FileWriter writer = new FileWriter(archivo);
            writer.write(reporte.toString());
            writer.close();

            System.out.println(":D JSON exportado en: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("X Error al guardar JSON: " + e.getMessage());
        }
    }
}
