package aufgabe8;

import java.util.Map;

public class Product extends CompoundExpression {

    public Product(Expression c, Expression d) {
        super(c,d);
    }

    @Override
    public double eval(Map t){
        return a.eval(t) * b.eval(t);
    }

    @Override
    public String toString() {
        return "(" + a.toString() + " * " + b.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Product) {
            Product that = (Product) o;
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
