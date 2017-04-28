import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class VentanaPiano extends JPanel implements Runnable, MouseListener{
	private Image fondo,fondoM, rosa, azul, amarillo, verde, base,naveAz, naveAm, naveRo, naveV,apoyo;
	private SLinkedList lista;
	private int y1,
				sig,
				puntaje;
	private boolean siguiente;
	int flecha;
	private String Nombre, ID, letra;
	private int caracter;
	private boolean girl, start;
	private int constante;
	
	public VentanaPiano (){
		super();
		this.setPreferredSize(new Dimension(1400, 700));
		this.lista = new SLinkedList();
		int rand;
		for(int i=0;i<50;i++){
			rand = (int) (Math.random()*4+1);
			this.lista.add(i, rand, 600);
		}
		this.girl=true;
		if(this.girl==true){
			this.importaGirl();
		}
		else{
			this.importaBoy();
		}
		
		this.siguiente=true;
		this.y1=0;
		this.sig=0;
		this.puntaje=0;
		this.fondoM=new ImageIcon("fondoMenu.png").getImage();
		
		

		///////////// Key listener
		this.addKeyListener(new KeyListener(){
				public void keyPressed(KeyEvent e) {
					
					System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
					VentanaPiano.this.letra = KeyEvent.getKeyText(e.getKeyCode());
					VentanaPiano.this.caracter = (int) VentanaPiano.this.letra.charAt(0) - 48;
					int c = VentanaPiano.this.lista.get(VentanaPiano.this.sig);
					if(VentanaPiano.this.caracter == c && (VentanaPiano.this.y1 - 470) > (-50)){
						VentanaPiano.this.puntaje+=10;
						VentanaPiano.this.siguiente=true;
						VentanaPiano.this.sig++;
						VentanaPiano.this.y1=0;
					}else{
						VentanaPiano.this.puntaje-=5;
						VentanaPiano.this.siguiente=true;
						VentanaPiano.this.sig++;
						VentanaPiano.this.y1=0;
					}
				}
			
			public void keyReleased(KeyEvent e) {}
			
			public void keyTyped(KeyEvent e) {}
			});
		
		//////////// Key listener
		
		this.setFocusable(true);
		Thread hilo= new Thread(this);
		hilo.start();
		//Fin de constructor
	}
	
	public void importaGirl(){
		this.constante=1;
		this.fondo=new ImageIcon("fondoG.jpg").getImage();
		this.rosa=new ImageIcon("azul.png").getImage();
		this.azul=new ImageIcon("azul.png").getImage();
		this.verde=new ImageIcon("naranja.png").getImage();
		this.amarillo=new ImageIcon("naranja.png").getImage();
		this.base=new ImageIcon("canastaa.png").getImage();
		
	}
	
	public void importaBoy(){
		this.constante=2;
		this.fondo=new ImageIcon("estrellas.jpg").getImage();
		this.rosa=new ImageIcon("alienRos.png").getImage();
		this.amarillo=new ImageIcon("alienAma.png").getImage();
		this.azul=new ImageIcon("alienAzul.png").getImage();
		this.verde=new ImageIcon("alienNar.png").getImage();
		this.naveAz=new ImageIcon("planet.png").getImage();
		this.naveV=new ImageIcon("naveAzul.png").getImage();
		this.naveRo=new ImageIcon("spacena.png").getImage();
		this.naveAm=new ImageIcon("naveNa.png").getImage();
		this.apoyo=new ImageIcon("apoyo2.png").getImage();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(this.fondo, 0, 0,this.getWidth(),this.getHeight(), this);
		this.dibujaTablero(g);
		this.dibujarCondiciones(g);
		//this.dibujaMenu(g);
	}
	
	

	
	public void dibujaMenu(Graphics g){
		g.drawImage(this.fondoM, 0, 0,this.getWidth(),this.getHeight(), this);
		
		
	}
	public void dibujaTablero(Graphics g){
		//g.drawImage(this.rosa, 400, 400,100,100, this);
		//g.setColor(new Color);

		g.setColor(new Color(28, 103, 178));
		g.drawImage(this.apoyo, 1100, 100,250,500, this);
		//1 Azul 
		g.drawImage(this.naveAz, 70, 10,200,200, this);
		
		g.fillOval(70, 610, 170, 40);
		g.drawImage(this.base, 70, 490,170,150, this);
		g.setColor(new Color(255, 238, 12));
		//2 Amarrillo
		g.fillOval(320, 610, 170, 40);
		
		g.drawImage(this.base, 320, 490,170,150, this);
		g.setColor(new Color(255, 12, 76));
		//3 rojo
		g.fillOval(570, 610, 170, 40);
		
		g.drawImage(this.base, 570, 490,170,150, this);
		//4 verde
		g.setColor(new Color(9, 249, 81));
		g.fillOval(820, 610, 170, 40);
		
		g.drawImage(this.base, 820, 490,170,150, this);
		
		//g.setColor(Color.WHITE);
		g.setColor(new Color(249, 116, 9));
		Font myFont = new Font ("Showcard Gothic", 1, 50);	
		g.setFont (myFont);
		g.drawString(String.valueOf(this.puntaje), 70,90 );
	}
	
	
	public void dibujaGirl(Graphics g){
		this.fondo=new ImageIcon("fondo.jpg").getImage();
	}
	public void dibujaBoy(Graphics g){
		this.fondo=new ImageIcon("fondo.jpg").getImage();
	}
	
	public void dibujarCondiciones(Graphics g){
		//if(this.siguiente){
		do{
			if(this.lista.get(this.sig)==1){
				
				this.pintaflechas(g, new Color(255, 0, 178), 100, 20);
			}
			if(this.lista.get(this.sig)==2){
				g.drawImage(this.naveAm, 320, 10,200,200, this);
				this.pintaflechas(g, new Color(255, 0, 178), 350, 20);
			}
			if(this.lista.get(this.sig)==3){
				g.drawImage(this.naveRo, 570, 10,200,200, this);
				this.pintaflechas(g, new Color(255, 0, 178), 600, 20);
			}
			if(this.lista.get(this.sig)==4){
				g.drawImage(this.naveV, 820, 10,200,200, this);
				this.pintaflechas(g, new Color(255, 0, 178), 850, 20);
			}
			
		//System.out.println("this.sig= "+this.sig);
		}while(this.siguiente && this.sig<50);
		}
	//}
	public void pintaflechas(Graphics g, Color color, int xFlecha, int yFlecha){
			g.setColor(color);
			//azul, amarillo, rojo verde
			//g.drawImage(rosa, xFlecha, yFlecha, this);
			
			//g.fillOval(xFlecha,yFlecha+this.y1,120, 120);
			
			if(xFlecha==100){
				g.drawImage(this.azul, xFlecha, yFlecha+this.y1,100,100*constante, this);

			}
			if(xFlecha==350){
				g.drawImage(this.amarillo, xFlecha, yFlecha+this.y1,140,100*constante, this);
			}
			if(xFlecha==600){
				g.drawImage(this.rosa, xFlecha, yFlecha+this.y1,100,100*constante, this);
			}
			if(xFlecha==850){
				g.drawImage(this.verde, xFlecha, yFlecha+this.y1,100,100*constante, this);
			}
				}
	
	public static void main(String[] args) {
		VentanaPiano ven= new VentanaPiano();
		JFrame jf= new JFrame();
		jf.setTitle("Aliens and cakes");
		jf.getContentPane().add(ven);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	@Override
	public void run() {
		try{
			while(this.y1<=500){
				this.siguiente=false;
					this.y1+=this.lista.getVelS(this.sig);
					this.repaint();
				Thread.sleep(15);
			if(this.y1>500){
				this.siguiente=true;
				this.sig++;
				this.y1=0;
				this.puntaje-=5;
			}
			if(this.sig==50){
				int reinicio=JOptionPane.showConfirmDialog(null, "Se ha terminado el juego, ¿quieres reiniciar?",null,JOptionPane.YES_NO_OPTION);
				if(reinicio==JOptionPane.YES_OPTION){
					this.siguiente=true;
					this.sig=0;
					this.y1=0;
					this.puntaje=0;
				}
				else{
					JOptionPane.showMessageDialog(null,"Gracias por jugar, vuelve pronto");
					System.exit(0);
					
				}
				break;
				
				
			}
			else{
				this.siguiente=false;
				//System.out.println("no termina aun");
			}
			}
		}catch(InterruptedException e){
			System.out.println(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
