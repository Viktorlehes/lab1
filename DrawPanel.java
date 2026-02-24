import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

class ImageObject {
    private final Point position;
    private BufferedImage image;
    public ImageObject(int x, int y, String imgSource) {
        this.position = new Point(x,y);
        try {
            this.image = ImageIO.read(DrawPanel.class.getResourceAsStream(imgSource));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void moveit(int x, int y) {
        position.x = x;
        position.y = y;
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
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.gray);
        objects.put("Volvo240", new ImageObject(0,0,"pics/Volvo240.jpg"))  ;
        objects.put("Saab95", new ImageObject(0,0,"pics/Saab95.jpg")) ;
        objects.put("Scania", new ImageObject(0,0,"pics/Scania.jpg"));
        objects.put("Verkstad", new ImageObject(300,300,"pics/VolvoBrand.jpg")) ;
    }

    protected  void moveit(String name, int x, int y) {
        if (objects.containsKey(name)) {
            objects.get(name).moveit(x,y);
        }
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
