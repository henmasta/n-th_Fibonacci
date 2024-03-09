import javax.swing.*;

import java.awt.*;
import java.math.BigInteger;

public class MainFrame {

    private static JLabel labelEnterN;
    private static JTextField enterField;
    private static JPanel panel;

    private static JButton ok;
    private static JTextArea result;
    private static JTextArea firstNth;
    private static JTextArea history;



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


        firstNth = new JTextArea();
        history = new JTextArea();

        firstNth.setVisible(true);
        firstNth.setEditable(false);
        firstNth.setText("""
                1. n = 1
                2. n = 1
                3. n = 2
                4. n = 3
                5. n = 5
                6. n = 8
                7. n = 13
                8. n = 21
                9. n = 34
                10. n = 55
                11. n = 89
                12. n = 144
                """);

        history.setVisible(true);
        history.setEditable(false);
        history.setLineWrap(true);
        history.setWrapStyleWord(true);

        

        JScrollPane scroll = new JScrollPane(
                history,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scroll.setBounds(20, 290, 150, 200); // Задаем расположение
        scroll.setVisible(true);

        firstNth.setBounds(20, 70, 150, 200);
        panel.add(firstNth);
        panel.add(scroll);


        ok = new JButton("OK");
        ok.setFont(new Font("Arial", Font.BOLD, 30));
        ok.setBounds(frame.getWidth()/2 - 50, 280, 100, 30);
        frame.getRootPane().setDefaultButton(ok);

        result = new JTextArea();
        result.setLineWrap(true);
        result.setWrapStyleWord(true);

        JScrollPane scrollResult = new JScrollPane(
                result,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scrollResult.setBounds(200, frame.getHeight()-270, frame.getWidth()-400, 130);
        frame.setVisible(true);

        panel.add(labelEnterN);
        panel.add(enterField);
        panel.add(ok);
        panel.add(scrollResult);


        frame.add(panel);

        ok.addActionListener(e -> {

            if (enterField.getText().equals("")) return;

            Load load = new Load();

            new Thread(() -> load.setVisible(true)).start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            double startTime = System.currentTimeMillis();
            BigInteger num = Fibonacci.nthNumFibonacci(
                    Long.parseLong(enterField.getText())
            );
            double endTime = System.currentTimeMillis();

            result.setText( ( (endTime - startTime)/1000 ) + " seconds\n");
            result.append(num.toString());
            String resultHistory = enterField.getText() + " = " + num + "\n\n";
            history.append(resultHistory);

            load.setVisible(false);

            result.setText(num.toString());
        });

        frame.setVisible(true);
    }
}