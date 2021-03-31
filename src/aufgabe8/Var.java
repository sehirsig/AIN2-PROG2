package aufgabe8;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Var implements Expression {
    private String variable;

    public Var (String a) {
        variable = a;
    }

    @Override
    public double eval(Map t){
        if (!t.containsKey(variable)) {
            throw new IllegalArgumentException();
        }
        return (double)t.get(variable);
    }

    @Override
    public Set getVars() {
        Set<String> a = new TreeSet<>();
        a.add(this.variable);
        return a;
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Var) {
            Var that = (Var) o;
            if(that.toString().equals(this.toString())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
