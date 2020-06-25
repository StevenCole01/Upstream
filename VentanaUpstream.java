import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import java.lang.Thread;

class VentanaUpstream extends JFrame implements ActionListener, KeyListener
{
	JPanel panel;

	JLabel pantallaLogo;
	JLabel lblTitulo;
	JLabel lblTiempo;
	JLabel lblHighScore;
	JLabel salmonWasted;

	JButton btnJugar;
	JButton btnContinuar;
	JButton btnSalir;
	//declarar para pez
	BufferedImage imagen;
	BufferedImage subImagen;
	Pez pez;
	int indiceX = 0;

	Boolean flagUp;
	Boolean flagDown;
	boolean check;

	boolean corriendo;

	//obstaculo
	Thread hilo1;
	ObjetoHilo1 oh1;

	//pez
	ObjetoHilo2 oh2;

	LogoInicial lg;//objeto del logo inicial

	Reproductor rep = new Reproductor();
	MiReproductor miRep = new MiReproductor();
	Obstaculo[] arregloTroncos;



	public VentanaUpstream()
	{
		panel = new CambioDeFondo();
		panel.setLayout(null);

		/*pantallaLogo = lg.getLogo();
		pantallaLogo.setBounds(0,0,1700,800);*/

		///Se crea
		this.setIconImage(new ImageIcon(getClass().getResource("./imagenes/IconoUpstream.jpg")).getImage());
		lg = new LogoInicial();
		lg.setBounds(0,0,1700, 1000);
		lblTitulo = new JLabel(new ImageIcon("./imagenes/titulo.png"));
		lblTitulo.setBounds(270,150,1145,400);

		lblTiempo = new JLabel();
		lblTiempo.setBounds(0,0,1700,100);
		lblTiempo.setFont(new Font("Helvetica", Font.PLAIN, 88));
		lblTiempo.setForeground(Color.WHITE);
		lblTiempo.setVisible(false);

		lblHighScore = new JLabel("HS: " + Archivo.leerHighScore());
		lblHighScore.setBounds(1450,0,300,100);
		lblHighScore.setFont(new Font("Helvetica", Font.PLAIN, 48));
		lblHighScore.setForeground(Color.WHITE);

		salmonWasted = new JLabel(new ImageIcon("./imagenes/Oso_Salmon_Wasted.gif"));
		salmonWasted.setBounds(650,200,400,240);
		salmonWasted.setVisible(false);
		
		//imagen pez
		try
		{
		    imagen = ImageIO.read(new File("./imagenes/Sprite_Salmon.png"));
		}
		catch(Exception e)
		{
			System.out.println("Error al cargar imagen");
		}
		
		///Se edita el pez
		subImagen = imagen.getSubimage(0,209,412,171);
		pez = new Pez(subImagen);
		pez.setBounds(30,300,250,100);
		pez.setVisible(false);
		pez.setOpaque(false);

		///banderas de moviemento para el pez
		flagUp = false;
		flagDown = false;

		///botones
		btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(630,600,440,160);
		btnJugar.setIcon(new ImageIcon("./imagenes/botonPlay.png"));
		btnJugar.setBackground(new java.awt.Color(80,161,46));

		btnContinuar = new JButton();
		btnContinuar.setBounds(650,500,400,120);
		btnContinuar.setIcon(new ImageIcon("./imagenes/botonContinuar.png"));
		btnContinuar.setVisible(false);

		btnSalir = new JButton();
		btnSalir.setBounds(650,650,400,126);
		btnSalir.setIcon(new ImageIcon("./imagenes/botonSalir.png"));
		btnSalir.setVisible(false);


		///Se agrega el panel
		this.add(panel);

		///Se editan las opciones del panel
		this.setTitle("Upstream");
		this.setSize(1700, 1000);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);//colocando la ventana en el medio
		this.setVisible(true);
		this.setResizable(false);
		
		/// Sea agregan los listeners
		this.addKeyListener(this);

		///***pantalla de incio de carga		
		panel.add(lg);
		try
		{
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println("Error: al ejecutar el sleep");
		}		
		lg.setVisible(false);


		///****Se agregan todos los objetos al panel

		panel.add(pez); //agregando pez
		panel.add(btnJugar);
		panel.add(btnContinuar);
		panel.add(btnSalir);
		panel.add(lblTitulo);
		panel.add(lblTiempo);
		panel.add(lblHighScore);
		panel.add(salmonWasted);

