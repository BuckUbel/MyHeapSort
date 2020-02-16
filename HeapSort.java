package quentin.weber;

class HeapSort {

    private int[] array;
    private Output output;

    HeapSort(int[] array, Output o) {
        this.setArray(array);
        this.output = o;
    }

    void setArray(int[] array){
        this.array = array;
    }

    /**
     * Diese Funktion gibt lediglich den Heap als baumähnliche Struktur auf der Kommandozeile aus.
     * */
    String stringify() {

        String returnString = "\n";

        int j = 0;
        int count = 1;

        for (int anArray : this.array) {
            returnString = returnString.concat(anArray + " ");
            j++;

            if (j == count) {
                returnString = returnString.concat("\n");
                j = 0;
                count = count * 2;
            }
        }
        return returnString;
    }

    /**
     * Diese Funktion gibt nacheinander die Eleemente des derzeitigen Arrays aus.
     */
    String stringArray() {
        String returnString = "";

        for (int anArray : this.array) {
            returnString = returnString.concat(anArray + " ");
        }
        return returnString;
    }

    /**
     * Diese Funktion leitet den Suchprozess ein und gibt das jeweilige Ergebnis aus.
     * */
    void seek(int value){
        int result = this.heapSeek(value, 0);
        if (result == -1) {
            output.print("Die " + value + " ist nicht in diesem Heap.");
        } else {
            output.print("Die " + value + " ist an Position " + result);
        }
    }

    /**
     * Diese Funktion ruft für jeden Eintrag im Heap die toHeap-Methode auf.
     * Dies muss vom letzten zum ersten Element erfolgen, da sonst die Heap-Bedingungen verletzt werden.
     */
    void heapBuild() {

        for (int node = this.array.length; node >= 0; node--) {
            toHeap(this.array.length, node);
        }
    }

    /**
     * Hier wird ein Elternelement mit seinen Kinderelementen verglichen und  ggf. mit ihnen vertauscht.
     */
    private void toHeap(int heapSize, int node) {

        int nextNode = node;
        int leftChild = 2 * node + 1;
        int rightChild = leftChild + 1;

        if (rightChild < heapSize && this.array[rightChild] > this.array[nextNode]) {
            nextNode = rightChild;
        }

        if (leftChild < heapSize && this.array[leftChild] > this.array[nextNode]) {
            nextNode = leftChild;
        }

        if (nextNode != node) {

            int tmpNodeValue = this.array[node];
            this.array[node] = this.array[nextNode];
            this.array[nextNode] = tmpNodeValue;

            toHeap(heapSize, nextNode);
        }
    }

    /**
     * Diese Funktion sortiert den Heap.
     * Dazu vertauscht es jeweils das letzte Element des realen Heaps mit dem ersten.
     * Das erste wird dann wiederum an das Ende des Heaps in dem Array gespeichert.
     * Dann wird für dieses Element wieder die Heap-Bedingungen durchgesetzt und es versickert.
     * Danach wird dasselbe Verfahren mit dem vorletzen Element durchgeführt.
     * Bis am Ende der Heap leer ist und mit einem sortierten Array gefüllt wurde.
     */
    void heapSort() {

        for (int node = this.array.length - 1; node > 0; node--) {

            int tmpNodeValue = this.array[node];
            this.array[node] = this.array[0];
            this.array[0] = tmpNodeValue;

            this.toHeap(node, 0);
        }

    }

    /**
     * In dieser Funktion wird ein Teilbaum bzw. der gesamte Heap nach einem Wert durchsucht.
     * Wenn allerdings schon ein Element gefunden wurde, welches kleiner ist, muss in diesem Strang nicht weitergesucht werden.
     * Denn alle Elemente darunter sind entweder gleich oder kleiner als dieser Wert.
     * Dies gilt jedoch nur bei Max-Heaps.
     * Min-Heaps sind dementsprechend umgekehrt aufgebaut.
     */
    private int heapSeek(int seekNodeValue, int parentNode) {

        int returnValue = -1;

        /*
         * Überprüfung ob der aktuelle Wert im Elternknoten ist
         */
        if (this.array[parentNode] == seekNodeValue) {
            returnValue = parentNode;

        } else {

            if (this.array[parentNode] <= seekNodeValue) {
                output.print(seekNodeValue + " ist größer als das Wurzelelement und kann daher nicht im Heap vorhanden sein.");
            }

            /*
             * Kinderpositionen werden gespeichert und geprüft
             */
            int leftChild = 2 * parentNode + 1;
            int rightChild = leftChild + 1;
            boolean finish = false;

            if (leftChild >= this.array.length) {
                output.print(this.array[parentNode] + " hat kein linkes Kind.");
            } else if (rightChild >= this.array.length) {
                output.print(this.array[parentNode] + " hat kein rechtes Kind.");
            }
            /*
             * Wenn eines der Kinder im Array liegt und größer oder gleich dem gesuchten Wert ist
             * wird dieses durchsucht.
             * Das resultierende Ergebnis wird rekursiv an die darüberliegende Funktion übergeben.
             */
            if (leftChild < this.array.length && this.array[leftChild] >= seekNodeValue) {

                output.print("[" + seekNodeValue + "] " + this.array[parentNode] + " wurde überprüft. Als nächstes wird das linke Kind überprüft: " + this.array[leftChild]);
                int result = this.heapSeek(seekNodeValue, leftChild);
                if (result != -1) {
                    finish = true;
                    returnValue = result;
                }
            }
            if (rightChild < this.array.length && this.array[rightChild] >= seekNodeValue && !finish) {
                output.print("[" + seekNodeValue + "] " + this.array[parentNode] + " wurde überprüft. Als nächstes wird das rechte Kind überprüft: " + this.array[rightChild]);
                int result = this.heapSeek(seekNodeValue, rightChild);
                if (result != -1) {
                    returnValue = result;
                }
            }
        }
        return returnValue;
    }
}