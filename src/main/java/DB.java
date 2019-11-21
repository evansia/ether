import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private Connection conn;

    DB() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/diabolo-db",
                            "postgres", "password");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    List<Upload> fetchAllUploads() {
        List<Upload> allUploads = new ArrayList<Upload>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, name FROM uploads;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Upload upload = new Upload(id, name);
                allUploads.add(upload);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return allUploads;
    }

    public boolean insert(String filename, String filepath) {
        File file = new File(filepath);

        try (FileInputStream fis = new FileInputStream(file)) {
            String query = "INSERT INTO uploads(name,data) VALUES(?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, filename);
            pst.setBinaryStream(2, fis, (int) file.length());
            pst.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
        return true;
    }
//    public boolean insert() {
//        File file = new File("src/main/java/dummy.txt");
//
//        try (FileInputStream fis = new FileInputStream(file)) {
//            String query = "INSERT INTO uploads(name,data) VALUES(?,?)";
//            PreparedStatement pst = conn.prepareStatement(query);
//            pst.setString(1, "dummyOnly");
//            pst.setBinaryStream(2, fis, (int) file.length());
//            pst.executeUpdate();
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//            System.err.println(e.getClass().getName()+": "+e.getMessage());
//            return false;
//        }
//        return true;
//    }
}
