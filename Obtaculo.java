import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

class Obstaculo extends JPanel
{
	int tamanoTronco;

	BufferedImage imagenTronco;

	JLabel img = new JLabel();

	public Obstaculo()
	{
		int t = (int)(Math.random()*2+1);

		this.tamanoTronco = t;

		try{
			
			switch(t)
			{
				case 1:
				this.imagenTronco = ImageIO.read(new File("./imagenes/TroncoChico.png"));
				break;

				case 2:
				this.imagenTronco = ImageIO.read(new File("./imagenes/TroncoMediano.png"));
				break;
			
				case 3:
				this.imagenTronco = ImageIO.read(new File("./imagenes/TroncoGrande.png"));
				break;				
			}

			

		}catch(Exception e)
		{
			System.out.println("Error al cargar la imagen");
		} 
		
		img.setIcon(new ImageIcon(imagenTronco));
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(imagenTronco,0,0,imagenTronco.getWidth(),imagenTronco.getHeight(),null);
	}

	public void reiniciar()
	{
		
		int a = (int)(Math.random()*2+1);

		this.tamanoTronco = a;

		try{
			
			switch(a)
			{
				case 1:
				this.imagenTronco = ImageIO.read(new File("./imagenes/TroncoChico.png"));
				this.setBounds(1700,400,130,195);
				break;

				case 2:
				this.imagenTronco = ImageIO.read(new File("./imagenes/TroncoMediano.png"));
				this.setBounds(1700,400,130,380);
				break;
			
				case 3:
				this.imagenTronco = ImageIO.read(new File("./imagenes/TroncoGrande.png"));
				this.setBounds(1700,400,130,490);
				break;				
			}

		}catch(Exception e)
		{
		System.out.println("Error al cargar la imagen");
		} 
		
		img.setIcon(new ImageIcon(imagenTronco));

	}

	public int getTamanoTronco()
	{
		return this.tamanoTronco;
	}

}