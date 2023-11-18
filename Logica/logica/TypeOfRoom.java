package logica;

public class TypeOfRoom {
	private int id;
	private String type_of_room_name;
	
	public TypeOfRoom(String type_of_room_name) {
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
