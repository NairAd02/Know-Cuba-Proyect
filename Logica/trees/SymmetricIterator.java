package trees;

import java.util.Deque;
import java.util.LinkedList;

public class SymmetricIterator<E> {
	private	BinarySearchTree<E> tree;
	private Deque<BinaryNode<E>> stack;

	public SymmetricIterator (BinarySearchTree<E> tree){
		this.tree = tree;
		this.stack = new LinkedList<BinaryNode<E>>();
		if (!this.tree.isEmpty())
			this.inicializarPila();
	}

	private void inicializarPila(){ // Metodo para inicializar la pila

		this.agregarNodoYDescendientesIzq(this.tree.getRoot()); // inicializar pila con la raiz y todos sus hijos izquierdos

	}

	private void agregarNodoYDescendientesIzq(BinaryNode<E> nodo){
		if (nodo != null){
			BinaryNode<E> cursor = nodo;
			this.stack.push(cursor);
			while (cursor.getLeft() != null){ 
				this.stack.push(cursor.getLeft());
				cursor = cursor.getLeft();
			}
		}
	}

	public boolean hasNext(){
		boolean siguiente = false;

		if (!this.stack.isEmpty())
			siguiente = true;

		return siguiente;
	}

	public E next(){
		BinaryNode<E> nodoRetornar = null;
		if (!this.stack.isEmpty()){
			nodoRetornar = this.stack.pop();
			this.agregarNodoYDescendientesIzq(nodoRetornar.getRight()); // Agregar hijo derecho y todos los descendiente izquierdos de ese nodo
		}

		return nodoRetornar.getInfo();
	}

	public BinaryNode<E> nextNode(){

		BinaryNode<E> nodoRetornar = null;
		if (!this.stack.isEmpty()){
			nodoRetornar = this.stack.pop();
			this.agregarNodoYDescendientesIzq(nodoRetornar.getRight()); // Agregar hijo derecho y todos los descendiente izquierdos de ese nodo
		}

		return nodoRetornar;	
	}

}
