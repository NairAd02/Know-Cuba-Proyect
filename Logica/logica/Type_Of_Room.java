package logica;

public class Type_Of_Room {
	private int id;
	private String type_of_room_name;
	
	public Type_Of_Room(String type_of_room_name) {
		this.type_of_room_name = type_of_room_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType_of_room_name() {
		return type_of_room_name;
	}
	public void setType_of_room_name(String type_of_room_name) {
		this.type_of_room_name = type_of_room_name;
	}
}
