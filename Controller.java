package quentin.weber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    static final String INPUT = "INPUT";
    static final String CREATE = "CREATE";
    static final String SORT = "SORT";
    static final String SEEK = "SEEK";

    HeapSort hs;
    View window;
    Output output;

    public Controller(View window, HeapSort hs, Output o){
        this.hs = hs;
        this.window = window;
        this.output = o;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        switch(command){
            case Controller.INPUT:
                int[] array = new int[]{6, 2, 3, 8, 7, 5, 10, 12, 1};
                this.hs.setArray(array);
                this.window.createBST();
                output.print("Heap(unsortiert): " + this.hs.stringify());
                //display the normal array

                break;
            case Controller.CREATE:

                this.hs.heapBuild();
                this.window.createBST();
                output.print("Heap(unsortiert): " + this.hs.stringify());

                // display the normal heap

                break;
            case Controller.SORT:

                this.hs.heapSort();
                this.window.createBST();
                output.print("Heap (sortiert): " + this.hs.stringArray());
                // display the sorted heap

                break;
            case Controller.SEEK:
                this.hs.seek(10);
                this.hs.seek(34);
                // display the steps to seek a number

                break;
            default:
                break;
        }

    }



}
