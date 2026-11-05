public class meetingRoom extends abstractRoom {

    private String topic;
    private boolean hasProjector;
    
    public meetingRoom(String roomID, int floor, int maxCapacity, double width, double length, double height, boolean hasProjector) {
        super(roomID, floor, maxCapacity, width, length, height);
    }

    public void setTopic(String topic){
        this.topic = topic;
    }

    public String getTopic() {return topic;}    
    
    public boolean hasProjector() {return hasProjector;}

    public String getRoomType() {
        return "Meeting Room";
    }
}
