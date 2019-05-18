package namegenerator;

import namegenerator.file.PathFinder;
import namegenerator.file.FileManager;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import java.util.List;

public class NameGenerator
{
    
    public static String createRandomName() throws ParserConfigurationException, IOException, SAXException, EmptyFileException
    {
        return FileManager.createRandomName(new File(PathFinder.newInstance().getCurrentJarPath() + "/namensdatei.xml"));
    }
    
    public static String createRandomName(String[][] namen) throws EmptyArrayException
    {
        String name = "";
            
        int i = getLengthVorname(namen);
        if(i == 0)
            throw new EmptyArrayException("In dem Array befanden sich keine Vornamen");
        
        i = (int)Math.round(Math.random() * (i - 1));
        
        name += namen[1][i];
        name += " ";
        int j = getLengthNachname(namen);
        if(j == 0)
            throw new EmptyArrayException("In dem Array befanden sich keine Vornamen");
        
        j = (int)Math.round(Math.random() * (j - 1));
        
        name += namen[0][j];

            
        return name;
    }
    
    public static int getLengthVorname(String[][] namen)
    {
        int i = 0;
        for(i = 0; i < namen[1].length; i++)
            if(namen[1][i] == null)
                break;
        
        return i;
    }
    
    public static int getLengthNachname(String[][] namen)
    {
        int i = 0;
        for(i = 0; i < namen[0].length; i++)
            if(namen[0][i] == null)
                break;
        
        return i;
    }
    
    public static final void addVornamen(String[] vornamen, File file)
    {
        try 
        {
            NameAdder nameadder = NameAdderGenerator.generateNameAdder();
            nameadder.addVornamen(vornamen);
        }
        catch(IOException | ParserConfigurationException | TransformerException | SAXException e)
        {
            e.printStackTrace();
            System.out.println("Beim Schreiben oder Lesen der Datei ist etwas falsch gelaufen. Stellen Sie sicher, dass die Datei existiert oder Java Schreibrechte auf den angepeilten Dateipfad hat");
        }
    }
    
    public static final void addNachnamen(String[] nachnamen, File file)
    {
        try 
        {
            NameAdder nameadder = NameAdderGenerator.generateNameAdder();
            nameadder.addNachnamen(nachnamen);
        }
        catch(IOException | ParserConfigurationException | TransformerException | SAXException e)
        {
            e.printStackTrace();
            System.out.println("Beim Schreiben oder Lesen der Datei ist etwas falsch gelaufen. Stellen Sie sicher, dass die Datei existiert oder Java Schreibrechte auf den angepeilten Dateipfad hat");
        }
    }
    
    public static final void addVornamen(String[] vornamen)
    {
        addVornamen(vornamen, new File(PathFinder.newInstance().getCurrentJarPath() + "/namensdatei.xml"));
    }
    
    public static final void addNachnamen(String[] nachnamen)
    {
        addNachnamen(nachnamen, new File(PathFinder.newInstance().getCurrentJarPath() + "/namensdatei.xml"));
    }
    
    public static List<List<String>> getFileAsList() throws ParserConfigurationException, IOException, SAXException
    {
        return FileManager.getFileAsList(new File(PathFinder.newInstance().getCurrentJarPath() + "/namensdatei.xml"));
    }
    
    public static List<List<String>> getArrayAsList(String[][] namen)
    {
        List<String> nachnamen = new ArrayList(), vornamen = new ArrayList();
        
        for(int i = 0; i < NameGenerator.getLengthVorname(namen); i++)
            vornamen.add(namen[1][i]);
        
        for(int j = 0; j < NameGenerator.getLengthNachname(namen); j++)
            nachnamen.add(namen[0][j]);
        
        List<List<String>> namensliste = new ArrayList();
        namensliste.add(nachnamen);
        namensliste.add(vornamen);
        
        return namensliste;
    }
    
    public NameAdder generateNameAdder()
    {
        return new NameAdder();
    }
    
    //************************************************************************************************************************************//
    
    public final class NameAdder extends Thread
    {
    
        public final void addVornamen(String[] vornamen, File file) throws ParserConfigurationException, IOException, SAXException, TransformerException
        {
            String[][] namen = FileManager.readFile(file);

            int i;
            for(i = 0; i < getLengthVorname(namen); i++)
            {
                if(namen[1][i] == null)
                    break;
            }

            for(int j = 0; j < vornamen.length; j++)
            {
                namen[1][i+j] = vornamen[j];
            }

            try
            {
                FileManager.createFile(file, namen);
            }
            catch(EmptyArrayException e)
            {
                e.printStackTrace();
            }
        }

        public final void addNachnamen(String[] nachnamen, File file) throws ParserConfigurationException, IOException, SAXException, TransformerException
        {
            String[][] namen = FileManager.readFile(file);

            int i;
            for(i = 0; i < getLengthNachname(namen); i++)
            {
                if(namen[0][i] == null)
                    break;
            }

            for(int j = 0; j < nachnamen.length; j++)
            {
                namen[0][i+j] = nachnamen[j];
            }

            try
            {
                FileManager.createFile(file, namen);
            }
            catch(EmptyArrayException e)
            {
                e.printStackTrace();
            }
        }

        public void addVornamen(String[] vornamen) throws ParserConfigurationException, IOException, SAXException, TransformerException
        {
            addVornamen(vornamen, new File(PathFinder.newInstance().getCurrentJarPath() + "/namensdatei.xml"));
        }

        public void addNachnamen(String[] nachnamen) throws ParserConfigurationException, IOException, SAXException, TransformerException
        {
            addNachnamen(nachnamen, new File(PathFinder.newInstance().getCurrentJarPath() + "/namensdatei.xml"));
        }   
        
    }
    
    //************************************************************************************************************************************//
    
}
