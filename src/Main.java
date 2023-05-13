// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Interfaz gui = new Interfaz();
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setBounds(200, 200, 800, 500);
                gui.setVisible(true);
            }
        });
    }
}
