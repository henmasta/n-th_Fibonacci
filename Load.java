import javax.swing.*;
import java.awt.*;

public class Load extends JFrame {

    public Load() {

        setTitle("Loading...");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setString("Loading...");
        progressBar.setStringPainted(true);

        add(progressBar, BorderLayout.CENTER);
    }
}