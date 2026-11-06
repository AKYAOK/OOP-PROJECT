public class LXBuilding extends Building {
 
    public LXBuilding(String buildingId, String buildingName) {
        super(buildingId, buildingName);
        buildFloor10();
        buildFloor11();
        buildFloor12();
    }
 
    // รหัสห้อง = รหัสตึก + เลขห้อง เช่น LX + 1001 -> LX1001
    private String id(int number) {
        return getBuildingId() + number;
    }
 
    // ชั้น 10: ห้องประชุม 2 ห้อง + Study 2 ห้อง
    private void buildFloor10() {
        addFloor(10);
        addRoom(new meetingRoom(id(1001), 10, 20, 5, 7, 3, true));
        addRoom(new meetingRoom(id(1002), 10, 40, 8, 10, 3, true));
        addRoom(new StudyRoom(id(1003), 10, 12, 4, 5, 3, true));
        addRoom(new StudyRoom(id(1004), 10, 12, 4, 5, 3, false));
    }
 
    // ชั้น 11: Study 4 ห้อง
    private void buildFloor11() {
        addFloor(11);
        addRoom(new StudyRoom(id(1101), 11, 10, 4, 5, 3, true));
        addRoom(new StudyRoom(id(1102), 11, 10, 4, 5, 3, true));
        addRoom(new StudyRoom(id(1103), 11, 16, 5, 6, 3, false));
        addRoom(new StudyRoom(id(1104), 11, 16, 5, 6, 3, true));
    }
 
    // ชั้น 12: Lab 3 ห้อง (รวม Lab ใหญ่) + ห้องประชุม 2 ห้อง (รวมห้องประชุมใหญ่)
    private void buildFloor12() {
        addFloor(12);
        addRoom(new labRoom(id(1201), 12, 50, 9, 11, 3, 40));
        addRoom(new labRoom(id(1202), 12, 50, 9, 11, 3, 40));
        addRoom(new labRoom(id(1203), 12, 120, 14, 16, 4, 100));
        addRoom(new meetingRoom(id(1204), 12, 20, 5, 7, 3, true));
        addRoom(new meetingRoom(id(1205), 12, 150, 15, 20, 4, true));
    }
}
