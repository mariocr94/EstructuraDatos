import java.util.NoSuchElementException;
public class SLinkedList<E> {

    private Node<E> firstNode;
    private int size;

    public SLinkedList(){
        this.firstNode = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
       return size;
    }
  
    public int getFirst(){ 
        if(firstNode != null){
            return firstNode.getData();
        }
        else{
            throw new NoSuchElementException ("Empty List!");
        }
    }

    public int getLast(){	
		if(this.size == 0){
			throw new NoSuchElementException ("Empty List!");
		}else{
			Node<E> ant = null;
			ant = firstNode.getNext();
			while(ant.getNext() !=null){
				ant=ant.getNext();
			}
			return ant.getData();
		}

	} 

	public void add(int index, int data){  
		if(index<0 || index > this.size){
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		if(index == 0){
			addFirst(data);
		}
		else{
			Node<E> ant;
			int aux=0;
			ant=this.firstNode;
			while(aux<index-1){
				ant=ant.getNext();
				aux++;
			}
			Node<E> nuevo = new Node(ant.getNext(),data);
			ant.setNext(nuevo);
			size++;
		}
	}
	
	public void addFirst(int data){  
			this.firstNode = new Node(this.firstNode, data);
			this.size++; 
	}
	
	public boolean contains(E data){
		if(this.size == 0){
			return false;
		}
		Node<E> ant;
		ant=this.firstNode;
		boolean bandera = false;
		for(int i=0; i<size;i++){
			if (data.equals(ant.getData())){
				return true;
			}else{
				ant=ant.getNext();
			}
		}
		return false;
	}
	
	public int indexOf(E data){
		int aux= -1;
		if(this.size != 0){
			Node<E> ant;
			ant=this.firstNode;
			for(int i=0; i<size;i++){
				if (data.equals(ant.getData())){
					aux = i;
					return aux;
				}else{
					ant=ant.getNext();
				}
			}
		}
		return aux;
		
	}

	public int get(int index){
		if(this.size == 0){
			throw new NoSuchElementException ("Empty List!");
		}else if(index == 0){
			return getFirst();
		}else{
			Node<E> ant = null;
			ant = firstNode;
			for(int i=0;i<index;i++){
				ant = ant.getNext();
			}
			return ant.getData();
		}
	}
	
	public int getVel(int index){
		if(this.size == 0){
			throw new NoSuchElementException ("Empty List!");
		}else if(index == 0){
			return firstNode.getVel();
		}else{
			Node<E> ant = null;
			ant = firstNode;
			for(int i=0;i<index;i++){
				ant = ant.getNext();
			}
			return ant.getVel();
		}
	}
	
	public boolean remove(int index){
		if(index > size() || index < 0){
			return false;
		}
		if(index == 0){
			firstNode = firstNode.getNext();
			size--;
			return true;
		}
		Node<E> actual = firstNode;
		if(firstNode != null){
			for(int i=0;i<index-1;i++){
				actual = actual.getNext();
			}
			actual.setNext(actual.getNext().getNext());
			size--;
			return true;
		}
		return false;
	}
	
	public String toString(){
		
		
		String lista = "";
		
		if (firstNode != null){
			Node<E> actual = firstNode;
			while (actual != null){
				lista += "[" + actual.getData() + "]";
				actual = actual.getNext();
			}
		}
		return lista;
	}

	public void reverse(){  
		Node<E> previous = null;  
		Node<E> actual = firstNode;  
		while(actual!=null){
			Node<E> siguiente = actual.getNext();  
		    actual.setNext(previous); 
		    previous = actual;  
		    actual = siguiente;  
		}  
		firstNode = previous;  
	} 


}



// javac "SLinkedList.java" (en el directorio: C:\Users\mario\OneDrive\Documentos\ITESM\6to Semestre\Estructura de Datos)
