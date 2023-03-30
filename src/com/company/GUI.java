package com.company;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class GUI extends JFrame {

    private final Generator generator;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;

    public GUI(){
        generator = new Generator();
    }

    private void setUpGUI(){
        setTitle("CPU Scheduling Algorithm Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel label1 = new JLabel("Enter number of Processes:");
        panel.add(label1);
        textField1 = new JTextField();
        panel.add(textField1);

        JLabel label2 = new JLabel("Enter time for Round Robin:");
        panel.add(label2);
        textField2 = new JTextField();
        panel.add(textField2);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(0, 0));


        JLabel labelNULL = new JLabel("");
        panel.add(labelNULL);

        button = new JButton("Run");
        panel.add(button);
        JLabel label3 = new JLabel("Averege waiting time:");
        panel.add(label3);

        JLabel labelNULL2 = new JLabel("");
        panel.add(labelNULL2);


        label4 = new JLabel(" FSFS: ");
        panel.add(label4);

        label5 = new JLabel(" SJF: ");
        panel.add(label5);

        label6 = new JLabel(" SJFw: ");
        panel.add(label6);

        label7 = new JLabel(" RR: ");
        panel.add(label7);

        add(panel);
        setVisible(true);
        setActionListener();
    }

    private void setActionListener(){
        button.addActionListener(e -> {
            int numberOFProcesses = Integer.parseInt(textField1.getText());
            int timeRR = Integer.parseInt(textField2.getText());
            try {
                double[] result = generator.generate(numberOFProcesses, timeRR);
                double fcfs = result[0];
                double sjf = result[1];
                double sjfw = result[2];
                double rr = result[3];
                DecimalFormat df = new DecimalFormat("#.##");
                String formattedValueFCFS = df.format(fcfs);
                String formattedValueSJF = df.format(sjf);
                String formattedValueSJFW = df.format(sjfw);
                String formattedValueRR  = df.format(rr);

                label4.setText(" FCFS:    " + formattedValueFCFS);
                label5.setText(" SJF:    " + formattedValueSJF);
                label6.setText(" SJFw:    " + formattedValueSJFW);
                label7.setText(" RR:    " + formattedValueRR);

            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }

        });
    }

    public static void main(String[] args) {
        GUI g = new GUI();
        g.setUpGUI();
    }

}
