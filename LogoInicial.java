import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

class LogoInicial extends JPanel
{
	BufferedImage imagenLogo;

	JLabel logo = new JLabel();

	public LogoInicial()
	{
		try
		{
			this.imagenLogo = ImageIO.read(new File("./imagenes/Logo.png"));
			
		}
		catch(Exception e)
		{
			System.out.println("Error al cargar la imagen");
		} 
		
		logo.setIcon(new ImageIcon(imagenLogo));
	}

	public JLabel getLogo()
	{
		return this.logo;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(imagenLogo,0,0,1700,1000,null);
	}
}