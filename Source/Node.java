public class Node<E>{

    int data;
    int velocidad;
    Node<E> next;

    public Node(Node<E> n, int d){
		this.next=n;
		this.data=d;
		this.velocidad = (int) (Math.random()*10+6);
	}
	public Node(){
		this(null, 0);
	}
	public Node(int d){
		this(null, d);
	}
	public int getData(){
		return this.data;
	}
	public int getVel(){
		return this.velocidad;
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
