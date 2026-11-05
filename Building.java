import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Building {
    private String buildingId;
    private String buildingName;
    private Map<String, abstractRoom> rooms;          // key = roomID
    private Set<Integer> floors;              // ชั้นที่มีในอาคาร

    public Building(String buildingId, String buildingName) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.rooms = new HashMap<>();
        this.floors = new HashSet<>();
    }

    /** เพิ่มห้อง (ถ้าชั้นของห้องยังไม่มี จะเพิ่มชั้นให้อัตโนมัติ) */
    public void addRoom(abstractRoom room) {
        if (room == null) {
            throw new IllegalArgumentException("room must not be null");
        }
        if (rooms.containsKey(room.getRoomID())) {
            throw new IllegalArgumentException("Room " + room.getRoomID() + " already exists");
        }
        addFloor(room.getFloor());
        rooms.put(room.getRoomID(), room);
    }

    /** เพิ่มชั้นใหม่ในอาคาร */
    public void addFloor(int floorNumber) {
        if (floorNumber < 0) {
            throw new IllegalArgumentException("floorNumber must be >= 0");
        }
        floors.add(floorNumber);
    }

    /** คืนรายการห้องว่างทั้งหมดในชั้นที่ระบุ */
    public List<abstractRoom> getEmptyRoom(int whichFloor) {
        List<abstractRoom> result = new ArrayList<>();
        for (abstractRoom r : rooms.values()) {
            if (r.getFloor() == whichFloor && !r.isOccupy()) {
                result.add(r);
            }
        }
        return result;
    }

    /** คืนห้องตามรหัสห้อง ถ้าห้องว่าง; ถ้าไม่มีห้องหรือไม่ว่าง คืน null */
    public abstractRoom getEmptyRoom(String whichRoom) {
        abstractRoom r = rooms.get(whichRoom);
        return (r != null && !r.isOccupy()) ? r : null;
    }

    public String getBuildingId()   { return buildingId; }
    public String getBuildingName() { return buildingName; }
    public Map<String, abstractRoom> getRooms() { return rooms; }
    public Set<Integer> getFloors() { return floors; }
}
