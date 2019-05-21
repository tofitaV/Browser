import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Browser extends JFrame {

    private JTextField urlField;
    private JTextPane codeViewer;
    private JPanel panel;
    private StyledDocument doc;

    public Browser() {
        super("Лучший браузер ХНТУ");
        setBounds(150, 50, 1024, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InitComponents();
        doc = codeViewer.getStyledDocument();
        setVisible(true);
        OkayGoogle();
    }

    private void InitComponents() {
        panel = new JPanel(new BorderLayout());
        setContentPane(panel);

        urlField = new JTextField(40);
        urlField.setBounds(0, 0, 640, 20);

        codeViewer = new JTextPane();
        codeViewer.setEditable(false);


        panel.add(urlField, BorderLayout.NORTH);
        panel.add(new JScrollPane(codeViewer), BorderLayout.CENTER);

        InitAction();
    }

    private void InitAction() {
        urlField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Search();
                }
            }
    });
    }

    private void Search() {
        URL url = null;
        String urlName = urlField.getText();
        codeViewer.setText("Loading...");

        try {
            url = new URL("https://" + urlName + "/");
        } catch (MalformedURLException ex) {
            codeViewer.setText("Некорректно заданы протокол, доменное имя или путь к файлу");
        }
            if (url == null) {
                throw new RuntimeException();
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line = "";
                while ((line = br.readLine()) != null) {
                    doc.insertString(codeViewer.getCaretPosition(), line + '\n', null);
                }
            } catch (Exception e) {
                codeViewer.setText(e.getMessage());
            }
    }

    private void OkayGoogle(){
        urlField.setText("www.google.com/");
        URL url = null;
        String google = "https://www.google.com/";
        codeViewer.setText("Loading...");

        try {
            url = new URL(google);
        } catch (MalformedURLException ex) {
            codeViewer.setText("Google сдох:(");
        }
        if (url == null) {
            throw new RuntimeException();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                doc.insertString(codeViewer.getCaretPosition(), line + '\n', null);
            }
        } catch (Exception e) {
            codeViewer.setText(e.getMessage());
        }
    }

}