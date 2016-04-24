package model;

public class RoomConfiguration {

	private int creatorId;
	private String roomName;
	
	public RoomConfiguration(String roomName, int creatorId)
	{
		this.roomName = roomName;
		this.creatorId = creatorId;				
	}

	public int getCreatorId() {
		return creatorId;
	}

	public String getRoomName() {
		return roomName;
	}

}
