package Model;

import Datenbank.*;
import java.util.Observable;
import dbklassen.*;

public class Model extends Observable
{
    
    public static final String ip = "10.16.0.3";
    public static final int port = 3306;
    
    private final String s1 = "SELECT * FROM Polizist";
    private final String s2 = "SELECT * FROM Straftaeter";
    private final String s3 = "SELECT * FROM Ordnungswidrigkeit";
    private final String s4 = "SELECT * FROM Rang";
    private final String s5 = "SELECT * FROM Strafzelle";
    private final String s6 = "SELECT OName, OVorname, Ordnungswidrigkeit, Datum, Geldstrafe FROM Ordnungswidrigkeit WHERE OName = 'Neumann' AND OVorname = 'Marvin'";
    private final String s7 = "SELECT Polizist.Name, Polizist.Vorname, Rang.Gehaltshoehe FROM Polizist JOIN Rang ON Polizist.Rang = Rang.Rang";
    
    private DatabaseConnectorMySQL connector;
    private QueryResult qresult;
    
    public Model() throws ConnectionFailureException
    {
        this.connector = new DatabaseConnectorMySQL(ip, port, "tim", "tim", "Tim2003!");
        if(this.connector.getErrorMessage() != null)
            throw new ConnectionFailureException("Die Verbindung konnte nicht hergestellt werden");
    }
    
    public void verifyAndSendStatement(String statement) throws StatementDeniedException, SQLStatementException
    {
        
        //auf Nummer eines Statements überprüfen//
        switch (statement)
        {
            case "1":
                this.sendStatement(this.s1);
                return;
            case "2":
                this.sendStatement(this.s2);
                return;
            case "3":
                this.sendStatement(this.s3);
                return;
            case "4":
                this.sendStatement(this.s4);
                return;
            case "5":
                this.sendStatement(this.s5);
                return;
            case "6":
                this.sendStatement(this.s6);
                return;
            case "7":
                this.sendStatement(this.s7);
                return;
            default:
                break;
        }
        
        ////////////////////////
        
        //auf Datenbankveränderung überprüfen//
        
        String s = statement.toUpperCase();
        if(s.contains("DELETE"))
            throw new StatementDeniedException("Ungenehmigte Operation gefunden - 'DELETE'");
        if(s.contains("DROP"))
            throw new StatementDeniedException("Ungenehmigte Operation gefunden - 'DROP'");
        if(s.contains("UPDATE"))
            throw new StatementDeniedException("Ungenehmigte Operation gefunden - 'UPDATE'");
        if(s.contains("INSERT"))
            throw new StatementDeniedException("Ungenehmigte Operation gefunden - 'INSERT'");
        
        ////////////////////////////////////////
        
        this.sendStatement(statement);
    }
    
    public void sendStatement(String statement) throws SQLStatementException
    {
        long a = System.currentTimeMillis();
        this.connector.executeStatement(statement);
        long z = System.currentTimeMillis() - a;
        if(this.connector.getErrorMessage() != null)
                throw new SQLStatementException("Fehler beim Ausführen des Statements: " + this.connector.getErrorMessage());
        
        this.qresult = this.connector.getCurrentQueryResult();
        this.notifyObs();
        this.meldeTimeout(z);
    }
    
    private void meldeTimeout(long timeout)
    {
        this.setChanged();
        this.notifyObservers(timeout);
    }
    
    @Override
    public String toString()
    {
        String[][] data;
        String[] u;
        String ausgabe = "";
        if(this.qresult != null)
        {
            ausgabe += "Ergebnis der SQL-Abfrage";
            data = this.qresult.getData();
            u = this.qresult.getColumnNames();
            ausgabe += "\n";
            
            for(int i = 0; i < u.length; i++)
            {
                ausgabe += u[i];
                for(int z = 0; z < 23 - u[i].length(); z++)
                    ausgabe += " ";
            }
            
            for(int i = 0; i < data.length; i++)
            {
                ausgabe += "\n";
                for(int j = 0; j < data[i].length; j++)
                {
                    ausgabe += data[i][j];
                    for(int z = 0; z <  23 - data[i][j].length(); z++)
                        ausgabe += " ";
                }
            }
        }
        
        return ausgabe;
    }
    
    private void meldeServerTimeout()
    {
        this.setChanged();
        this.notifyObservers("/timeout/");
    }
    
    private void notifyObs()
    {
        this.setChanged();
        this.notifyObservers();
    }
    
}
