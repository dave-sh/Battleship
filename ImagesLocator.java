package SinglePlayer;

import java.net.URL;
import java.awt.*;
import javax.swing.*;

public class ImagesLocator
{
     public static ImageIcon getImage(String imageName)
     {
          ClassLoader cl = ImagesLocator.class.getClassLoader();
          ImageIcon i = new ImageIcon(cl.getResource(imageName));
          return i;
     }

}
