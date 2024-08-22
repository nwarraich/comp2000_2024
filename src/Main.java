import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    public static void main(String[] args) {
        try {
            Main window = new Main();
            window.run();
        } catch (Exception e) {
            e.printStackTrace(); // Handle any exceptions here
        }
    }

    class Canvas extends JPanel {
        Stage stage;

        public Canvas() {
            setPreferredSize(new Dimension(720, 720));
            try {
                stage = StageReader.readStage("data/stage1.rvb");
            } catch (IOException e) {
                e.printStackTrace(); // Handle the IOException here
                stage = new Stage();
            }

        }

        @Override
        public void paint(Graphics g) {
            if (stage != null) {
                stage.paint(g, getMousePosition());
            }
        }
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(16); // Add a small delay to avoid busy-waiting
            } catch (InterruptedException e) {
                e.printStackTrace(); // Handle InterruptedException
            }
        }
    }
} 

