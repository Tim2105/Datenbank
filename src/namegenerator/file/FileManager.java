package namegenerator.file;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.TransformerException;
import namegenerator.EmptyArrayException;
import namegenerator.EmptyFileException;
import namegenerator.NameGenerator;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

public class FileManager 
{
    
    public static final void createFile(File file) throws TransformerException, ParserConfigurationException
    {
        DocumentBuilderFactory dBuilder = DocumentBuilderFactory.newInstance();
        DocumentBuilder docWriter = dBuilder.newDocumentBuilder();
        Document document = docWriter.newDocument();
        Element element = document.createElement("Name");
        document.appendChild(element);
        
        Attr vattribut = document.createAttribute("Vornamen");
        vattribut.setValue("Tim-");
        element.setAttributeNode(vattribut);
        Attr nattribut = document.createAttribute("Nachnamen");
        nattribut.setValue("Plotzki-");
        element.setAttributeNode(nattribut);
        
        TransformerFactory schreiberFactory = TransformerFactory.newInstance();
        Transformer schreiber = schreiberFactory.newTransformer();
        DOMSource domsource = new DOMSource(document);
        StreamResult ergebnis = new StreamResult(file);
        schreiber.transform(domsource, ergebnis);
    }
    
    public static final void createFile(File file, String[][] namen) throws TransformerException, ParserConfigurationException, EmptyArrayException
    {
        DocumentBuilderFactory dBuilder = DocumentBuilderFactory.newInstance();
        DocumentBuilder docWriter = dBuilder.newDocumentBuilder();
        Document document = docWriter.newDocument();
        Element element = document.createElement("Name");
        document.appendChild(element);
        
        Attr vattribut = document.createAttribute("Vornamen");
        String vornamen = "";
        
        for(int i = 0; i < NameGenerator.getLengthVorname(namen); i++)
            vornamen += namen[1][i] + "-";
        
        vattribut.setValue(vornamen);
        element.setAttributeNode(vattribut);
        Attr nattribut = document.createAttribute("Nachnamen");
        String nachnamen = "";
        
        for(int j = 0; j < NameGenerator.getLengthNachname(namen); j++)
            nachnamen += namen[0][j] + "-";
        
        nattribut.setValue(nachnamen);
        element.setAttributeNode(nattribut);
        
        TransformerFactory schreiberFactory = TransformerFactory.newInstance();
        Transformer schreiber = schreiberFactory.newTransformer();
        DOMSource domsource = new DOMSource(document);
        StreamResult ergebnis = new StreamResult(file);
        schreiber.transform(domsource, ergebnis);
    }
    
    public static final String[][] readFile(File file) throws ParserConfigurationException, IOException, SAXException
    {
        String[][] namen = new String[2][Short.MAX_VALUE];
        File rfile = file;
        
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run() 
            {
                DocumentBuilderFactory dBuilder = DocumentBuilderFactory.newInstance();
                DocumentBuilder docWriter = null;
                
                try 
                {
                    docWriter = dBuilder.newDocumentBuilder();
                } 
                catch (ParserConfigurationException e) 
                {
                    e.printStackTrace();
                }
                
                Document document = null;
                
                try 
                {
                    document = docWriter.parse(rfile);
                } catch (IOException | SAXException e) 
                {
                    e.printStackTrace();
                } 
                
                document.getDocumentElement().normalize();
                NodeList vnnamen = document.getElementsByTagName("Name");

                for(int i = 0; i < vnnamen.getLength(); i++)
                {
                    NamedNodeMap attribute = vnnamen.item(i).getAttributes();

                    for(int j = 0; j < attribute.getLength(); j++)
                    {
                        String namenkette;
                        namenkette = ((Attr)attribute.item(j)).getValue();
                        int a = 0;
                        int b = 0;

                        for(int z = 0; z < namenkette.length(); z++)
                        {
                            if(namenkette.charAt(z) == '-')
                            {
                                namen[j][b] = namenkette.substring(a, z);
                                a = z + 1;
                                b++;
                            }
                            else if(z == namenkette.length() - 1)
                            {
                                namen[j][b] = namenkette.substring(a, z + 1);
                                a = z + 1;
                                b++;
                            }
                        }
                    }
                }
            }
        }
        );
        
        thread.run();
        
        return namen;
    }
    
    public static String createRandomName(File file) throws ParserConfigurationException, IOException, SAXException, EmptyFileException
    {
        String namen[][] = FileManager.readFile(file);
            
        String name = "";
            
        int i = NameGenerator.getLengthVorname(namen);
        if(i == 0)
            throw new EmptyFileException("In der Datei befanden sich keine Vornamen");

        i = (int)Math.round(Math.random() * (i - 1));

        name += namen[1][i];
        name += " ";
        int j = NameGenerator.getLengthNachname(namen);
        if(j == 0)
            throw new EmptyFileException("In der Datei befanden sich keine Vornamen");

        j = (int)Math.round(Math.random() * (j - 1));
        
        name += namen[0][j];
            
        return name;
    }
    
    public static List<List<String>> getFileAsList(File file) throws ParserConfigurationException, IOException, SAXException
    {
        String[][] namen = FileManager.readFile(file);
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
    
    public static int getLengthVorname(File file) throws EmptyArrayException, ParserConfigurationException, IOException, SAXException
    {
        return NameGenerator.getLengthVorname(readFile(file));
    }
    
    public static int getLengthNachname(File file) throws EmptyArrayException, ParserConfigurationException, IOException, SAXException
    {
        return NameGenerator.getLengthNachname(readFile(file));
    }
    
    public static int getLengthVorname() throws EmptyArrayException, ParserConfigurationException, IOException, SAXException
    {
        return getLengthVorname(new File(PathFinder.newInstance().getCurrentJarPath() + "/testdatei.xml"));
    }
    
    public static int getLengthNachname() throws EmptyArrayException, ParserConfigurationException, IOException, SAXException
    {
        return getLengthNachname(new File(PathFinder.newInstance().getCurrentJarPath() + "/testdatei.xml"));
    }
    
}
