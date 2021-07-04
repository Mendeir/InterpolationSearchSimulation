

import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import javax.swing.*;

public class PanelNumbers extends JPanel{



    JLabel labelIndex = new JLabel();
    int size;

    //System.out.println(getNum());

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gcs = (Graphics2D) g;
        Graphics2D gci = (Graphics2D) g;
        System.out.println(size);
        for(int i=1;i<=10;i++) {
            gci.drawString(Integer.toString(i-1),i*63,100);
            gci.setBackground(Color.black);
            gcs.setStroke(new BasicStroke(2));
            gcs.drawRect(i*60, 120, 60, 60);
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

}
