package homework4;

import java.awt.Color;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

import javax.swing.Timer;

public class ColorGenerator extends Observable{
	
	private static ColorGenerator singleColGen = new ColorGenerator();
	
	Timer t;
	private PaintOrder myStrategy;
	public static ColorGenerator getInstance() {
		return singleColGen;
	}
	public void initialStra(PaintOrder newStrategy){
		myStrategy=newStrategy;
	}
	public PaintOrder getStra(){
		return myStrategy;
	}	
	public void UpdateStrategy (PaintOrder newStrategy,ArrayList<Panel> panelObservers){

		if(t!=null) {
			t.stop();
		}
        t = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
	            Color newCol =randColor();
	            myStrategy.stopRun();
	            myStrategy=newStrategy;
	            Iterator<Panel> it = panelObservers.iterator();
	            while(it.hasNext()){
	            	((Panel) it.next()).setColor(newCol);       
	            }
	            setChanged();
	            
	            //newStrategy.stopRun();
	            singleColGen.addObserver(newStrategy);
	            notifyObservers(panelObservers);
	            singleColGen.deleteObserver(newStrategy);
            

            }
        });
        t.start();
	}
	public Color randColor(){
		Random rand = new Random();
		// Java 'Color' class takes 3 floats, from 0 to 1.
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		return (new Color(r, g, b));
	
	}
	public void stopRun(){
		myStrategy.stopRun();
	}

	

}
