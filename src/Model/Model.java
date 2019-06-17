package Model;

import Datenbank.*;
import java.util.Observable;
import dbklassen.*;

public class Model extends Observable
{
    
    public static final String ip = "10.16.0.3";
    public static final int port = 3306;
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
        String s = statement.toUpperCase();
        if(s.contains("DELETE"))
            throw new StatementDeniedException("Ungenehmigte Operation gefunden - 'DELETE'");
        if(s.contains("DROP"))
            throw new StatementDeniedException("Ungenehmigte Operation gefunden - 'DROP'");
        if(s.contains("UPDATE"))
            throw new StatementDeniedException("Ungenehmigte Operation gefunden - 'UPDATE'");
        if(s.contains("INSERT"))
            throw new StatementDeniedException("Ungenehmigte Operation gefunden - 'INSERT'");
        
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
