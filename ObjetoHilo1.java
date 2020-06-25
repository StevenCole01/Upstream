class ObjetoHilo1 implements Runnable{
	
	Obstaculo[] arregloTroncos;
	boolean corriendo = true;
	ObjetoHilo2 oh2;
	Cronometro cm = new Cronometro();
	int tiempo;

	Pez pez;
	Boolean check = false;
	
	int separacionTroncos = 630;
	int movPixeles = 50; 
	int tiempoRetardo = 15; //milisegundos

	int[] y = new int[6];

	public ObjetoHilo1(Obstaculo[] arregloTroncos, Pez pez, ObjetoHilo2 oh2)
	{
		this.arregloTroncos = arregloTroncos;
		this.pez = pez;
		this.oh2 = oh2;
	}
		
	@Override
	public void run()
	{
		//al momento de iniciar se llena la variable boleana "corriendo" como "true"
		this.corriendo = true;
		pez.setVisible(true); //el pez se muestra en pantalla al iniciar el hilo
		pez.setLocation(30,450); // y se cambia su localizacion al centro (aprox.)

		oh2.start();//se inicia el hilo 2 que realiza el movimiento del pez
		cm.start();
		do
		{
			/*if (tiempoRetardo >= 75)
			tiempoRetardo = tiempoRetardo -25; // para que incremente la velocidad cada vuelta*/
			for (int i = 0 ; i<6; i++) 
			{
				y[i] = posicionarTroncosRandomEnY(arregloTroncos[i].getTamanoTronco());
			}

			for (int x=1490; x>-3300 ; x=x-5) 
			{


				arregloTroncos[0].setLocation(x,y[0]);

				for (int i =1 ; i<6; i++) 
				{
					arregloTroncos[i].setLocation(x+separacionTroncos*i,y[i]);
				}
				retardo(tiempoRetardo);
				checkCollisions();

				if (check==true)
				{
					cm.detener();
					this.tiempo = cm.getTiempo();
					break;
				}	
			}

			for (int i = 0 ; i<6; i++) 
			{
				arregloTroncos[i].reiniciar();
			}

		} while(check == false); /*hasta que la variable "check" es "true" termina el while 
		y pasa a los siguientes metodos*/


		oh2.detener(); //este metodo detiene el movimiento del pez (cambia la variable "check" de false a true, lo que detiene el proceso)

		pez.setVisible(false);//oculta el pez en pantalla

		this.corriendo = false;
		// la variable "Corriendo" pasa a ser false
		//System.out.println(cm.getTiempo());
	}

	public void retardo(int ms)
	{
		try
			{
				Thread.sleep(ms);
			}catch(Exception e){
				System.out.println("Error: al ejecutar el sleep");
			}

	}

	
	public int posicionarTroncosRandomEnY(int tamanoTronco) 
	{	
		int posicionYRdm = 0;
		int opc;
		switch(tamanoTronco)
		{
			case 1:
			   opc = (int)(Math.random()*7+1);
			   if (opc == 1)
				  posicionYRdm = 110;
			   else if (opc == 2)
				  posicionYRdm = 305;
			   else if (opc == 3)
				  posicionYRdm = 500;
				else if (opc == 4)
				  posicionYRdm = 200;
				else if (opc == 5)
				  posicionYRdm = 400;
				else if (opc == 6)
				  posicionYRdm = 550;
				else if (opc == 7)
				  posicionYRdm = 600;  
			break;

			case 2:
			   opc = (int)(Math.random()*5+1);
			   if (opc == 1)
			   		posicionYRdm = 110;
			   else if (opc == 2)
			   		posicionYRdm = 330;
			   else if (opc == 3)
			   		posicionYRdm = 220;
			   else if (opc == 4)
				   posicionYRdm = 360;
				else if (opc == 5)
			   		posicionYRdm = 400;
			break;

		}

		return posicionYRdm;
	}

	
	public void checkCollisions()
	{	
	    for (int i = 0; i<6 ; i++) 
	    {
			if(arregloTroncos[i].getBounds().intersects(pez.getBounds()))
		    {
		   		System.out.println("choco");
		   		this.check = true;

		    }
		}
		
	}


	public boolean getCheck()
	{
		return this.check;
	}

	public boolean getCorriendo()
	{
		return this.corriendo;
	}

	public int getTiempoInt()
	{
		return this.tiempo;
	}
	
}