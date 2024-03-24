package form;

import java.sql.*;

@RestController
@RequestMapping("http://localhost:8080/api/submit-form")
public class Form {

    @PostMapping("/submit-form")
    public String submitForm(@RequestBody User user) throws Exception{
        try {
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/your_database", "username", "password");
            
            String sql = "INSERT INTO users (name, email, message) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
           
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getMessage());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "Form submitted successfully!";
            } else {
                return "Failed to submit form!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Server error";
        }
    }
    public static void main(String[]args) throws Exception{
    	submitForm();
    }
}