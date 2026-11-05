public class labRoom extends abstractRoom {
    private int numberOfComputers;

    public labRoom(String roomID, int floor, int maxCapacity, double width, double length, double height, int numberOfComputers) {
        super(roomID, floor, maxCapacity, width, length, height);
        this.numberOfComputers = numberOfComputers;
    }

    public int getNumberOfComputers() { 
        return numberOfComputers; 
    }

    
    public String getRoomType() {
        return "Computer Lab Room";
    }
}

