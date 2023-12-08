package trees;

import java.io.Serializable;



import cu.edu.cujae.ceis.tree.lexicographical.DoesNotImplementsComparable;


public class BinarySearchTree<E> implements Serializable{


	/**
	 * 
	 */

	private BinaryNode<E> root;
	private int size;
	private static final long serialVersionUID = 1L;



	public BinarySearchTree(){
		this.root = null;
		this.size = 0;
	}

	public BinaryNode<E> getRoot() {
		return root;
	}

	public void setRoot(BinaryNode<E> root) {
		this.root = root;
	}

	public boolean isEmpty(){ // Metodo para determinar si el arbol esta vacio
		boolean empty = true;

		if (this.root != null)
			empty = false;

		return empty;
	}

	public int totalNodes(){
		return this.size;
	}

	public void clear(){
		this.root = null;
		this.size = 0;
	}

	public void add(E elemento) throws Exception, DoesNotImplementsComparable{
		if (implementsComparable(elemento)){
			if (!this.isEmpty())
				add(elemento, this.root);
			else
				setRoot(new BinaryNode<E>(elemento));

			this.size++;


		}
		else
			throw new DoesNotImplementsComparable("El objeto que se intenta insertar no implementa la interfaz Comparable");	

	}

	public void addAll(BinarySearchTree<E> tree) throws Exception, DoesNotImplementsComparable{
		SymmetricIterator<E> iter = tree.symmetricIterator();

		while(iter.hasNext()){
			this.add(iter.next());
		}
	}

	public E search(E elemento) throws Exception{ // Metodo para saber si un elemento esta en el arbol
		E elementoFind = null;
		if (!this.isEmpty())
			elementoFind = search(elemento, this.root);
		else
			throw new Exception("Arbol vacio");

		return elementoFind;
	}

	@SuppressWarnings({ "unchecked" })
	private E  search(E elemento, BinaryNode<E> nodo){  // Metodo de busqueda recursivo
		E elementoFind = null;

		if (((Comparable<E>) elemento).compareTo(nodo.getInfo()) > 0){
			if (nodo.getRight() != null)
				elementoFind = search(elemento, nodo.getRight());

		}
		else if (((Comparable<E>) elemento).compareTo(nodo.getInfo()) < 0){
			if (nodo.getLeft() != null)
				elementoFind = search(elemento, nodo.getLeft());			
		}
		else{
			elementoFind = nodo.getInfo();
		}

		return elementoFind;
	}

	@SuppressWarnings("unchecked")
	private void add(E elemento, BinaryNode<E> nodo) throws Exception{ //Metodo para insertar elemento en el arbol de acuerdo al orden lexicografico

		if (((Comparable<E>) elemento).compareTo(nodo.getInfo()) > 0){ // si el elemento es lexicograficamente mayor que el nodo de ese nivel, se insertara por la derecha
			if (nodo.getRight() == null)  
				nodo.setRight(new BinaryNode<E>(elemento));  
			else
				add(elemento, nodo.getRight());
		}
		else if (((Comparable<E>) elemento).compareTo(nodo.getInfo()) < 0){ // si el elemento es lexicograficamente menor que el nodo de ese nivel, se insertara por la izquierda
			if (nodo.getLeft() == null)
				nodo.setLeft(new BinaryNode<E>(elemento));
			else
				add(elemento, nodo.getLeft());
		}
		else{  // si el elemento es lexicograficamente igual que el nodo de ese nivel, se lanzara una exception
			throw new Exception("No se permiten duplicados");
		}	
	}


	public E delete(E elemento) throws Exception{
		E elementoAEliminar = null;
		if (!this.isEmpty())
			elementoAEliminar =	this.delete(elemento, this.root, null);
		else
			throw new Exception("Arbol vacio");

		if (elementoAEliminar == null)
			throw new Exception("No se encontro al elemento");

		this.size--;

		return elementoAEliminar;
	}

	private BinaryNode<E> onlySon(BinaryNode<E> nodo){ //  Metodo para devolver el unico hijo de un nodo que solo tenga un hijo
		BinaryNode<E> hijo = null;
		if (nodo.getLeft() != null)
			hijo = nodo.getLeft();
		else
			hijo = nodo.getRight();

		return hijo;
	}

	//Metodos para obtener el elemento maximo y minimo
	public E getMaxElement() throws Exception{
		E element = null;

		if (!isEmpty())
			element = this.getMaxNode(this.root).getInfo();
		else
			throw new Exception("Arbol vacio");

		return element;
	}


