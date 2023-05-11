import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Etiqueta extends Thread{

    JFrame frame;
    JLabel blinker;
    JPanel panel;
    Servidor servidor;

    public Etiqueta(){
        frame = new JFrame("Etiqueta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        blinker = new JLabel();
        blinker.setPreferredSize(new Dimension(250, 250));
        blinker.setOpaque(true);
        blinker.setBackground(Color.WHITE);

        panel = new JPanel(new BorderLayout());
        panel.add(blinker, BorderLayout.CENTER);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        servidor = new Servidor();
        servidor.start();
    }

    private void blink(){
        if(servidor.getActivo()) {
            System.out.println(servidor.getActivo());
            while(servidor.getActivo()){
                try {
                    System.out.println(servidor.getActivo());
                    blinker.setBackground(Color.YELLOW);
                    Thread.sleep(500);
                    blinker.setBackground(Color.WHITE);
                    Thread.sleep(500);
                } catch (InterruptedException e) {   
                    e.printStackTrace();
                }
            }
        }
        blinker.setBackground(Color.WHITE); // Asegura que el panel siempre queda en blanco al final
    }

    @Override
    public void run() {
        while(true){
            System.out.println(servidor.getActivo());
            blink();
        }
    }
}