//package proyectoEstructura;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class VentanaPiano extends JPanel implements Runnable, ActionListener{
	private Image fondo;
	private SLinkedList lista;
	private int y1,
				sig,
				puntaje;
	private boolean siguiente;
	int flecha;
	private String Nombre, ID, letra;
	private int caracter;
	
	
	File Do = new File("DoS.wav");
	File Re = new File("ReS.wav");
	File Mi = new File("MiS.wav");
	File Fa = new File("FaS.wav");
	File Sol = new File("SolS.wav");
	File La = new File("LaS.wav");
	
	File Cancion;
	private JButton CargarCancion;
	
	public VentanaPiano (){
		super();
		this.setPreferredSize(new Dimension(1400, 700));
		this.lista = new SLinkedList();
		int rand;
		for(int i=0;i<50;i++){
			rand = (int) (Math.random()*7+1);
			this.lista.add(i, rand);
		}
		
		
		
		this.CargarCancion= new JButton("Cargar Canción");
		this.add(CargarCancion);
		this.CargarCancion.addActionListener(this);
		
		this.fondo=new ImageIcon("fondo.jpg").getImage();
		this.siguiente=true;
		this.y1=0;
		this.sig=0;
		this.puntaje=0;
		
		///////////// Key listener
		
		this.addKeyListener(new KeyListener(){
				public void keyPressed(KeyEvent e) {
					
					//System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
					VentanaPiano.this.letra = KeyEvent.getKeyText(e.getKeyCode());
					VentanaPiano.this.caracter = (int) VentanaPiano.this.letra.charAt(0) - 48;
					int c = VentanaPiano.this.lista.get(VentanaPiano.this.sig);
					if(VentanaPiano.this.caracter == c && (VentanaPiano.this.y1 - 430) > (-50)){
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
					int tecla = /*(String)KeyEvent.getKeyText(e.getKeyCode())*/VentanaPiano.this.caracter;
					System.out.print(tecla);
					//A
					if(tecla==17){
						PlaySound(Do);
					}
					//B
					else if(tecla==35){
						PlaySound(Re);
					}
					//C
					else if(tecla==20){
						PlaySound(Mi);
					}
					//D
					else if(tecla == 22){
						PlaySound(La);
					}
				}
			
			public void keyReleased(KeyEvent e) {}
			
			public void keyTyped(KeyEvent e) {
				
			}
			});
		
		//////////// Key listener
		
		this.setFocusable(true);
		Thread hilo= new Thread(this);
		hilo.start();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("LOL:"+tfSemanas.getText());
		
		if(arg0.getSource() == this.CargarCancion){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
			    this.Cancion = fileChooser.getSelectedFile();
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(this.fondo, 0, 0,this.getWidth(),this.getHeight(), this);
		this.dibujaTablero(g);
		this.dibujarCondiciones(g);
	}
	public void dibujaTablero(Graphics g){

		g.setColor(new Color(255, 0, 178));
		//1
		g.fillOval(100, 450, 120, 120);
		//2
		g.fillOval(250, 450, 120, 120);
		//3
		g.fillOval(400, 450, 120, 120);
		//4
		g.fillOval(550, 450, 120, 120);
		//5
		g.fillOval(700, 450, 120, 120);
		g.fillOval(850, 450, 120, 120);
		g.fillOval(1000, 450, 120, 120);	
		g.setColor(Color.WHITE);
		int ax[]={150,150,127,150,150,210,210,150};
		int ay[]={480,465,500,535,520,520,480,480};
		g.fillPolygon(ax, ay, 8);
		int dx[]={1040,1100,1100,1120,1100,1100,1040,1040};
		int dy[]={480,480,465,500,535,520,520,480};
		g.fillPolygon(dx, dy, 8);
		Font myFont = new Font ("Showcard Gothic", 1, 50);	
		g.setFont (myFont);
		g.drawString(String.valueOf(this.puntaje), 70,90 );
	}
	
	public void dibujarCondiciones(Graphics g){
		//if(this.siguiente){
		do{
			if(this.lista.get(this.sig)==1){
				this.pintaflechas(g, new Color(255, 0, 178), 100, 20);
			}
			if(this.lista.get(this.sig)==2){
				this.pintaflechas(g, new Color(255, 0, 178), 250, 20);
			}
			if(this.lista.get(this.sig)==3){
				this.pintaflechas(g, new Color(255, 0, 178), 400, 20);
			}
			if(this.lista.get(this.sig)==4){
				this.pintaflechas(g, new Color(255, 0, 178), 550, 20);
			}
			if(this.lista.get(this.sig)==5){
				this.pintaflechas(g, new Color(255, 0, 178), 700, 20);
			}
			if(this.lista.get(this.sig)==6){
				this.pintaflechas(g, new Color(255, 0, 178), 850, 20);
			}
			if(this.lista.get(this.sig)==7){
				this.pintaflechas(g, new Color(255, 0, 178), 1000, 20);
			}
		//System.out.println("this.sig= "+this.sig);
		}while(this.siguiente && this.sig<50);
		}
	//}
	public void pintaflechas(Graphics g, Color color, int xFlecha, int yFlecha){
			g.setColor(color);
			g.fillOval(xFlecha,yFlecha+this.y1,120, 120);
			
	}
	
	public static void main(String[] args) {
		
		VentanaPiano ven= new VentanaPiano();
		JFrame jf= new JFrame();
		jf.setTitle("PXY interactive");
		jf.getContentPane().add(ven);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	
	}
	
	public static void PlaySound(File Sound){
		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();

			//Thread.sleep(clip.getMicrosecondLength()/10000);

		}catch(Exception e){

		}
	}
	
	@Override
	public void run() {
		try{
			while(this.y1<=430){
				this.siguiente=false;
					this.y1+=this.lista.getVel(this.sig);
					this.repaint();
				Thread.sleep(15);
			if(this.y1>430){
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
	

}