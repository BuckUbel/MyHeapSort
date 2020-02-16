package quentin.weber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class View extends JFrame {

    HeapSort hs;

    int width = 0;
    int height = 0;

    JPanel topPanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel textPanel = new JPanel();

    Controller c;


    public View(HeapSort hs, String title, int width, int height) {
        this.hs = hs;
        this.width = width;
        this.height = height;

        this.setLayout(new BorderLayout());


    }

    public void setController(Controller c) {
        this.c = c;
    }

    public void createGUI() {

        this.add(topPanel, BorderLayout.NORTH);
        this.topPanel.setLayout(new GridLayout(1, 4));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1,2));
        this.add(contentPanel, BorderLayout.CENTER);

        contentPanel.add(mainPanel, BorderLayout.WEST);
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        textPanel.setBackground(Color.BLACK);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        JScrollPane sp = new JScrollPane(textPanel);
        sp.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });
        contentPanel.add(sp, BorderLayout.EAST);

        JButton createI = new JButton(Controller.INPUT);
        createI.setActionCommand(Controller.INPUT);
        createI.addActionListener(c);
        topPanel.add(createI);

        JButton createC = new JButton(Controller.CREATE);
        createC.setActionCommand(Controller.CREATE);
        createC.addActionListener(c);
        topPanel.add(createC);

        JButton createSO = new JButton(Controller.SORT);
        createSO.setActionCommand(Controller.SORT);
        createSO.addActionListener(c);
        topPanel.add(createSO);

        JButton createSE = new JButton(Controller.SEEK);
        createSE.setActionCommand(Controller.SEEK);
        createSE.addActionListener(c);
        topPanel.add(createSE);

    }

    public void createBST() {


        this.mainPanel.removeAll();
        String[] levels = hs.stringify().split("\n");

        int count = 0;
        int zaehler = 0;
        JPanel jp;
        String[] elements;
        JLabel jl;

        for (String level : levels) {

            jp = new JPanel();
            elements = level.split(" ");

            for (String element : elements) {
                jl = new JLabel("{" + element + "}");
                jp.add(jl);
                if (!element.equals("")) {
                    zaehler++;
                }
            }
            while (zaehler < count) {
                jl = new JLabel("{ }");
                jp.add(jl);
                zaehler++;
            }
            this.mainPanel.add(jp);
            count = count * 2;
            if (count == 0) {
                count = 1;
            }
            zaehler = 0;
        }
        this.setVisible(true);
    }

    public void write(String s) {
        JLabel text = new JLabel();
        text.setText(s);
        text.setForeground(Color.WHITE);
        this.textPanel.add(text);

        this.setVisible(true);
    }

    public void finish() {

        this.setSize(this.width, this.height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
