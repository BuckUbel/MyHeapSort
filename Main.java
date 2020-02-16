package quentin.weber;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

/** Der eigentliche Ablauf des Programmes.
 */
///*
        Output o = new Output();
        int[] array = new int[]{6, 2, 3, 8, 7, 5, 10, 12, 1};
        HeapSort hs = new HeapSort(array, o);
        System.out.println("Array: " + hs.stringify());
        hs.heapBuild();

        System.out.println("Heap(unsortiert): " + hs.stringify());

        hs.seek(10);
        hs.seek(34);

        hs.heapSort();
        System.out.println("Heap (sortiert): " + hs.stringArray());
//*/

/**
 * Ab hier beginnt der Ablauf des Programmes mit grafischem Output.
 * Der eigentliche Ablauf kann durch entsprechendes Drücken der Knöpfe simuliert werden.
 * */
/*
        int[] array = new int[]{6, 2, 3, 8, 7, 5, 10, 12, 1};

        Output o = new Output(false);
        HeapSort hs = new HeapSort(array, o);
        View window = new View(hs, "HeapSort", 800, 600);
        Controller controller = new Controller(window, hs, o);

        o.setWindow(window);

        window.setController(controller);
        window.createGUI();
        window.createBST();
        window.finish();
*/
    }
}

