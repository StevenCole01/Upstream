import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

class Pez extends JPanel
{

	BufferedImage img;

	Rectangle[] hitBox = new Rectangle[5];

	public Pez(BufferedImage img)
	{
		this.img = img;		
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img,0,0,250,110,null);
	}

	
}