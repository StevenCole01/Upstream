import javax.sound.midi.*;

class MiReproductor
{
	private Synthesizer synthe = null;
	private int intensity = 100;
	private MidiChannel [] channels;
	private MidiChannel channel;

	public MiReproductor()
	{
		try
		{
			synthe = MidiSystem.getSynthesizer();
		}
		catch(Exception e)
		{
			System.out.println("Error al Obtener el Synthe");			
		}
	}

	public void inicializar()
	{
		try
		{
			synthe.open();
			channels = synthe.getChannels();
			
		}
		catch(Exception e)
		{
			System.out.println("Error al inicializar el Synthe");			
		}
	}

	public void reproducirNota(int nota,int canal, int duracion)
	{
		channel = channels[canal];

		
		channel.programChange(57);

		channel.noteOn(nota,intensity);

		try
		{
		Thread.sleep(duracion);
		}
		catch(Exception e)
		{
		System.out.println("Error en sleep");
		}

		channel.noteOff(nota);

	}

	public void finalizar()
	{
		try
		{
			synthe.close();
		}
		catch(Exception e)
		{
			System.out.println("Error al finalizar el Synthe");			
		}
	}	

	public void reproducirSadTrombone()
	{
		//***********
		
		inicializar();

		//**********

		try
		{
		Thread.sleep(100);
		}
		catch(Exception e)
		{
		System.out.println("Error en sleep");
		}

		//**********
		
		reproducirNota(50,1,600);
		reproducirNota(49,1,600);
		reproducirNota(48,1,600);
		reproducirNota(47,1,2000);
		


		//**********

		finalizar();

		//**********

	}

	public void setInstrumentTrombone()
	{
	    try 
	    {
	      channel.programChange(57);
	    } 
	    catch (Exception e) 
	    {
	      System.out.println("Error al cambiar instrumento");
	    }
	}

}