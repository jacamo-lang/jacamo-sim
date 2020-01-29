package jcmsim;

 
import java.io.*;

import cartago.*;
import cartago.events.ArtifactObsEvent;

public class BasicLoggerOnFile {

    private FileWriter fw;
    private String name;
    
    public BasicLoggerOnFile(String fileName) throws IOException{
        this.name = fileName;
    }

    public void reset() throws IOException  {
        fw = new FileWriter(name);
        
    }
    public void log(String msg){
        try {
            fw.write(msg);
            fw.flush();
        } catch (Exception ex){
            
        }
    }
}
