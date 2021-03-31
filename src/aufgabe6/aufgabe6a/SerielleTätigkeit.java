package aufgabe6.aufgabe6a;

public class SerielleTätigkeit extends ZusammengesetzteTätigkeit {



    public double getTime() {
        double time = 0;
        for(var d : meineTätigkeiten)
            time += d.getTime();
        return time;
    }
}
