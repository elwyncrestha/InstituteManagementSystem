package dao;

import model.CourseModel;
import model.ExamModel;
import model.SubjectModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamDao {
    public static boolean insert(ExamModel examModel){
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;
        boolean status = false;

        if (connection != null){
            String insertQuery = "insert into exam_table(subject_code,question_name,question_answer1,question_answer2,question_answer3,question_answer4,question_correct_answer)" +
                    " values(?,?,?,?,?,?,?)";
            try {
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1,examModel.getSubject());
                preparedStatement.setString(2,examModel.getQuestionName());
                preparedStatement.setString(3,examModel.getAnswer1());
                preparedStatement.setString(4,examModel.getAnswer2());
                preparedStatement.setString(5,examModel.getAnswer3());
                preparedStatement.setString(6,examModel.getAnswer4());
                preparedStatement.setString(7,examModel.getCorrectAnswer());
                System.out.println(examModel.getCorrectAnswer());
                int i = preparedStatement.executeUpdate();
                if (i == 1){
                    System.out.println("One row inserted"); //only to check
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

    public static ArrayList<SubjectModel> getAllSubjects(){
        ArrayList<SubjectModel> arrayList = new ArrayList<>();
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String selectSQL = "select * from subject_table";
            try {
                preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    String subjectCode = resultSet.getString("subject_code");
                    String subjectName = resultSet.getString("subject_name");

                    arrayList.add(new SubjectModel(subjectCode,subjectName));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally{
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

    public static ArrayList<ExamModel> displayQuestion(String subjectCode){
        ArrayList<ExamModel> arrayList = new ArrayList<>();
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String selectSQL = "select * from exam_table where subject_code='"+subjectCode+"'";
            try {
                preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    String subject = resultSet.getString("subject_code");
                    String qName = resultSet.getString("question_name");
                    String qAns1 = resultSet.getString("question_answer1");
                    String qAns2 = resultSet.getString("question_answer2");
                    String qAns3 = resultSet.getString("question_answer3");
                    String qAns4 = resultSet.getString("question_answer4");
                    String correctAns = resultSet.getString("question_correct_answer");

                    arrayList.add(new ExamModel(subject,qName,qAns1,qAns2,qAns3,qAns4,correctAns));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally{
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

    public static boolean insertSubject(String subjectCode, String subjectName){
        boolean status = false;
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String insertSQL = "insert into subject_table values(?,?);";
            try {
                preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1,subjectCode);
                preparedStatement.setString(2,subjectName);

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

    public static boolean deleteSubject(String subjectCode){
        boolean status = false;
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String deleteSQL = "delete from subject_table where subject_code=?;";
            try {
                preparedStatement = connection.prepareStatement(deleteSQL);
                preparedStatement.setString(1,subjectCode);

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
