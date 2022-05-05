import java.sql.*;

public class JdbcSelectTest {
    public static void main(String[] args) {
     try (
         //Bước 1: Phân bổ đối tượng 'Kết nối' 'Connection' cơ sở dữ liệu

         Connection cv = DriverManager.getConnection(
                 //DriverManager : trình  điều khiển cơ sở dữ liệu
                 //getConnection :  thiết lập kết nối đến cái bên dưới

                 "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                 "root", ""); // chỉ dành cho mySQL
         //Định dạng là: "jdbc: mysql: // hostname: port / databaseName", "tên người dùng", "mật khẩu"
         //Bước 2: Phân bổ đối tượng 'Tuyên bố' 'Statement'  trong Kết nối

         Statement stmt = cv.createStatement();
         // Statement đối tượng tạo câu lệnh truy vấn
     ) {
         //Bước 3: Thực hiện truy vấn SQL SELECT. Kết quả truy vấn được trả về trong đối tượng 'ResultSet'.
         String strSelect = "select title , price, qty from books"; // tạo 1 đối tượng
         System.out.println("The SQL statement is:" + strSelect+ "\n"); //Echo để gỡ lỗi

         ResultSet rset = stmt.executeQuery(strSelect);
         //  rset lưu lại kết quả truy vấn
         //executeQuery gọi đến phương thức thực thi truy vấn
         //// Bước 4: Xử lý ResultSet bằng cách cuộn con trỏ về phía trước qua next ().;
         //Đối với mỗi hàng, truy xuất nội dung của các ô bằng getXxx (columnName).


         System.out.println("The records selected are:");
         int rowCount = 0;
         while (rset.next()){
             // Di chuyển con trỏ đến hàng tiếp theo, trả về false nếu không còn hàng
             String title = rset.getString("title");
             double price = rset.getDouble("price");
             int qty = rset.getInt("qty");
             System.out.println(title + "," + price + ","+ qty);
             ++rowCount;
         }
         System.out.println("Total number of records = " + rowCount);

     } catch (SQLException e) {
         e.printStackTrace();
     }// Bước 5: Đóng cv và stmt - Được thực hiện tự động bằng thử tài nguyên (JDK 7)
    }
     }


