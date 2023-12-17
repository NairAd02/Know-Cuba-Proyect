package modelosTablas;

public interface ModelOperations <T> {
	public void addElement (T element);
	public T getElement (int pos);
	public T deleteElement (int pos);
}
