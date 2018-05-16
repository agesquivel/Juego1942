import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JPanel;

class SSCanvas extends JPanel /*Canvas*/ implements Runnable, 
												KeyListener {
	private int indice_in, indice, xTiles, yTiles, sleepTime; 
	private int deltaX,deltaY;
	private Sprite hero=new Sprite(1);
	private Sprite[] tile=new Sprite[5];
	
	// Mapa del juego
	int map[] ={1,1,1,1,1,1,1,
				1,1,1,1,1,1,1, 
				1,2,1,1,1,1,1, 
				1,1,1,4,1,1,1, 
				1,1,1,1,1,1,1, 
				1,1,3,1,2,1,1, 
				1,1,1,1,1,1,1, 
				1,4,1,1,1,1,1, 
				1,1,1,1,3,1,1, 
				1,1,1,1,1,1,1, 
				1,4,1,1,1,1,1, 
				1,1,1,3,1,1,1, 
				1,1,1,1,1,1,1, 
				1,1,1,1,1,1,1, 
				1,2,1,1,1,1,1, 
				1,1,1,4,1,1,1, 
				1,1,1,1,1,1,1, 
				1,1,3,1,2,1,1, 
				1,1,1,1,1,1,1, 
				1,4,1,1,1,1,1};
	
	public SSCanvas() {
		this.addKeyListener(this);
		// Cargamos los sprites 
		try {
			hero.addFrame(1,"hero.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Iniciamos los Sprites
		hero.on(); 
	}
	
	void iniciar() { 
		int i;
		sleepTime = 50; 
		hero.setX((getWidth()-100)/2); 
		hero.setY(getHeight()-150); 
		deltaX=0;
		deltaY=0;
		xTiles=7;
		yTiles=7; 
		indice=map.length-(xTiles*yTiles); 
		indice_in=0;
		
		// Inicializamos los tiles 
		for (i=1 ; i<=4 ; i++) {
			tile[i]=new Sprite(1);
			tile[i].on(); 
		}
		
		try {
			tile[1].addFrame(1,"tile1.png");
			tile[2].addFrame(1,"tile2.png"); 
			tile[3].addFrame(1,"tile3.png"); 
			tile[4].addFrame(1,"tile4.png");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	void doScroll() {
		// movimiento del scenario (scroll) 
		indice_in+=2;
		
		if (indice_in>=100 /*32*/) {
			indice_in=0;
			indice-=xTiles; 
		}
		
		if (indice <= 0) {
			// si llegamos al final, empezamos de nuevo. 
			indice=map.length-(xTiles*yTiles); 
			indice_in=0;
		} 
	}
	
	void computePlayer() {
		// actualizar posición del avión
		if (hero.getX()+deltaX>0 && hero.getX()+deltaX<getWidth() 
				&& hero.getY()+deltaY>0 
				&& hero.getY()+deltaY<getHeight()) { 
			hero.setX(hero.getX()+deltaX); 
			hero.setY(hero.getY()+deltaY);
		}
	} 
	
	// thread que contiene el game loop 
	public void run() {
		iniciar(); 
		
		while (true) {
			// Actualizar fondo de pantalla 
			doScroll();
			
			// Actualizar posición del jugador 
			computePlayer();
			
			// Actualizar pantalla 
			repaint(); 
			//serviceRepaints();
		
			try { 
				Thread.sleep(sleepTime);
			} 
			catch (InterruptedException e) { 
				System.out.println(e.toString());
			}
		} 
	}
	
	@Override
	public void keyReleased(KeyEvent e) { 
		//int action=getGameAction(keyCode);
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT: 
				deltaX=0;
				break; 
			case KeyEvent.VK_RIGHT:
				deltaX=0;
				break; 
			case KeyEvent.VK_UP:
				deltaY=0;
				break; 	
			case KeyEvent.VK_DOWN:
				deltaY=0;
				break; 
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) { 
		//int action=getGameAction(keyCode); 
		
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT: 
				deltaX=-5;
				break; 
			case KeyEvent.VK_RIGHT:
				deltaX=5;
				break; 	
			case KeyEvent.VK_UP:
				deltaY=-5;
				break; 
			case KeyEvent.VK_DOWN:
				deltaY=5;
				break; 
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int x=0,y=0,t=0; 
		int i,j;
		
		g.setColor(Color.white); 
		g.fillRect(0,0,getWidth(),getHeight()); 
		g.setColor(new Color(200,200,0));
		
		// Dibujar fondo
		for (i=0 ; i<yTiles ; i++) {
			for (j=0 ; j<xTiles ; j++) { 
				t=map[indice+(i*xTiles+j)];
				// calculo de la posición del tile 
				x=j*100;//32;
				y=(i-1)*100 /*32*/+indice_in;
				// dibujamos el tile 
				tile[t].setX(x); 
				tile[t].setY(y); 
				tile[t].draw(g);
			} 
		}
		// Dibujar el jugador
		hero.setX(hero.getX()); 
		hero.setY(hero.getY()); 
		hero.draw(g);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}