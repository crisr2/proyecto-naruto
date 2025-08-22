package visitor;

import object.Aldea;
import object.Ninja;

public interface Visitor {
    void visitarNinja(Ninja ninja);
    void visitarAldea(Aldea aldea);
}
