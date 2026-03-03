import Vehicles.PointD;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

class ImageObject {
    private final Point position;
    private BufferedImage image;
    public ImageObject(int x, int y, String imgSource) {
        this.position = new Point(x,y);
        if  (imgSource != null) {
            try {
                this.image = ImageIO.read(DrawPanel.class.getResourceAsStream(imgSource));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            this.image = null;
        }
    }

    protected void moveit(PointD p) {
        position.x = (int) p.x;
        position.y = (int) p.y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }
}

public class DrawPanel extends JPanel{
    HashMap<String, ImageObject> objects = new HashMap<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, Map<String, PointD> vehicleData) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.gray);
        objects.put("Verkstad", new ImageObject(300,300,"pics/VolvoBrand.jpg")) ;
        updateImageObjects(vehicleData);
    }

    public void updateImageObjects(Map<String, PointD> vehicleData) {
        System.out.println(objects.size());
        if (vehicleData == null) return;
        vehicleData.forEach((s, point) -> {
            if (!objects.containsKey(s)) {
                String img = null;
                switch (s) {
                    case "Volvo240" -> img = "pics/Volvo240.jpg";
                    case "Saab95" -> img = "pics/Saab95.jpg";
                    case "Scania" -> img = "pics/Scania.jpg";
                }
                ImageObject imgObject = new ImageObject((int) point.x,(int) point.y, img);
                objects.put(s, imgObject);
            } else  {
                ImageObject imgObject = objects.get(s);
                imgObject.moveit(point);
            }
        });
        repaint();
    }

    protected void removeObject(String name) {
            objects.remove(name);
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        objects.keySet().forEach((name) -> {
            g.drawImage(
                    objects.get(name).getImage(),
                    objects.get(name).getX(),
                    objects.get(name).getY(),
                    null);}
        );
    }
}
