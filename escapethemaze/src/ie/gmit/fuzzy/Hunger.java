package ie.gmit.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;

public class Hunger {
	
	private String fileName;
	private FIS fis;
	
	public Hunger()
	{
		 fileName = "fcl/hunger.fcl";
		 
	     fis = FIS.load(fileName,true);
	     
	     if( fis == null ) 
	     { 
	         System.err.println("Can't load file: '" + fileName + "'");
	         return;
	     }
	}
	
	public HungerStatus howHungryAmI(int h,int f)
	{
	    fis.setVariable("health", h);
	    fis.setVariable("hungerlevel", f);

	    fis.evaluate();
	    
	    double x = fis.getVariable("hungerstatus").getValue();
	    
	    if(x < 2)
	    {
	    	return HungerStatus.WEAKEST;
	    }
	    else if(x < 4)
	    {
	    	return HungerStatus.WEAK;
	    }
	    else if(x < 7)
	    {
	    	return HungerStatus.CONTENT;
	    }
	    else
	    {
	    	return HungerStatus.HEALTHY;
	    }
	}
}
