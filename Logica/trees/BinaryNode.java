package trees;

import java.io.Serializable;

public class BinaryNode<E> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private E info;
	private BinaryNode<E> left;
	private BinaryNode<E> right;
	
	
	public BinaryNode(E info) {

		this.info = info;
		this.left = null;
		this.right = null;
	}


	public E getInfo() {
		return info;
	}


	public void setInfo(E info) {
		this.info = info;
	}


	public BinaryNode<E> getLeft() {
		return left;
	}


	public void setLeft(BinaryNode<E> left) {
		this.left = left;
	}


	public BinaryNode<E> getRight() {
		return right;
	}


	public void setRight(BinaryNode<E> right) {
		this.right = right;
	}
	
	
	
	
}
