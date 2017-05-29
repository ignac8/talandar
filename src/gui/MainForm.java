package gui;

import bwmcts.sparcraft.SparcraftUI;
import jnibwapi.JNIBWAPI;
import sandbox.ForwardEngineering;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainForm {
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JTextField labelTextField1;
    private JTextField labelTextField2;
    private JTextField labelTextField3;
    private JTextField labelTextField4;
    private JTextField labelTextField5;
    private JTextField labelTextField6;
    private JTextField labelTextField7;
    private JTextField labelTextField8;
    private JTextField labelTextField9;
    private JTextField labelTextField10;
    private JTextField labelTextField11;
    private JTextField labelTextField12;
    private JTextField labelTextField13;
    private JTextField labelTextField14;
    private JTextField labelTextField15;
    private JTextField labelTextField16;
    private JButton button1;
    private JButton button2;
    private SparcraftUI sparcraftUI;
    private static JNIBWAPI bwapi;
    private ExecutorService executorService;

    public MainForm() {


        executorService = Executors.newSingleThreadExecutor();
        Replay replay = new Replay();


        button1.addActionListener(e -> ForwardEngineering.main());
        button2.addActionListener(e -> executorService.submit(replay));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Talandar");
        frame.setContentPane(new MainForm().panel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        sparcraftUI = SparcraftUI.getUI(false);
    }
}
