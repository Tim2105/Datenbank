package namegenerator.file;

import java.security.CodeSource;

/**
 * aus meiner(Tim Plotzki's) Facharbeit
 * 
 * Enthält Methoden zur Bestimmung des Dateipfades der .jar
 * 
 * @author Tim Plotzki
 * @version 17.02
 */
public class PathFinder
{
    /**
     * Konstruktor für PathFinderobjekte
     */
    public PathFinder()
    {
           
    }
       
    //aus dem Internet
    /**
     * wandelt den Dateipfad aus getJarPath() zu einem Pfad in den Ordner um
     *
     * @return der Dateipfad des Ordners, in dem sich die .jar befindet
     */
    public String getCurrentJarPath() 
    {
        String path = getJarPath();
        if(path.endsWith(".jar")) 
            return path.substring(0, path.lastIndexOf("/"));
        return path;
    }
        
    //aus dem Internet
    /**
     * bestimmt den Daetipfad der .jar und gibt diesen zurück
     *
     * @return der Daetipfad der .jar
     */
    private String getJarPath() 
    {
        final CodeSource source = this.getClass().getProtectionDomain().getCodeSource();
        if (source != null) 
            return source.getLocation().getPath().replaceAll("%20", " ");
        return null;
    }
       
    /**
     * erstellt eine neue Instanz der Klasse PathFinder und gibt diese zurück
     *
     * @return eine neue Instanz der Klasse PathFinder
     */
    public static PathFinder newInstance()
    {
        return new PathFinder();
    }
}