package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private JTextField barva = new JTextField( 10);
    private JTextField red = new JTextField("0",10);
    private JTextField green = new JTextField("0",10);
    private JTextField blue = new JTextField("0",10);



    List<Barva> seznamBarev = new ArrayList<>();
    TabulkaBarev tabulkaBarev = new TabulkaBarev(seznamBarev);
    JPanel barevnyPanel = new JPanel();


    public MainFrame() {
        setTitle("RGB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1000,600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());


        JLabel lbBarva = new JLabel("Barva");
        lbBarva.setLabelFor(barva);
        panel.add(lbBarva);
        panel.add(barva);

        JLabel lbRed = new JLabel("Red");
        lbRed.setLabelFor(red);
        panel.add(lbRed);
        panel.add(red);

        JLabel lbGreen = new JLabel("Green");
        lbRed.setLabelFor(green);
        panel.add(lbGreen);
        panel.add(green);

        JLabel lbBlue = new JLabel("Blue");
        lbRed.setLabelFor(blue);
        panel.add(lbBlue);
        panel.add(blue);


        JButton vlozit = new JButton("Vložit");
        vlozit.addActionListener((e -> {
            pridatBarvu(new Barva(barva.getText(), Integer.parseInt(red.getText()), Integer.parseInt(green.getText()), Integer.parseInt(blue.getText())));
            Color color = new Color(Integer.parseInt(red.getText()), Integer.parseInt(green.getText()), Integer.parseInt(blue.getText()));
            barevnyPanel.setBackground(color);

            barva.setText("");
            red.setText("0");
            green.setText("0");
            blue.setText("0");
        }));
        panel.add(vlozit);

        JButton exportJSON = new JButton("Export JSON");
        exportJSON.addActionListener((e -> {
            JsonCode.saveJSON(seznamBarev);
        }));
        panel.add(exportJSON);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(500,this.getHeight());
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,2));
        panel1.add(barevnyPanel);
        panel1.add(scrollPane);


        table.setModel(tabulkaBarev);
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Barva selectedBarva = seznamBarev.get(selectedRow);
                        barva.setText(selectedBarva.getNazev());
                        red.setText(String.valueOf(selectedBarva.getRed()));
                        green.setText(String.valueOf(selectedBarva.getGreen()));
                        blue.setText(String.valueOf(selectedBarva.getBlue()));

                        Color color = new Color(Integer.parseInt(red.getText()), Integer.parseInt(green.getText()), Integer.parseInt(blue.getText()));
                        barevnyPanel.setBackground(color);
                    }
                }
            }
        });



        barevnyPanel.setSize(200, this.getHeight());



        getRootPane().setDefaultButton(vlozit);
        add(panel, BorderLayout.NORTH);
        add(panel1, BorderLayout.CENTER);

    }

    private void pridatBarvu(Barva barva){
        seznamBarev.add(barva);
        tabulkaBarev.fireTableDataChanged();
    }

}
