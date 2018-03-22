package dao;

import model.StudentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDao {
    public static boolean insert(StudentModel studentModel){
        boolean status = false;
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String insertSQL = "insert into student_table(student_name,student_country,student_gender,student_hobbies) values(?,?,?,?);";
            try {
                preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1,studentModel.getName());
                preparedStatement.setString(2,studentModel.getCountry());
                preparedStatement.setString(3,studentModel.getGender());
//                String hobbies ="";
//                String[] hobby = studentModel.getHobbies()
//                for (int i = 0; i < hobby.length; i++){
//                    if (i == 0){
//                        hobbies = hobby[0];
//                    }
//                    else{
//                        hobbies + = ", " + hobby[i];
//                    }
//                }
                String hobbyString = String.join(", ",studentModel.getHobbies());   //join using delimiters works only for Java 8 or above
                preparedStatement.setString(4,hobbyString);

                int i = preparedStatement.executeUpdate();
                if (i == 1){
                    System.out.println("One row inserted !!!");
                    status = true;
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

    public static ArrayList<StudentModel> display(){
        ArrayList<StudentModel> arrayList = new ArrayList<>();
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String displaySQL = "select * from student_table;";
            try {
                preparedStatement = connection.prepareStatement(displaySQL);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    int id = resultSet.getInt("student_id");
                    String name = resultSet.getString("student_name");
                    String country = resultSet.getString("student_country");
                    String gender = resultSet.getString("student_gender");
                    String hobbies = resultSet.getString("student_hobbies");

                    arrayList.add(new StudentModel(id,name,country,gender,hobbies));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return arrayList;
    }

    public static ArrayList<StudentModel> getById(int sId){
        ArrayList<StudentModel> arrayList = new ArrayList<>();
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String selectSQL = "select * from student_table where student_id=?";
            try {
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setInt(1,sId);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    int id = resultSet.getInt("student_id");
                    String name = resultSet.getString("student_name");
                    String country = resultSet.getString("student_country");
                    String gender = resultSet.getString("student_gender");
                    String hobbies = resultSet.getString("student_hobbies");

                    arrayList.add(new StudentModel(id,name,country,gender,hobbies));
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

    public static boolean update(StudentModel studentModel){
        boolean status = false;
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String updateSQL = "update student_table set student_name=?,student_country=?,student_gender=?,student_hobbies=? where student_id=?;";
            try {
                preparedStatement = connection.prepareStatement(updateSQL);
                preparedStatement.setString(1,studentModel.getName());
                preparedStatement.setString(2,studentModel.getCountry());
                preparedStatement.setString(3,studentModel.getGender());
                String hobbies = String.join(", ",studentModel.getHobbies());
                preparedStatement.setString(4,hobbies);
                preparedStatement.setInt(5,studentModel.getId());

                int i = preparedStatement.executeUpdate();
                if (i == 1){
                    System.out.println("One row updated !!!");
                    status = true;
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

    public static boolean delete(int id){
        boolean status = false;
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String deleteSQL = "delete from student_table where student_id=?";
            try {
                preparedStatement = connection.prepareStatement(deleteSQL);
                preparedStatement.setInt(1,id);

                int i = preparedStatement.executeUpdate();
                if (i == 1){
                    System.out.println("One row deleted !!!");
                    status = true;
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
}
