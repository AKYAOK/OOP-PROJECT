public class StudyRoom extends abstractRoom {
    private boolean hasWhiteboard;

    public StudyRoom(String roomID, int floor, int maxCapacity, double width, double length, double height, boolean hasWhiteboard) {
        super(roomID, floor, maxCapacity, width, length, height);
        this.hasWhiteboard = hasWhiteboard;
    }

    public boolean isHasWhiteboard() { return hasWhiteboard; }

    public String getRoomType() {
        return "Study Room";
    }
}
