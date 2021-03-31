package aufgabe8;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public abstract class CompoundExpression implements Expression {
    protected Expression a;
    protected Expression b;

    public CompoundExpression(Expression k, Expression d) {
        this.a = k;
        this.b = d;
        insert(this);
    }

    private static int getClasses(Expression k) {
        String str = k.getClass().getName();
        switch(str) {
            case "Constant":
                return 1;
            case "Var":
                return 2;
            case "Difference":
            case "Sum":
                return 3;
            case "Product":
            case "Quotient":
                return 4;
            default:
                return 0;
        }
    }

        private class Node {
            Expression data;
            Node left;
            Node right;

            Node(Expression x) {
                data = x;
                left = null;
                right = null;
            }
        }

        private Node root = null;
/*
        public boolean contains(Expression x) {
            return containsR(x, root);
        }

        private boolean containsR(Expression x, Node p) {
            if (p == null)
                return false;
            else if (getClasses(x) < getClasses(p.data))
                return containsR(x, p.left);
            else if (getClasses(x) > getClasses(p.data))
                return containsR(x, p.right);
            else
                return true;
        }
*/
        public void insert(Expression x) {
            root = insertR(x, root);
        }

        private Node insertR(Expression x, Node p) {
            if (p == null)
                p = new Node(x);
            else if (getClasses(x) < getClasses(p.data))
                p.left = insertR(x, p.left);
            else if (getClasses(x) > getClasses(p.data))
                p.right = insertR(x, p.right);

            return p;
        }
/*
        public void remove(Expression x) {
            root = removeR(x, root);
        }

        private Node removeR(Expression x, Node p) {
            if (p == null)
                p = null;
            else if (getClasses(x) < getClasses(p.data))
                p.left = removeR(x, p.left);
            else if (getClasses(x) > getClasses(p.data))
                p.right = removeR(x, p.right);
            else { // Knoten loeschen:
                if (p.left == null || p.right == null)
                    p = (p.left != null) ? p.left : p.right;
                else {
                    p.data = getMin(p.right);
                    p.right = removeR(p.data, p.right);
                }
            }
            return p;
        }

        private Expression getMin(Node p) {
            assert (p != null);
            while (p.left != null)
                p = p.left;
            return p.data;
        }
*/
        void inOrder(Node p) {
            if (p != null) {
                inOrder(p.left);

                inOrder(p.right);
            }
        }

    @Override
    public double eval(Map t){
        return 0;
    }

    @Override
    public Set getVars() {
        Set<String> k = new TreeSet<>();
        if(a.getVars() != null) {
            k.addAll(a.getVars());
        }
        if(b.getVars() != null) {
            k.addAll(b.getVars());
        }
        return k;
    }
}