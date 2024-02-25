import javax.swing.*;


import java.awt.*;

import java.math.BigInteger;

public class MainFrame {

    private static JLabel labelEnterN;
    private static JTextField enterField;
    private static JPanel panel;

    private static JButton ok;
    private static JTextArea result;


    public MainFrame() {


        // Создаем главное окно
        JFrame frame = new JFrame("N-ое число ряда Фибоначчи");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);

       
        labelEnterN = new JLabel("ENTER N");
        labelEnterN.setFont(new Font("Arial", Font.BOLD, 70));
        labelEnterN.setBounds(frame.getWidth()/2 - 150, 30, 500, 200);

        enterField = new JTextField();
        enterField.setFont(new Font("Arial", Font.PLAIN, 20));
        enterField.setBounds(frame.getWidth()/2 - 130, 220, 265, 35);
        

        ok = new JButton("OK");
        ok.setFont(new Font("Arial", Font.BOLD, 30));
        ok.setBounds(frame.getWidth()/2 - 50, 280, 100, 30);
        

        result = new JTextArea();
        result.setLineWrap(true);
        result.setWrapStyleWord(true);

        result.setBounds(200, frame.getHeight()-270, frame.getWidth()-400, 130);
        


        frame.setVisible(true);

        panel.add(labelEnterN);
        panel.add(enterField);
        panel.add(ok);
        panel.add(result);


        frame.add(panel);

        ok.addActionListener(e -> {
            if (enterField.getText().equals("")) return;
            BigInteger num = Fibonacci.nthNumFibonacci(
                    Integer.parseInt(enterField.getText())
            );
            result.setText(num.toString());

        });

        frame.setVisible(true);
    }

}
