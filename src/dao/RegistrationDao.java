package dao;

import model.RegistrationModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrationDao {
    public static boolean insert(RegistrationModel registrationModel){
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String insertSQL = "insert into login_table(first_name,last_name,email,user_username,user_password)" +
                    "values(?,?,?,?,?);";
            try {
                preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1,registrationModel.getFirstName());
                preparedStatement.setString(2,registrationModel.getLastName());
                preparedStatement.setString(3,registrationModel.getEmail());
                preparedStatement.setString(4,registrationModel.getUsername());
                preparedStatement.setString(5,registrationModel.getPassword());

                int i = preparedStatement.executeUpdate();
                if (i == 1){
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean validateUser(String username, String password){
        boolean status = false;
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String selectSQL = "select user_username, user_password from login_table;";
            try {
                preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    String uName = resultSet.getString("user_username");
                    String uPwd = resultSet.getString("user_password");

                    if ((uName.equals(username)) && (uPwd.equals(password))){
                        status = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }

    public static ArrayList<RegistrationModel> displayRegUser(){
        ArrayList<RegistrationModel> arrayList = new ArrayList<>();
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String displaySQL = "select * from login_table;";
            try {
                preparedStatement = connection.prepareStatement(displaySQL);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    String fName = resultSet.getString("first_name");
                    String lName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    String username = resultSet.getString("user_username");
                    String password = null;
                    arrayList.add(new RegistrationModel(fName,lName,email,username,password));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public static boolean deleteUser(String email){
        boolean status = false;
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String deleteSQL = "delete from login_table where email=?;";
            try {
                preparedStatement = connection.prepareStatement(deleteSQL);
                preparedStatement.setString(1,email);
                int i = preparedStatement.executeUpdate();
                if (i == 1){
                    status = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }
}
