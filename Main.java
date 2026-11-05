import java.util.*;

public class Main {
    private static Map<String, Building> buildingMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // สร้างตึกเริ่มต้นไว้ในระบบ
        buildingMap.put("SIT", new SITBuilding("SIT", "SIT Building"));
        buildingMap.put("LX", new LXBuilding("LX", "LX Building"));

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n====================================================");
            System.out.println("building and room management system (OOP & Interface)");
            System.out.println("======================================================");
            System.out.println("1. add new building into the system");
            System.out.println("2. add  new room (choose room type)");
            System.out.println("3. find empty room (by floor / by room ID)");
            System.out.println("4. book a room");
            System.out.println("5. show all building and room");
            System.out.println("0. exit the program");
            System.out.print("choose what to do (0-5): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addNewBuilding();
                    break;
                case "2":
                    addRoomToBuilding();
                    break;
                case "3":
                    searchEmptyRoom();
                    break;
                case "4":
                    bookRoom();
                    break;
                case "5":
                    displayAllBuildings();
                    break;
                case "0":
                    isRunning = false;
                    System.out.println("👋 exited the program");
                    break;
                default:
                    System.out.println("❌ invalid choice, choose again");
            }
        }
    }

    // 1. เพิ่มตึกใหม่เข้าสู่ระบบ
    private static void addNewBuilding() {
        System.out.println("\n--- [add new building] ---");
        System.out.print("input building ID (ex. SIT, LX, CB1): ");
        String bId = scanner.nextLine().trim();

        if (buildingMap.containsKey(bId)) {
            System.out.println("❌ this ID has already been added to the system");
            return;
        }

        System.out.print("input building name: ");
        String bName = scanner.nextLine().trim();

        System.out.println("choose building: 1. SIT Building  2. LX Building");
        System.out.print("choose (1-2): ");
        String type = scanner.nextLine().trim();

        Building newBuilding;
        if (type.equals("1")) {
            newBuilding = new SITBuilding(bId, bName);
        } else {
            newBuilding = new LXBuilding(bId, bName);
        }

        buildingMap.put(bId, newBuilding);
        System.out.println("✅ \"" + bName + "\" added to the system!");
    }

    // 2. เพิ่มห้องเข้าตึก (ใช้ abstractRoom ตาม Building.java)
    private static void addRoomToBuilding() {
        System.out.println("\n--- [add room into the building] ---");
        if (buildingMap.isEmpty()) {
            System.out.println("❌ There is no building in the system");
            return;
        }

        System.out.print("input building ID to add room into: ");
        String bId = scanner.nextLine().trim();
        Building building = buildingMap.get(bId);

        if (building == null) {
            System.out.println("❌ building not found");
            return;
        }

        System.out.print("input room ID (ex. SIT101): ");
        String rId = scanner.nextLine().trim();

        System.out.print("input floor (ex. 1, 2, 3): ");
        int floor = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("input capacity (people): ");
        int capacity = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("input room width (m.): ");
        double w = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("input room length (m.): ");
        double l = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("input room height (m.):");
        double h = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("\nchoose room type to create:");
        System.out.println("1. Study Room (ห้องอ่านหนังสือ)");
        System.out.println("2. Lab Room (ห้องปฏิบัติการคอมพิวเตอร์)");
        System.out.println("3. Meeting Room (ห้องประชุม)");
        System.out.print("choose room type (1-3): ");

        abstractRoom newRoom; // เปลี่ยนชนิดตัวแปรเป็น abstractRoom

        while (true) { 
            String roomTypeChoice = scanner.nextLine().trim();
            if (roomTypeChoice.equals("1")) {
                System.out.print("consist of whiteboard? (y/n): ");
                boolean hasBoard = scanner.nextLine().trim().equalsIgnoreCase("y");
                newRoom = new StudyRoom(rId, floor, capacity, w, l, h, hasBoard);
                break;
            } else if (roomTypeChoice.equals("2")) {
                System.out.print("input computer amount in the room: ");
                int compCount = Integer.parseInt(scanner.nextLine().trim());
                newRoom = new labRoom(rId, floor, capacity, w, l, h, compCount); // ใช้ labRoom (l ตัวเล็ก)
                break;
            } else if (roomTypeChoice.equals("3")) {
                System.out.print("consist of projector?: ");
                boolean hasProjector = scanner.nextLine().trim().equalsIgnoreCase("y");
                System.out.print(": ");
                newRoom = new meetingRoom(rId, floor, capacity, w, l, h, hasProjector);
                break;
            } else{
                System.out.print("invalid choice, try again!!!: ");
            }
        }

        building.addRoom(newRoom);
        System.out.println("✅ added room [" + rId + "] type: " + newRoom.getRoomType() + " into " + building.getBuildingName() + " successfully!");
    }

    // 3. ค้นหาห้องว่าง
    private static void searchEmptyRoom() {
        System.out.println("\n--- [find empty room] ---");
        System.out.print("input building ID: ");
        String bId = scanner.nextLine().trim();
        Building building = buildingMap.get(bId);

        if (building == null) {
            System.out.println("❌😭 building not found");
            return;
        }

        System.out.println("how to search:");
        System.out.println("1. search by floor (int)");
        System.out.println("2. search by room ID (String)");
        System.out.print("choose (1-2): ");
        String searchChoice = scanner.nextLine().trim();

        if (searchChoice.equals("1")) {
            System.out.print("input floor: ");
            int floor = Integer.parseInt(scanner.nextLine().trim());
            
            List<abstractRoom> emptyRooms = building.getEmptyRoom(floor);
            System.out.println("\n🔍 result of searching floor " + floor + ":");
            if (emptyRooms.isEmpty()) {
                System.out.println("   no empty room in this floor");
            } else {
                for (abstractRoom r : emptyRooms) {
                    System.out.println("   - room " + r.getRoomID() + " [" + r.getRoomType() + "] volume: " + r.getVolume() + " m³");
                }
            }
        } else if (searchChoice.equals("2")) {
            System.out.print("input room ID: ");
            String rId = scanner.nextLine().trim();
            
            abstractRoom r = building.getEmptyRoom(rId);
            if (r != null) {
                System.out.println("✅ room " + r.getRoomID() + " [" + r.getRoomType() + "] is empty and ready to be used!");
            } else {
                System.out.println("❌ room not found or not free");
            }
        }
    }

    // 4. จองห้องพัก
    private static void bookRoom() {
        System.out.println("\n--- [book room] ---");
        System.out.print("input building ID: ");
        String bId = scanner.nextLine().trim();
        Building building = buildingMap.get(bId);

        if (building == null) {
            System.out.println("❌ building not found");
            return;
        }

        System.out.print("input room to book: ");
        String rId = scanner.nextLine().trim();
        abstractRoom room = building.getRooms().get(rId);

        if (room == null) {
            System.out.println("❌ room not found");
            return;
        }

        if (room.isOccupy()) {
            System.out.println("⚠️ room has already been booked! " + room.getBookTime());
            return;
        }

        System.out.print("input start time (ex. 9 for 09:00 am.): ");
        int start = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("input end time (ex. 12 for 12:00 pm.): ");
        int end = Integer.parseInt(scanner.nextLine().trim());

        boolean success = room.book(start, end);
        if (success) {
            System.out.println("🎉 booked room " + room.getRoomID() + " successfully!");
            System.out.println("   booked time: " + room.timeThatTheRoomNotEmpty() + " hour(s)");
        } else {
            System.out.println("❌ booking failed");
        }
    }

    // 5. แสดงข้อมูลตึกและห้องทั้งหมด
    private static void displayAllBuildings() {
        System.out.println("\n===== all buildings and rooms in the system =====");
        if (buildingMap.isEmpty()) {
            System.out.println("no data in the system");
            return;
        }

        for (Building b : buildingMap.values()) {
            System.out.println("\n🏢 building: " + b.getBuildingName() + " (ID: " + b.getBuildingId() + ")");
            Map<String, abstractRoom> rooms = b.getRooms();
            
            if (rooms.isEmpty()) {
                System.out.println("   (no room in this building))");
            } else {
                for (abstractRoom r : rooms.values()) {
                    System.out.printf("   [room %s] floor %d | type: %-18s | capacity: %d people | volume: %.1f m³ | status: %s\n",
                            r.getRoomID(),
                            r.getFloor(),
                            r.getRoomType(),
                            r.getHowManyPeople(),
                            r.getVolume(),
                            (r.isOccupy() ? "❌ already booked (" + r.getBookTime() + ")" : "✅ free"));
                }
            }
        }
    }
}