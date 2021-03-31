package aufgabe6.aufgabe6a;

import java.util.List;

public class ParalleleTätigkeit extends ZusammengesetzteTätigkeit {

    public double getTime() {
        double time = 0;
        for(var d : meineTätigkeiten)
            if (time < d.getTime())
                time = d.getTime();
        return time;
    }
}
