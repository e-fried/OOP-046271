package homework4;
/**
This is a base interface for strategy design pattern of different orders by which to paint the panels
*/
import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public interface PaintOrder extends Observer {
	
	public void update(Observable arg0,Object  panels);

	public void stopRun();
	


}
