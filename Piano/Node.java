public class Node<E>{

    int data;
    float velocidad;
    int velocidadSet;
    Node<E> next;

    public Node(Node<E> n, int d, int tiempo){
		this.next=n;
		this.data=d;
		float normal = tiempo/600f;
		float vel = (15 - (normal * 10));
		this.velocidadSet = (int) (Math.random()*10+5);
		this.velocidad = vel;
		//System.out.println(this.velocidad);
	}
	public Node(){
		this(null, 0, 0);
	}
	public Node(int d){
		this(null, d, 0);
	}
	public int getData(){
		return this.data;
	}
	public float getVel(){
		return this.velocidad;
	}
	public int getVelS(){
		return this.velocidadSet;
	}
	public Node<E> getNext(){
		return this.next;
	}
	public void setData(int d){
		this.data=d;
	}
	public void setNext(Node<E> n){
		this.next=n;
	}
}
