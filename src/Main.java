import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main(){
        super("Browser Launcher");
        setBounds(500, 200, 300, 75);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        JButton text = new JButton("Text Browser"), graph = new JButton("Graphic Browser");
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Browser();
            }
        });
        graph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GraphicBrowser();
            }
        });

        add(text);
        add(graph);
        setVisible(true);
    }

    public static void main(String[] args) {

        new Main();
    }
}
