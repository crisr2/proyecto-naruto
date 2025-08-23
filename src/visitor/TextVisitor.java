package visitor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import object.*;

public class TextVisitor implements Visitor {

    private StringBuilder reporte = new StringBuilder();

    @Override
    public void visitarNinja(Ninja ninja) {
        reporte.append("\n===== Ninja =====\n");
        reporte.append("Nombre: ").append(ninja.getNombre()).append("\n");
        reporte.append("Rango: ").append(ninja.getRango()).append("\n");
        reporte.append("Aldea: ").append(ninja.getAldea().getNombre()).append("\n");
        reporte.append("Ataque: ").append(ninja.getAtaque()).append("\n");
        reporte.append("Defensa: ").append(ninja.getDefensa()).append("\n");
        reporte.append("Chakra: ").append(ninja.getChakra()).append("\n");
        reporte.append("Jutsus:\n");
        for (Jutsu jutsu : ninja.getJutsus()) {
            reporte.append(" - ").append(jutsu.getNombre())
                    .append(" (").append(jutsu.getTipo()).append(")")
                    .append(" Poder: ").append(jutsu.getdaño())
                    .append(" Chakra: ").append(jutsu.getchakra())
                    .append("\n");
        }
    }

    @Override
    public void visitarAldea(Aldea aldea) {
        reporte.append("\n= Aldea ============================\n");
        reporte.append("Nombre: ").append(aldea.getNombre())
                .append(" (").append(aldea.getNacion()).append(")\n");
        reporte.append("Número de ninjas: ").append(aldea.getNinjas().size()).append("\n");

        for (Ninja n : aldea.getNinjas()) {
            n.accept(this);
        }

        reporte.append("\n======================== Fin Aldea =\n");
    }

    @Override
    public void visitarMision(Mision mision) {
        reporte.append("\n>>> Misión <<<\n");
        reporte.append("Título: ").append(mision.getNombre()).append("\n");
        reporte.append("Descripción: ").append(mision.getDescripcion()).append("\n");
        reporte.append("Dificultad: ").append(mision.getDificultad()).append("\n");
        reporte.append("Rango mínimo: ").append(mision.getRangoMinimo()).append("\n");
        reporte.append("Recompensa: ").append(mision.getRecompensa()).append("\n");
        reporte.append("Participantes (").append(mision.getParticipantes().size()).append("):\n");

        boolean hayJonin = mision.getParticipantes().stream()
                .anyMatch(n -> n.getRango() == RangoNinja.Jonin);

        for (Ninja n : mision.getParticipantes()) {
            if (mision.getDificultad() == RangoMision.C
                    && n.getRango() == RangoNinja.Genin
                    && hayJonin) {
                reporte.append(" - ").append(n.getNombre())
                        .append(" (Genin) [Permitido por presencia de Jonin]\n");
            } else {
                reporte.append(" - ").append(n.getNombre())
                        .append(" (").append(n.getRango()).append(")\n");
            }
        }

        reporte.append(">>> Fin Misión <<<\n");
    }

    public void exportarTodo(List<Aldea> aldeas, List<Mision> misiones) {
        reporte.setLength(0);

        for (Aldea aldea : aldeas) {
            aldea.accept(this);
        }

        reporte.append("\n\n===== MISIONES =====\n");
        for (Mision m : misiones) {
            m.accept(this);
        }

        guardarEnArchivo("Exportaciones/Informe.txt");
    }

    private void guardarEnArchivo(String ruta) {
        try {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            File archivo = new File(ruta.replace(".txt", "_" + timestamp + ".txt"));
            archivo.getParentFile().mkdirs();

            FileWriter writer = new FileWriter(archivo);
            writer.write(reporte.toString());
            writer.close();

            System.out.println(":D TXT exportado en: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("X Error al guardar el informe: " + e.getMessage());
        }
    }
}
