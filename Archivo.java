import java.io.*;

class Archivo
{	
    
    public static int leerHighScore()
    {
        String strLine = new String();

        try(FileInputStream fis = new FileInputStream("High_Score.txt");
        DataInputStream in = new DataInputStream(fis);
        BufferedReader br = new BufferedReader( new InputStreamReader(in));)
        {
            strLine = br.readLine();
            // in.close();
        }
        catch (Exception e ) //Agregar Excepciones
        {
        e.printStackTrace();
        System.exit(1);
        }
        //arraylist.remove(arraylist.size()-1); // remover el ultimo elemento, que es null
        return Integer.parseInt(strLine);
    }

    public static void borrarHighScore()
    {
        File file = new File("High_Score.txt");
        file.delete();
    }

    public static void guardarHighScore(int tiempo) 
    {
        try{
        FileOutputStream fos = new FileOutputStream ("High_Score.txt");
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
        String tiempoInt= Integer.toString(tiempo); // parseo a string***
        out.write(tiempoInt); //no se si funciona directamente u ocupa ser string
        out.close(); 
        }
        catch (Exception e) //Agregar Excepciones
        { 
        e.printStackTrace();
        System.exit(1);
        }   
    }   


}
