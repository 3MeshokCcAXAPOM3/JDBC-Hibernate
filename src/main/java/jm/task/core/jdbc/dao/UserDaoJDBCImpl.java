package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    private final Connection connection = Util.getConnection();

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS learning_table (id SERIAL PRIMARY KEY, name VARCHAR(100), lastname VARCHAR(100), age SMALLINT)";
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
        }catch(SQLException e){e.printStackTrace();}
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS learning_table";
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
        }catch(SQLException e){e.printStackTrace();}
    }

    public void saveUser(String name, String lastName, byte age) {
    String sql = "INSERT INTO learning_table (name, lastname, age) VALUES (?,?,?)";
    try(PreparedStatement pStatement = connection.prepareStatement(sql)){
        pStatement.setString(1, name);
        pStatement.setString(2, lastName);
        pStatement.setByte(3, age);
        pStatement.executeUpdate();
    }catch (SQLException e){e.printStackTrace();}

    }

    public void removeUserById(long id) {
    String sql = "DELETE FROM learning_table WHERE id = ?";
    try(PreparedStatement pStatement = connection.prepareStatement(sql)){
        pStatement.setLong(1,id);
        pStatement.executeUpdate();
    }catch (SQLException e){e.printStackTrace();}
    }


     public List<User> getAllUsers() {
            List<User> userList = new ArrayList<>();
         String sql = "SELECT * FROM learning_table";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    userList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return userList;
        }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE learning_table";
        try(Statement statement = connection.createStatement()){
            statement.execute(sql);
        }catch (SQLException e){e.printStackTrace();}

    }
    public void chandgeType(){
        String sql = "ALTER TABLE learning_table ALTER COLUMN age TYPE INT";
        try(Statement statement = connection.createStatement()){
            statement.execute(sql);
        }catch (SQLException e){e.printStackTrace();}
    }
}
