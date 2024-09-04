## Xây dựng dự án ( ý tưởng lấy từ chatGPT )
- Model (Mô hình): Các lớp biểu diễn dữ liệu như Customer, Room, Booking, v.v.
- View (Giao diện): Giao diện người dùng được thiết kế bằng JavaFX hoặc FXML.
- Controller (Điều khiển): Lớp xử lý logic của ứng dụng, tương tác với giao diện người dùng và mô hình.
## từng mã khởi đầu 
1) lớp kahsch hàng
   
   public class Customer {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
 }
2) lớp phòng
   
   public class Room {
    private int roomNumber;
    private String type;
    private double price;
    private boolean available;
 }
3) lớp đặt phòng
   
   public class Booking {
    private int bookingId;
    private Customer customer;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
 }
4) điều khiển
   
   public class HotelController {
    @FXML
    private TableView<Room> roomTable;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private DatePicker checkInDate;
    @FXML
    private DatePicker checkOutDate;
 }
5) main application
   
   public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hotel_management.fxml"));
        primaryStage.setTitle("Hotel Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
 }

