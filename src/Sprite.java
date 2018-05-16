import java.awt.Graphics;
import java.awt.Image;
import java.io.*;

import javax.swing.ImageIcon;

class Sprite {
   private int posx,posy;
   private boolean active;
   private int frame,nframes;
   private Image[] sprites;
	
   // constructor. 'nframes' es el número de frames del Sprite. 
   // El Sprite no está activo por defecto. 
   
   public Sprite(int nframes) {
	   	active=false;
		frame=1;
		this.nframes=nframes;
		sprites=new Image[nframes+1]; 
   }
	   
   public void setX(int x) {
	   posx=x; 
   }
	   
    public void setY(int y) {
	   posy=y; 
	}
	
   	int getX() {
	      return posx;
	}
	
	int getY() {
	      return posy;
	}
	
	int getW() {
		return sprites[nframes].getWidth(null);
	}
	
	int getH() {
		return sprites[nframes].getHeight(null);
	}
	   
	public void on() {
	    active=true;
	}
	   
	public void off() {
	    active=false;
	}
	   
	public boolean isActive() {
	    return active;
	}
	
	public void selFrame(int frameno) {
	    frame=frameno;
	}
	 
	public int frames() {
	    return nframes;
	}
	
	// Carga un archivo tipo .PNG y lo añade al sprite en 
	// el frame indicado por 'frameno'
	public void addFrame(int frameno, String path) throws IOException {
		//sprites[frameno] = Image.createImage();
		//System.out.println("Ruta recurso: " + System.getProperty("user.dir"));
		sprites[frameno] = (new ImageIcon(System.getProperty("user.dir").toString() + "/res/hero.png")).getImage();
	}
	
	boolean collide(Sprite sp) { 
		int w1,
		h1,w2,h2,x1,y1,x2,y2;
		w1=getW();		// ancho del sprite1
		h1=getH();		// altura del sprite1
		w2=sp.getW();	// ancho del sprite2
		h2=sp.getH();	// alto del sprite2
		x1=getX();		// pos. X del sprite1
		y1=getY();		// pos. Y del sprite1
		x2=sp.getX();	// pos. X del sprite2
		y2=sp.getY();	// pos. Y del sprite2
		
		if (((x1+w1)>x2)&&((y1+h1)>y2)&&((x2+w2)>x1)&&((y2+h2)>y1)) { 
			return true;
		} else {
		     return false;
		}
	}
	   
	// Dibujamos el Sprite
	public void draw(Graphics g) {
		g.drawImage(sprites[frame], posx, posy, null);
	}
}






