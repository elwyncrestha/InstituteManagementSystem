package dao;

import model.SubscriptionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubscriptionDao {
    public static boolean insert(SubscriptionModel subscriptionModel){
        boolean status = false;
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String insertSQL = "insert into subscription_table(subscription_name,subscription_email) values(?,?);";
            try {
                preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1,subscriptionModel.getSubscriberName());
                preparedStatement.setString(2,subscriptionModel.getSubscriberEmail());

                int i = preparedStatement.executeUpdate();
                if (i == 1){
                    status = true;
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
        return status;
    }

    public static ArrayList<SubscriptionModel> displaySubcription(){
        ArrayList<SubscriptionModel> arrayList = new ArrayList<>();
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String displaySQL = "select * from subscription_table;";
            try {
                preparedStatement = connection.prepareStatement(displaySQL);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    String name = resultSet.getString(1);
                    String email = resultSet.getString(2);

                    arrayList.add(new SubscriptionModel(name,email));
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

    public static boolean delete(String subscriberEmail){
        boolean status = false;
        Connection connection = MyConnection.connect();
        PreparedStatement preparedStatement = null;

        if (connection != null){
            String deleteSQL = "delete from subscription_table where subscription_email=?;";
            try {
                preparedStatement = connection.prepareStatement(deleteSQL);
                preparedStatement.setString(1,subscriberEmail);

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
