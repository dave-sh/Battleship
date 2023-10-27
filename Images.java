package SinglePlayer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//TODO Load more images, redraw others
//TODO aesthetics
//TODO video

public class Images {
	static ImageIcon waterIcon = null;
	static ImageIcon shipIcon = null;
	static ImageIcon missed = null;
	static ImageIcon shipHit = null;
	
	public static void initImages() throws IOException {
		waterIcon = ImagesLocator.getImage("waterscaled.png");
		shipIcon = ImagesLocator.getImage("shippiece.png");
		missed = ImagesLocator.getImage("missed.png");
		shipHit = ImagesLocator.getImage("shiphit.png");
	}
}
