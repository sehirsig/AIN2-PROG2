package aufgabe8;
import java.util.Map;
import java.util.Set;

public interface Expression {

    public double eval(Map t);

    public Set getVars();

    public String toString();
}
