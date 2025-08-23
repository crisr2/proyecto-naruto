package visitor;

import object.Aldea;
import object.Ninja;
import object.Mision;

public interface Visitor {
    void visitarNinja(Ninja ninja);
    void visitarAldea(Aldea aldea);
    void visitarMision(Mision mision);
}
