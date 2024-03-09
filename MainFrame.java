import javax.swing.*;

import java.awt.*;
import java.math.BigInteger;

public class MainFrame {

    private static JLabel labelEnterN;
    private static JTextField enterField;
    private static JPanel panel;
    private static JPanel menuPanel;
    private static JButton ok;
    private static JTextArea result;
    private static JTextArea firstNth;
    private static JTextArea history;
    private static JButton hamburgerButton;



    public MainFrame() {


        // Создаем главное окно
        JFrame frame = new JFrame("N-ое число ряда Фибоначчи");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);

        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBounds(0, 0, 200, frame.getHeight());

        ImageIcon icon = new ImageIcon("1.png");
        Image img = icon.getImage() ;
        Image newImg = img.getScaledInstance(40, 30, java.awt.Image.SCALE_SMOOTH) ;
        icon = new ImageIcon(newImg);


        labelEnterN = new JLabel("ENTER N");
        labelEnterN.setFont(new Font("Arial", Font.BOLD, 70));
        labelEnterN.setBounds(frame.getWidth()/2 - 150, 30, 500, 200);

        enterField = new JTextField();
        enterField.setFont(new Font("Arial", Font.PLAIN, 20));
        enterField.setBounds(frame.getWidth()/2 - 130, 220, 265, 35);

        // Создаем кнопку в стиле гамбургера
        hamburgerButton = new JButton(icon);
        hamburgerButton.setFont(new Font("Arial", Font.PLAIN, 20));
        hamburgerButton.setBounds(20, 20, 40, 30);
        hamburgerButton.setVisible(true);
        menuPanel.add(hamburgerButton);

        firstNth = new JTextArea();
        history = new JTextArea();

        firstNth.setVisible(false);
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

        history.setVisible(false);
        history.setEditable(false);
        history.setLineWrap(true);
        history.setWrapStyleWord(true);

        

        JScrollPane scroll = new JScrollPane(
                history,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scroll.setBounds(20, 290, 150, 200); // Задаем расположение
        scroll.setVisible(false);

        firstNth.setBounds(20, 70, 150, 200);
        
        hamburgerButton.addActionListener(e -> toggleMenu(firstNth, history, scroll));

        menuPanel.add(firstNth);
        menuPanel.add(scroll);


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

        frame.add(menuPanel);
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

    private void toggleMenu(Component... components) {
        for (Component component : components) {
            component.setVisible(!component.isVisible());
        }
    }
}