import javax.swing.*;
import java.awt.*;

class CambioDeFondo extends JPanel{
	public Image imagen;

	@Override
	public void paint(Graphics g)
	{
		imagen = new ImageIcon(getClass().getResource("./imagenes/fondoUpstream.jpg")).getImage();

		g.drawImage(imagen,0,0,getWidth(),getHeight(),this);

		setOpaque(false);

		super.paint(g);
	}
	
}