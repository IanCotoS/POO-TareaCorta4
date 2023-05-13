import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

public class Boton implements ActionListener{
    JFrame frame;
    JButton button;
    JPanel panel;

    boolean activo = false;
    Socket client;
    ObjectOutputStream output;

    public Boton(){
        frame = new JFrame("Boton");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button = new JButton("Enceder");
        button.addActionListener(this);
        button.setActionCommand("encender");

        panel = new JPanel(new BorderLayout());
        panel.add(button, BorderLayout.CENTER);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void conectar(){
        System.out.println(activo);
        try {
            client = new Socket("127.0.0.1", 5555);
            output = new ObjectOutputStream(client.getOutputStream());
            output.writeObject(activo);
            output.flush(); // Se envía
            output.close();
            client.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int input = JOptionPane.showConfirmDialog(null, 
                "¿Quiere actualizar el estado?", "Seleciona una opción...",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (input == 0){
            if(e.getActionCommand().equals("encender")){
                button.setText("apagar");
                button.setActionCommand("apagar");
                activo = true;
            }
            else{
                button.setText("encender");
                button.setActionCommand("encender");
                activo = false;
            }
            conectar();
        }
        else{
            JOptionPane.showInternalMessageDialog(null, "No se ha actualizado el estado de etiqueta",
            "Acción cancelada del botón", JOptionPane.INFORMATION_MESSAGE); // Para retroalimentar al usuario
        }
    }
}