	public E getMinElement() throws Exception{
		E element = null;

		if (!isEmpty())
			element = this.getMinNode( this.root).getInfo();
		else
			throw new Exception("Arbol vacio");

		return element;
	}

	//Metodos para la obtencion de nodos maximos y minimos

	public BinaryNode<E> getMaxNode() throws Exception{
		BinaryNode<E> nodeMax = null;

		if (!isEmpty())
			nodeMax = getMaxNode( this.root);
		else
			throw new Exception("Arbol vacio");

		return nodeMax;
	}

	private BinaryNode<E> getMaxNode(BinaryNode<E> nodo){
		BinaryNode<E> nodeMax = null;

		if (nodo.getRight() != null)
			nodeMax = getMaxNode(nodo.getRight());
		else
			nodeMax = nodo;

		return nodeMax;
	}

	public BinaryNode<E> getMinNode() throws Exception{
		BinaryNode<E> nodeMin = null;

		if (!isEmpty())
			nodeMin = getMinNode( this.root);
		else
			throw new Exception("Arbol vacio");

		return nodeMin;
	}

	private BinaryNode<E> getMinNode(BinaryNode<E> nodo){
		BinaryNode<E> nodeMin = null;

		if (nodo.getLeft() != null)
			nodeMin = getMinNode(nodo.getLeft());
		else
			nodeMin = nodo;

		return nodeMin;
	}

	//fin

	private BinaryNode<E> deleteMaxIzq (BinaryNode<E> nodo, BinaryNode<E> padre){ //Metodo para encontrar y eliminar el nodo mas grande del subArbol izquierdo
		BinaryNode<E> maxIzq = null;

		if (nodo.getRight() != null)
			maxIzq = deleteMaxIzq(nodo.getRight(), nodo);
		else{
			maxIzq = nodo;      // una vez encontrado se guarda la referencia del nodo

			deleteNodo(nodo, padre); // se elimina ese nodo del arbol
		}

		return maxIzq;

	}

	private void deleteNodo(BinaryNode<E> nodo, BinaryNode<E> padre){ //metodo para eliminar un nodo de un arbol
		if (nodo.getLeft() != null && nodo.getRight() != null){ //Nodo con dos hijos

			nodo.setInfo(deleteMaxIzq(nodo.getLeft(), nodo).getInfo());		
		}
		else if (nodo.getLeft() != null || nodo.getRight() != null){ //Nodo con al menos un hijo

			if (!this.root.equals(nodo)){
				System.out.println("Padre:" + padre);
				if (padre.getLeft() != null && padre.getLeft().equals(nodo))
					padre.setLeft(onlySon(nodo));
				else
					padre.setRight(onlySon(nodo));
			}
			else
				this.setRoot(onlySon(nodo));
		}
		else{ //Nodo sin hijos

			if (!this.root.equals(nodo)){
				if (padre.getLeft() != null && padre.getLeft().equals(nodo))
					padre.setLeft(null);
				else
					padre.setRight(null);
			}
			else
				this.setRoot(null);
		}
	}

	@SuppressWarnings("unchecked")
	private E delete(E elemento, BinaryNode<E> nodo, BinaryNode<E> padre){ //Metodo para encontrar y eliminar el nodo que contiene a un elemento dado
		E elementoAEliminar = null;

		if (((Comparable<E>) elemento).compareTo(nodo.getInfo()) > 0){
			if (nodo.getRight() != null)
				elementoAEliminar = delete(elemento, nodo.getRight(), nodo);

		}
		else if (((Comparable<E>) elemento).compareTo(nodo.getInfo()) < 0){
			if (nodo.getLeft() != null)
				elementoAEliminar = delete(elemento, nodo.getLeft(), nodo);			
		}
		else{
			elementoAEliminar = nodo.getInfo();
			deleteNodo(nodo, padre);
		}

		return elementoAEliminar;
	}

	// Iteradores

	public SymmetricIterator<E> symmetricIterator(){
		return new SymmetricIterator<E>(this);
	}


	private boolean implementsComparable(Object object){
		boolean doesImplements = false;

		Class[] classes = object.getClass().getInterfaces();

		int pos = 0;

		while(pos < classes.length && !doesImplements){
			if(classes[pos].getCanonicalName().equals("java.lang.Comparable"))
				doesImplements = true;

			pos++;
		}			

		return doesImplements;
	}
}
