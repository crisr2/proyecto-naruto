package visitor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import object.*;

public class XMLVisitor implements Visitor {

    private StringBuilder reporte = new StringBuilder();

    @Override
    public void visitarNinja(Ninja ninja) {
        reporte.append("    <ninja>\n")
               .append("      <nombre>").append(ninja.getNombre()).append("</nombre>\n")
               .append("      <rango>").append(ninja.getRango()).append("</rango>\n")
               .append("      <aldea>").append(ninja.getAldea().getNombre()).append("</aldea>\n")
               .append("      <ataque>").append(ninja.getAtaque()).append("</ataque>\n")
               .append("      <defensa>").append(ninja.getDefensa()).append("</defensa>\n")
               .append("      <chakra>").append(ninja.getChakra()).append("</chakra>\n")
               .append("      <jutsus>\n");

        for (Jutsu jutsu : ninja.getJutsus()) {
            reporte.append("        <jutsu>\n")
                   .append("          <nombre>").append(jutsu.getNombre()).append("</nombre>\n")
                   .append("          <tipo>").append(jutsu.getTipo()).append("</tipo>\n")
                   .append("          <poder>").append(jutsu.getdaño()).append("</poder>\n")
                   .append("          <chakra>").append(jutsu.getchakra()).append("</chakra>\n")
                   .append("        </jutsu>\n");
        }
        reporte.append("      </jutsus>\n")
               .append("    </ninja>\n");
    }

    @Override
    public void visitarAldea(Aldea aldea) {
        reporte.append("  <aldea>\n")
               .append("    <nombre>").append(aldea.getNombre()).append("</nombre>\n")
               .append("    <nacion>").append(aldea.getNacion()).append("</nacion>\n")
               .append("    <ninjas>\n");

        for (Ninja n : aldea.getNinjas()) {
            n.accept(this);
        }

        reporte.append("    </ninjas>\n")
               .append("  </aldea>\n");
    }

    @Override
    public void visitarMision(Mision mision) {
        reporte.append("  <mision>\n")
               .append("    <titulo>").append(mision.getNombre()).append("</titulo>\n")
               .append("    <descripcion>").append(mision.getDescripcion()).append("</descripcion>\n")
               .append("    <dificultad>").append(mision.getDificultad()).append("</dificultad>\n")
               .append("    <rangoMinimo>").append(mision.getRangoMinimo()).append("</rangoMinimo>\n")
               .append("    <recompensa>").append(mision.getRecompensa()).append("</recompensa>\n")
               .append("    <participantes>\n");

        boolean hayJonin = mision.getParticipantes().stream()
                .anyMatch(n -> n.getRango() == RangoNinja.Jonin);

        for (Ninja n : mision.getParticipantes()) {
            reporte.append("      <participante>\n")
                   .append("        <nombre>").append(n.getNombre()).append("</nombre>\n")
                   .append("        <rango>");
            if (mision.getDificultad() == RangoMision.C && n.getRango() == RangoNinja.Genin && hayJonin) {
                reporte.append("Genin (Permitido por Jonin)");
            } else {
                reporte.append(n.getRango());
            }
            reporte.append("</rango>\n")
                   .append("      </participante>\n");
        }

        reporte.append("    </participantes>\n")
               .append("  </mision>\n");
    }

    public void exportarTodo(List<Aldea> aldeas, List<Mision> misiones) {
        reporte.setLength(0);
        reporte.append("<informe>\n  <aldeas>\n");

        for (Aldea aldea : aldeas) {
            aldea.accept(this); 
        }

        reporte.append("  </aldeas>\n\n  <misiones>\n");
        for (Mision m : misiones) {
            m.accept(this); 
        }

        reporte.append("  </misiones>\n</informe>");

        guardarEnArchivo("Exportaciones/Informe.xml");
    }

    private void guardarEnArchivo(String ruta) {
        try {
            String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            File archivo = new File(ruta.replace(".xml", "_" + timestamp + ".xml"));
            archivo.getParentFile().mkdirs();

            FileWriter writer = new FileWriter(archivo);
            writer.write(reporte.toString());
            writer.close();

            System.out.println(":D XML exportado en: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("X Error al guardar XML: " + e.getMessage());
        }
    }
}
