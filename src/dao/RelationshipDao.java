package dao;

import model.StudentCourseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RelationshipDao {
    public static boolean insert(StudentCourseModel studentCourseModel){
        boolean status = false;
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String insertSQL = "insert into student_course(student_id,course_id) values(?,?);";
            try {
                preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setInt(1,studentCourseModel.getStudentId());
                preparedStatement.setInt(2,studentCourseModel.getCourseId());

                int i = preparedStatement.executeUpdate();
                if (i == 1){
                    System.out.println("One row inserted");
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

    public static ArrayList<StudentCourseModel> display(){
        ArrayList<StudentCourseModel> arrayList = new ArrayList<>();
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String selectSQL = "select sc.student_course_id, st.student_id, st.student_name, ct.course_id, ct.course_title from " +
                    "student_table st, course_table ct, student_course sc where " +
                    "st.student_id=sc.student_id and ct.course_id=sc.course_id;";
            try {
                preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    int tableId = resultSet.getInt("student_course_id");
                    int sId = resultSet.getInt("student_id");
                    int cId = resultSet.getInt("course_id");
                    String sName = resultSet.getString("student_name");
                    String cName = resultSet.getString("course_title");

                    arrayList.add(new StudentCourseModel(sId,cId,tableId,sName,cName));
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

    public static boolean delete(int tableId){
        boolean status = false;
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String deleteSQL = "delete from student_course where student_course_id=?";
            try {
                preparedStatement = connection.prepareStatement(deleteSQL);
                preparedStatement.setInt(1,tableId);

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
