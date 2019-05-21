import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GraphicBrowser extends JFrame {

    private JTextField urlField;
    private JEditorPane codeViewer;
    private JPanel panel;

    public GraphicBrowser() {
        super("Лучший браузер ХНТУ");
        setBounds(150, 50, 1024, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InitComponents();
        setVisible(true);
        OkayGoogle();
    }

    private void InitComponents() {
        panel = new JPanel(new BorderLayout());
        setContentPane(panel);

        urlField = new JTextField(40);
        urlField.setBounds(0, 0, 640, 20);

        codeViewer = new JEditorPane();
        codeViewer.setEditable(false);
        codeViewer.setContentType("text/html");
        codeViewer.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if ( e.getEventType() != HyperlinkEvent.EventType.ACTIVATED)
                    return;
                try {
                    codeViewer.setText("Loading...");
                    urlField.setText(e.getURL().toString());
                    codeViewer.setPage(e.getURL());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

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
            codeViewer.setPage(url);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void OkayGoogle(){
        urlField.setText("www.google.com/");
        URL url = null;
        String google = "https://www.google.com/";
        codeViewer.setText("Loading...");

        try {
            url = new URL(google);
            codeViewer.setPage(url);
        } catch (Exception ex) {
            codeViewer.setText("Google сдох:(");
        }
    }

}