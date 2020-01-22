package homework4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public interface PaintOrder extends Observer {
	
	public void update(Observable arg0,Object  panels);

	public void stopRun();
	


}
