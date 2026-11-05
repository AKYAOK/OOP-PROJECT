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
            System.out.println("\n==========================================");
            System.out.println("   ระบบจัดการตึกและห้องพัก (OOP & Interface)");
            System.out.println("==========================================");
            System.out.println("1. เพิ่มตึกใหม่เข้าสู่ระบบ");
            System.out.println("2. เพิ่มห้องพักเข้าตึก (เลือกประเภทห้อง)");
            System.out.println("3. ค้นหาห้องว่างในตึก (รายชั้น / รหัสห้อง)");
            System.out.println("4. จองห้องพัก");
            System.out.println("5. แสดงข้อมูลตึกและห้องทั้งหมด");
            System.out.println("0. ออกจากโปรแกรม");
            System.out.print("เลือกรายการ (0-5): ");

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
                    System.out.println("👋 ออกจากโปรแกรมเรียบร้อยแล้ว");
                    break;
                default:
                    System.out.println("❌ ตัวเลือกไม่ถูกต้อง กรุณาเลือกใหม่");
            }
        }
    }

    // ----------------------------------------------------
    // 1. เพิ่มตึกใหม่เข้าสู่ระบบ (Polymorphism)
    // ----------------------------------------------------
    private static void addNewBuilding() {
        System.out.println("\n--- [เพิ่มตึกใหม่] ---");
        System.out.print("ระบุรหัสตึก (เช่น SIT, LX, CB1): ");
        String bId = scanner.nextLine().trim();

        if (buildingMap.containsKey(bId)) {
            System.out.println("❌ รหัสตึกนี้มีในระบบแล้ว!");
            return;
        }

        System.out.print("ระบุชื่อตึก: ");
        String bName = scanner.nextLine().trim();

        System.out.println("เลือกประเภทตึก: 1. SIT Building  2. LX Building");
        System.out.print("เลือก (1-2): ");
        String type = scanner.nextLine().trim();

        Building newBuilding;
        if (type.equals("1")) {
            newBuilding = new SITBuilding(bId, bName);
        } else {
            newBuilding = new LXBuilding(bId, bName);
        }

        buildingMap.put(bId, newBuilding);
        System.out.println("✅ เพิ่มตึก \"" + bName + "\" เข้าสู่ระบบเรียบร้อย!");
    }

    // ----------------------------------------------------
    // 2. เพิ่มห้องเข้าตึก (เลือก Dynamic Subclass ของ Room)
    // ----------------------------------------------------
    private static void addRoomToBuilding() {
        System.out.println("\n--- [เพิ่มห้องพักเข้าตึก] ---");
        if (buildingMap.isEmpty()) {
            System.out.println("❌ ยังไม่มีตึกในระบบ");
            return;
        }

        System.out.print("ป้อนรหัสตึกที่ต้องการเพิ่มห้อง: ");
        String bId = scanner.nextLine().trim();
        Building building = buildingMap.get(bId);

        if (building == null) {
            System.out.println("❌ ไม่พบตึกรหัสนี้ในระบบ");
            return;
        }

        System.out.print("ป้อนรหัสห้อง (เช่น SIT101): ");
        String rId = scanner.nextLine().trim();

        System.out.print("ป้อนชั้นที่อยู่ (เช่น 1, 2, 3): ");
        int floor = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("ป้อนความจุผู้ใช้งานสูงสุด (คน): ");
        int capacity = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("ป้อนความกว้างห้อง (ม.): ");
        double w = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("ป้อนความยาวห้อง (ม.): ");
        double l = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("ป้อนความสูงห้อง (ม.): ");
        double h = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("\nเลือกประเภทห้องที่จะสร้าง:");
        System.out.println("1. Study Room (ห้องอ่านหนังสือ)");
        System.out.println("2. Lab Room (ห้องปฏิบัติการคอมพิวเตอร์)");
        System.out.print("เลือกประเภท (1-2): ");
        String roomTypeChoice = scanner.nextLine().trim();

        Room newRoom;

        if (roomTypeChoice.equals("1")) {
            System.out.print("มีไวท์บอร์ดหรือไม่? (y/n): ");
            boolean hasBoard = scanner.nextLine().trim().equalsIgnoreCase("y");
            // Instantiate StudyRoom
            newRoom = new StudyRoom(rId, floor, capacity, w, l, h, hasBoard);
        } else {
            System.out.print("ป้อนจำนวนเครื่องคอมพิวเตอร์ในห้อง: ");
            int compCount = Integer.parseInt(scanner.nextLine().trim());
            // Instantiate LabRoom
            newRoom = new LabRoom(rId, floor, capacity, w, l, h, compCount);
        }

        // เพิ่ม Object Room (Interface) เข้าตึก
        building.addRoom(newRoom);
        System.out.println("✅ เพิ่มห้อง [" + rId + "] ประเภท " + newRoom.getRoomType() + " เข้าตึก " + building.getBuildingName() + " สำเร็จ!");
    }

    // ----------------------------------------------------
    // 3. ทดสอบเรียกใช้ Overloading Methods getEmptyRoom()
    // ----------------------------------------------------
    private static void searchEmptyRoom() {
        System.out.println("\n--- [ค้นหาห้องว่าง] ---");
        System.out.print("ป้อนรหัสตึก: ");
        String bId = scanner.nextLine().trim();
        Building building = buildingMap.get(bId);

        if (building == null) {
            System.out.println("❌ ไม่พบตึกนี้");
            return;
        }

        System.out.println("รูปแบบการค้นหา:");
        System.out.println("1. ค้นหาห้องว่างตามชั้น (int)");
        System.out.println("2. ค้นหาห้องว่างด้วยรหัสห้อง (String)");
        System.out.print("เลือก (1-2): ");
        String searchChoice = scanner.nextLine().trim();

        if (searchChoice.equals("1")) {
            System.out.print("ระบุเลขชั้น: ");
            int floor = Integer.parseInt(scanner.nextLine().trim());
            
            // Call Overloaded Method: getEmptyRoom(int)
            List<Room> emptyRooms = building.getEmptyRoom(floor);
            System.out.println("\n🔍 ผลการค้นหาห้องว่างบนชั้น " + floor + ":");
            if (emptyRooms.isEmpty()) {
                System.out.println("   ไม่พบห้องว่างบนชั้นนี้");
            } else {
                for (Room r : emptyRooms) {
                    System.out.println("   - ห้อง " + r.getRoomID() + " [" + r.getRoomType() + "] ปริมาตร: " + r.getVolume() + " ลบ.ม.");
                }
            }
        } else if (searchChoice.equals("2")) {
            System.out.print("ระบุรหัสห้อง: ");
            String rId = scanner.nextLine().trim();
            
            // Call Overloaded Method: getEmptyRoom(String)
            Room r = building.getEmptyRoom(rId);
            if (r != null) {
                System.out.println("✅ ห้อง " + r.getRoomID() + " [" + r.getRoomType() + "] ว่างพร้อมใช้งาน!");
            } else {
                System.out.println("❌ ไม่พบห้อง หรือห้องนี้ไม่ว่าง");
            }
        }
    }

    // ----------------------------------------------------
    // 4. จองห้องพัก
    // ----------------------------------------------------
    private static void bookRoom() {
        System.out.println("\n--- [จองห้องพัก] ---");
        System.out.print("ป้อนรหัสตึก: ");
        String bId = scanner.nextLine().trim();
        Building building = buildingMap.get(bId);

        if (building == null) {
            System.out.println("❌ ไม่พบตึกนี้");
            return;
        }

        System.out.print("ป้อนรหัสห้องที่ต้องการจอง: ");
        String rId = scanner.nextLine().trim();
        Room room = building.getRooms().get(rId);

        if (room == null) {
            System.out.println("❌ ไม่พบห้องรหัสนี้");
            return;
        }

        if (room.isOccupy()) {
            System.out.println("⚠️ ห้องนี้ถูกจองไปแล้ว! " + room.getBookTime());
            return;
        }

        System.out.print("ป้อนเวลาเริ่มจอง (เช่น 9 สำหรับ 09:00 น.): ");
        int start = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("ป้อนเวลาสิ้นสุด (เช่น 12 สำหรับ 12:00 น.): ");
        int end = Integer.parseInt(scanner.nextLine().trim());

        boolean success = room.book(start, end);
        if (success) {
            System.out.println("🎉 จองห้อง " + room.getRoomID() + " สำเร็จ!");
            System.out.println("   ระยะเวลาการใช้งาน: " + room.timeThatTheRoomNotEmpty() + " ชั่วโมง");
        } else {
            System.out.println("❌ การจองล้มเหลว");
        }
    }

    // ----------------------------------------------------
    // 5. แสดงข้อมูลตึกและห้องทั้งหมด
    // ----------------------------------------------------
    private static void displayAllBuildings() {
        System.out.println("\n===== รายงานตึกและห้องทั้งหมดในระบบ =====");
        if (buildingMap.isEmpty()) {
            System.out.println("ยังไม่มีข้อมูลในระบบ");
            return;
        }

        for (Building b : buildingMap.values()) {
            System.out.println("\n🏢 ตึก: " + b.getBuildingName() + " (รหัส: " + b.getBuildingId() + ")");
            Map<String, Room> rooms = b.getRooms();
            
            if (rooms.isEmpty()) {
                System.out.println("   (ยังไม่มีห้องในตึกนี้)");
            } else {
                for (Room r : rooms.values()) {
                    System.out.printf("   [ห้อง %s] ชั้น %d | ประเภท: %-18s | ความจุ: %d คน | ปริมาตร: %.1f ลบ.ม. | สถานะ: %s\n",
                            r.getRoomID(),
                            r.getFloor(),
                            r.getRoomType(),
                            r.getHowManyPeople(),
                            r.getVolume(),
                            (r.isOccupy() ? "❌ ถูกจองแล้ว (" + r.getBookTime() + ")" : "✅ ว่าง"));
                }
            }
        }
    }
}