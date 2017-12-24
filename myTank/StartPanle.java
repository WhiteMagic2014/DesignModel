package myTank;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StartPanle extends JPanel implements Runnable{

	Boolean temp;
	
	public StartPanle() {
		this.temp = true;
	}
	
	@Override
	public void run() {
		while(true){
		
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			repaint();
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Utils.toColorFromString("00e3f9fd"));
		g.fillRect(0, 0, Simulator.WIDTH, Simulator.LENGTH);
		g.setColor(Color.BLACK);
		
		if (temp) {
			g.drawString("info", 200, 200);
			temp = false;
		}else {
			temp = true;
		}
		
	}
}
