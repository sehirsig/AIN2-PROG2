package aufgabe8;

import java.util.Map;
import java.util.Set;

public class Constant implements Expression {
    private double a;

    public Constant(double b) {
        this.a = b;
    }

    @Override
    public double eval(Map t){
        return a;
    }

    @Override
    public Set getVars() {
        return null;
    }

    @Override
    public String toString() {
        Double u = this.a;
        return u.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Constant) {
            Constant that = (Constant) o;
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
