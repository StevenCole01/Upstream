import java.awt.*;

class ObjetoHilo2 extends Thread implements Runnable
{
	Boolean flagUp;
	Boolean flagDown;
	boolean check;
	Pez pez;


	Point pos;	
	int y;
	int x;

	public ObjetoHilo2( Pez pez, Boolean flagUp, Boolean flagDown)
	{
	 	this.pez = pez;
	 	this.flagUp = flagUp;
		this.flagDown = flagDown;		
	}

	@Override
	public void run()
	{
		check = false;
		do
		{
			retardo(10);

			if(flagUp == true)
			{
				pos = pez.getLocation();				
				y = (int)pos.getY();
				x = (int)pos.getX();

				if(y > 135)
				{
					pez.setLocation(x,y-5);
				}
			}


			if(flagDown == true)
			{
				pos = pez.getLocation();				
				y = (int)pos.getY();
				x = (int)pos.getX();

				if(y < 750)
				{
					pez.setLocation(x,y+5);
				}
			}


			
		} while (check == false);
	}

	/*public void detener()
	{
		this.check = true;
	}*/

	public void detener()
	{
			this.check = true;
	}

	public void retardo(int ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch(Exception e)
		{
			System.out.println("Error: al ejecutar el sleep");
		}

	}

	public void setFlagUp(Boolean flagUp)
	{
		this.flagUp = flagUp;
	}
	
	public void setFlagDown(Boolean flagDown)
	{
		this.flagDown = flagDown;
	}
}