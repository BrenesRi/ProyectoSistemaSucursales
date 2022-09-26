package sistemaDeSucursales.presentation.acercaDe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class View {
    private JPanel panel;
    private JLabel Image;

    public View() {
        cargarImagen();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void cargarImagen(){
        Image mapa;
        try {
            mapa = ImageIO.read(new File("resources/empresa.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mapa =  mapa.getScaledInstance(655,368,1);
       Image.setIcon(new ImageIcon(mapa));
    }
}