		//se llena inicializa de troncos
		arregloTroncos = new Obstaculo[6];
		int mas = 0;
		for(int i = 0; i < 6 ; i++)
		{
			mas= mas +250;
			arregloTroncos[i] = new Obstaculo();
			
			if (arregloTroncos[i].getTamanoTronco() == 1) 
			{
				arregloTroncos[i].setBounds(1800+mas,400,130,195);
			}
			if (arregloTroncos[i].getTamanoTronco() == 2) 
			{
				arregloTroncos[i].setBounds(1800+mas,300,130,380);
			}
			if (arregloTroncos[i].getTamanoTronco() == 3) 
			{
				arregloTroncos[i].setBounds(1800+mas,160,130,490);
			}
			panel.add(arregloTroncos[i]);
			arregloTroncos[i].setOpaque(false);
		}		

		btnJugar.addActionListener(this);
		btnContinuar.addActionListener(this);
		btnSalir.addActionListener(this);

		rep.playMusic();

		
		//while para repetir el ciclo y reiniciar el juego
		while(true){
			
			//crearHilos();

			do
			{
				System.out.println(); //*************Se necesita quien sabe pa que, si no no jala**************
								
				corriendo = oh1.getCorriendo();

				if (corriendo == false)
				{
					rep.stopPlaying();
					String tiempo_string = String.valueOf(oh1.getTiempoInt());
					lblTiempo.setText("Score: " + tiempo_string);

					salmonWasted.setVisible(true);
					btnContinuar.setVisible(true);
					btnSalir.setVisible(true);
					
					lblTiempo.setVisible(true);
				    revisarHighScore();
					refreshLblHighScore();
					miRep.reproducirSadTrombone();

				}
			}while(corriendo == true);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnJugar)
		{

			empezarPartida();
			btnJugar.setVisible(false);		
		    lblTitulo.setVisible(false);
		    lblTiempo.setVisible(false);


		}

		else if (e.getSource() == this.btnContinuar)
		{
			    btnContinuar.setVisible(false);
				btnSalir.setVisible(false);
				lblTiempo.setVisible(false);
				salmonWasted.setVisible(false);

				btnJugar.setVisible(true);
				lblTitulo.setVisible(true);
				rep.playMusic();
				
		}
		else if (e.getSource() == this.btnSalir)
		{
			System.exit(ABORT);
		}

	}
	

	public void keyPressed(KeyEvent e)
	{		
		
		int t = e.getKeyCode();

				
		if(t==40)//flecha abajo
		{
			//Mover pescado hacia abajo y cambiar a sprite hacia abajo
			oh2.setFlagDown(true);
			
			pez.img = imagen.getSubimage(0,380,375,225);
			
			pez.setSize(250,110);
		}
		if(t==38)//flecha arriba
		{
			//Mover Pescado hacia arriba y cambiar a sprite hacia arriba
			oh2.setFlagUp(true);
			
			pez.img = imagen.getSubimage(0,0,400,203);
			
			pez.setSize(250,110);
		}		

	}

	public void keyReleased(KeyEvent e)
	{
		//******CAMBIAR LA SPRITE A LA HORIZONTAL**********
		int t = e.getKeyCode();

		if(t==40)
		{
			oh2.setFlagDown(false);
			
			pez.img = imagen.getSubimage(0,209,412,171);
			
			pez.setSize(250,100);//cambia el rectangulo de la imagen
		}
		else if(t==38)
		{
			oh2.setFlagUp(false);
			
			pez.img = imagen.getSubimage(0,209,412,171);

			pez.setSize(250,100);//cambia el rectangulo de la imagen

		}
		
	}

	public void keyTyped(KeyEvent e){}

	
	///Metodo del hilo para mover un tronco detectanco colisiones
	public void crearHilos()
	{
		oh2 = new ObjetoHilo2(pez, flagUp, flagDown);
		oh1 = new ObjetoHilo1(arregloTroncos, pez, oh2);
		hilo1 = new Thread(oh1);
	}

	
	public void empezarPartida()
	{
		
		this.requestFocus();
		hilo1.start();
	}

	public boolean getCheck()
	{
		return this.check;
	}

	public void setCheck(boolean check)
	{
		this.check = check;
	}

	public void revisarHighScore()
	{
		if (oh1.getTiempoInt() > Archivo.leerHighScore())
		{
			Archivo.borrarHighScore();
			Archivo.guardarHighScore(oh1.getTiempoInt());

		}
	}

	public void refreshLblHighScore()
	{
		lblHighScore.setText("HS: " + Archivo.leerHighScore());
	}
	
}