package namegenerator;

import namegenerator.NameGenerator.NameAdder;

public class NameAdderGenerator
{
    
    public NameAdderGenerator()
    {
        
    }
    
    public static NameAdder generateNameAdder()
    {
        NameGenerator ngenerator = new NameGenerator();
        return ngenerator.generateNameAdder();
    }
    
}
