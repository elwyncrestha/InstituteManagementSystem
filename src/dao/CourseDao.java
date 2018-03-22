package dao;

import model.CourseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDao {
    public static boolean insert(CourseModel courseModel){
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;
        boolean status = false;

        if (connection != null) {

            String insertQuery = "insert into course_table(course_title,course_price,course_duration) values(?,?,?);";

            try {
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, courseModel.getTitle());
                preparedStatement.setFloat(2, courseModel.getPrice());
                preparedStatement.setString(3, courseModel.getDuration());

                int i = preparedStatement.executeUpdate();
                if (i != 0) {
                    System.out.println("One row inserted !!!");
                    status = true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
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

    public static ArrayList<CourseModel> selectAll(){
        ArrayList<CourseModel> arrayList = new ArrayList<>();
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String selectSQL = "select * from course_table;";

            try{
                preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    arrayList.add(new CourseModel(resultSet.getInt("course_id"),
                            resultSet.getString("course_title"),
                            resultSet.getFloat("course_price"),
                            resultSet.getString("course_duration")));
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

    public static ArrayList<CourseModel> getById(int id){
        ArrayList<CourseModel> arrayList = new ArrayList<>();
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null) {

            String selectSQL = "select * from course_table where course_id=?";

            try {
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int cId = resultSet.getInt("course_id");
                    String cTitle = resultSet.getString("course_title");
                    float cPrice = resultSet.getFloat("course_price");
                    String cDuration = resultSet.getString("course_duration");

                    arrayList.add(new CourseModel(cId, cTitle, cPrice, cDuration));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
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

    public static boolean update(CourseModel courseModel){
        boolean status = false;
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null) {

            String updateSQL = "update course_table set course_title=?,course_price=?,course_duration=? where course_id=?";

            try {
                preparedStatement = connection.prepareStatement(updateSQL);
                preparedStatement.setString(1, courseModel.getTitle());
                preparedStatement.setFloat(2, courseModel.getPrice());
                preparedStatement.setString(3, courseModel.getDuration());
                preparedStatement.setInt(4, courseModel.getId());

                int i = preparedStatement.executeUpdate();
                if (i == 1) {
                    status = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
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
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;
        boolean status = false;

        if (connection != null){
            String deleteSQL = "delete from course_table where course_id=?";

            try {
                preparedStatement = connection.prepareStatement(deleteSQL);
                preparedStatement.setInt(1,id);

                int i = preparedStatement.executeUpdate();
                if (i == 1){
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
