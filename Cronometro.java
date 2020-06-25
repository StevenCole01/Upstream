class Cronometro extends Thread implements Runnable
{
	int tiempo;
	boolean detener;

	public void run()
	{
		this.detener = false;
		this.tiempo = 0;

		do
		{
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				System.out.println("Error: al ejecutar el sleep");
			}

			this.tiempo++;

		}while(this.detener == false);

	}

	public void detener() 
	{
		this.detener = true;
	}

	public int getTiempo()
	{
		return this.tiempo;
	}

}