import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Main {
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	int W = dim.width; int H = dim.height;
	int w=1200, h=750; // frame size

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new Main();
			}
		});
	}
	public Main(){
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setLocation(W/8, H/8);
		JPanel pnl = new Panel();
		frm.add(pnl);
		frm.pack();
		frm.setResizable(false);
		frm.setVisible(true);
	}
	public class Panel extends JPanel{
		private double x, y, c, xMoon, yMoon, //x,y for Earth
			xMerc, yMerc, xV, yV, xMars, yMars, xJ, yJ, xJspot, yJspot, xS, yS, xSring, ySring,
			xU, yU, xN, yN;
		int t = 1;
		int a, b, /*ellipse axis for Earth orbit<  moon orbit>*/ aMoon, bMoon, r, rr,//<sun radius, sun ray 
			aMars, bMars, aMerc, bMerc, aV, bV,
			aJ, bJ, rJ,
			aS, bS, rS,
			aU, bU, rU,
			aN, bN, rN;
		Image img;
		
		public Panel(){
			setPreferredSize(new Dimension(w,h));
			a = 200; b = 100; r=25;
			c = Math.sqrt(a*a-b*b);
			aMoon = 35; bMoon = 25; //moon orbit around eatrh axis
			aMars = 230; bMars = 180; aMerc = 75; bMerc = 70; aV = 100; bV = 90;
			aJ = 400; bJ = 350; rJ = 18;
			aS = 500; bS = 400; rS = 15;
			aU = 550; bU = 450; rU = 12;
			aN = 600; bN = 500; rN = 10;
			img = Toolkit.getDefaultToolkit().getImage("C:/Users/Utilizador/Desktop/earth.jpg");
						
			Timer timer = new Timer(10, new ActionListener(){
				public void actionPerformed(ActionEvent e){
					x = a*Math.sin(0.01*t) + (w/2-c+r);
					y = b*Math.cos(0.01*t) + h/2-r;
					xMoon = aMoon*Math.sin(0.02*t) + x;
					yMoon = bMoon*Math.cos(0.02*t) + y;
					xMerc = aMerc*Math.sin(0.02*t) + (w/2-c+r);
					yMerc = bMerc*Math.cos(0.02*t) + h/2-r;
					xV = aV*Math.sin(0.018*t) + (w/2-c+r);
					yV = bV*Math.cos(0.018*t) + h/2-r;
					xMars = aMars*Math.sin(0.008*t) + (w/2-c+r);
					yMars = bMars*Math.cos(0.008*t) + h/2-r;
					xJ = aJ*Math.sin(0.0045*t) + (w/2-c+r);
					yJ = bJ*Math.cos(0.0045*t) + h/2-r;
					xJspot = xJ+8; yJspot = yJ+20;
					xS = aS*Math.sin(0.0038*t) + (w/2-c+r);
					yS = bS*Math.cos(0.0038*t) + h/2-r;
					xSring = xS-8; ySring = yS+rS-4;
					xU = aU*Math.sin(0.0032*t) + (w/2-c+r);
					yU = bU*Math.cos(0.0032*t) + h/2-r;
					xN = aN*Math.sin(0.003*t) + (w/2-c+r);
					yN = bN*Math.cos(0.003*t) + h/2-r;
					rr = (int)(Math.random()*4);
					t++;
					repaint();
				}
			});
			timer.start();
		}
		
		public void paint(Graphics g){
			super.paintComponent(g);
			g.setColor(new Color(1, 15, 125));
			g.fillRect(0, 0, w, h);
			g.setColor(Color.YELLOW);
			g.fillOval((int)(w/2-c+r+20), h/2-r, r*2, r*2);
			g.setColor(new Color(255, 255, 0, 200));
			g.fillOval((int)(w/2-c+r+20)-rr, h/2-r-rr, r*2+rr*2, r*2+rr*2);
			g.drawImage(img, (int)x, (int)y,  null);
			g.setColor(new Color(50, 50, 50));
			g.fillOval((int)xMerc, (int)yMerc, 10, 10);
			g.setColor(new Color(200,200,200));
			g.fillOval((int)xV, (int)yV, 12, 12);
			g.setColor(Color.GRAY);
			g.fillOval((int)xMoon, (int)yMoon, 8, 8);
			g.setColor(new Color(230, 90, 40));
			g.fillOval((int)xMars, (int)yMars, 16, 16);
			g.setColor(Color.ORANGE);
			g.fillOval((int)xJ, (int)yJ, rJ*2, rJ*2);
			g.setColor(Color.RED);
			g.fillOval((int)xJspot, (int)yJspot, 12, 9); //Great Red Spot
			g.setColor(Color.RED);
			g.fillRect((int)xJ, (int)(yJ+rJ+4), rJ*2, 1);
			g.setColor(Color.RED);
			g.fillRect((int)(xJ+1), (int)(yJ+rJ+6), rJ*2-1, 1);
			g.setColor(Color.RED);
			g.fillRect((int)(xJ+2), (int)(yJ+rJ-4), rJ*2-1, 1);
			g.setColor(Color.YELLOW);		//Saturn ring
			g.drawOval((int)xSring, (int)ySring, rS*2+16, 8);
			g.setColor(Color.YELLOW);
			g.drawOval((int)xSring, (int)ySring, rS*2+15, 7);
			g.setColor(Color.ORANGE);	//Saturn
			g.fillOval((int)xS, (int)yS, rS*2, rS*2);
			g.setColor(Color.YELLOW);
			g.fillRect((int)(xS-1), (int)(ySring+7), rS*2-1, 1); //Saturn end
			g.setColor(new Color(5, 160, 200));
			g.fillOval((int)xU, (int)yU, rU*2-2, rU*2);
			g.setColor(Color.BLUE);
			g.fillOval((int)xN, (int)yN, rN*2-2, rN*2);
		}
	}
}
