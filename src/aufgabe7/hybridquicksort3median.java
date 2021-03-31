package aufgabe7;
import java.util.Arrays;

public class hybridquicksort3median {
    public static <T extends Comparable<T>> void quickSort(T[] a) {
        quickSort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void quickSort(T[] a, int li, int re) {
        if (re - li < 100) {
            insertionSort(a, li, re);
            return;
        }
        while (re > li) {

            // Teileschritt:
            int i = partition(a, li, re);

            // Herrscheschritt:
            if (i - li < re - i) {
                quickSort(a, li, i - 1);
                li = i + 1;
            } else {
                quickSort(a, i + 1, re);
                re = i - 1;
            }
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T[] a, int li, int re) {
        for (int i = li + 1; i < re + 1; i++) {
            //Füge a[i] in a[0] ... a[i-1]
            // an der richtigen Stelle ein:
            T v = a[i];
            int j = i - 1;
            while (j >= li && a[j].compareTo(v) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = v;
        }
    }

    public static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static <T extends Comparable<T>> int partition(T[] a, int li, int re) {
        T k1 = a[li];
        T k2 = a[(li + re) / 2];
        T k3 = a[re];
        if (k1.compareTo(k2) < 0) { // k1 < k2
            if (k1.compareTo(k3) < 0) { // k1 < k3
                if (k2.compareTo(k3) < 0) { // k2 < k3
                    a[re] = k2;
                    a[(li + re) / 2] = k3;
                } else {    // k2 > k3
                    // Alles bleibt so, da k3 median
                }
            } else { //k1 ist größer/gleich k3 und kleiner k2
                a[li] = k3;
                a[re] = k1;
            }
        } else { // k1 größer k2
            if (k1.compareTo(k3) < 0) { // k1 < k3
                a[li] = k3;
                a[re] = k1;
            } else { // k1 > k3
                if (k2.compareTo(k3) < 0) { // k2 < k3
                    // Alles bleibt so, da k3 median ist
                } else {
                    a[re] = k2;
                    a[(li + re) / 2] = k3;
                }
            }
        }

        //System.out.println(Arrays.toString(a));

        T v = a[re]; // Pivotelement
        int i = li - 1;
        int j = re;

        while (true) {
            do i++; while (a[i].compareTo(v) < 0);
            do j--; while (j >= li && a[j].compareTo(v) > 0);
            if (i >= j)
                break;
            swap(a, i, j);
        }

        // Pivotelement v= a[re] und a[i] vertauschen:
        a[re] = a[i];
        a[i] = v;

        return i;
    }
}
