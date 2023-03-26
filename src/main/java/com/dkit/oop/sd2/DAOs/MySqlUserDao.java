package com.dkit.oop.sd2.DAOs;

/** OOP Feb 2022
 *
 * Data Access Object (DAO) for User table with MySQL-specific code
 * This 'concrete' class implements the 'UserDaoInterface'.
 *
 * The DAO will contain the SQL query code to interact with the database,
 * so, the code here is specific to a particular database (e.g. MySQL or Oracle etc...)
 * No SQL queries will be used in the Business logic layer of code, thus, it
 * will be independent of the database specifics.
 *
 * The Business Logic layer is only permitted to access the database by calling
 * methods provided in the Data Access Layer - i.e. by callimng the DAO methods.
 *
 */


import com.dkit.oop.sd2.DTOs.User;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySqlUserDao extends MySqlDao implements UserDaoInterface
{

    @Override
    public List<User> findAllUsers() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<User> usersList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM USER";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int userId = resultSet.getInt("USER_ID");
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String lastname = resultSet.getString("LAST_NAME");
                String firstname = resultSet.getString("FIRST_NAME");
                User u = new User(userId, firstname, lastname, username, password);
                usersList.add(u);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllUseresultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return usersList;     // may be empty
    }

    @Override
    public User findUserByUsernamePassword(String user_name, String pass_word) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, pass_word);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                int userId = resultSet.getInt("USER_ID");
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String lastname = resultSet.getString("LAST_NAME");
                String firesultSettname = resultSet.getString("FIRST_NAME");

                user = new User(userId, firesultSettname, lastname, username, password);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
            }
        }
        return user;     // reference to User object, or null value
    }
    public List<User> findAllUsersLastNameContains(String subString) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> usersList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM USER WHERE LAST_NAME LIKE '%' ? '%'";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,subString);


            resultSet = preparedStatement.executeQuery();
            //Using a PreparedStatement to execute SQL...

            while (resultSet.next())
            {
                int userId = resultSet.getInt("USER_ID");
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String lastname = resultSet.getString("LAST_NAME");
                String firstname = resultSet.getString("FIRST_NAME");
                User u = new User(userId, firstname, lastname, username, password);
                usersList.add(u);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllUsers() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return usersList;     // may be empty
    }
    public User addUser(User user) throws DaoException{
        Connection connection = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet resultSet = null;
       // List<User> usersList = new ArrayList<>();
        User u = null;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query1 = "INSERT INTO USER (USERNAME,PASSWORD,LAST_NAME,FIRST_NAME) VALUES (?,?,?,?)";
            String query2="SELECT * FROM USER WHERE USERNAME=?";
            ps1 = connection.prepareStatement(query1);
            ps2 = connection.prepareStatement(query2);
            ps1.setString(1, user.getUsername());
            ps1.setString(2, user.getPassword());
            ps1.setString(3, user.getLastName());
            ps1.setString(4, user.getFirstName());
            ps2.setString(1, user.getUsername());
            //Using a PreparedStatement to execute SQL...
            ps1.executeUpdate();
            resultSet = ps2.executeQuery();
            if (resultSet.next())
            {
                //int userId = resultSet.getInt("USER_ID");
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String lastname = resultSet.getString("LAST_NAME");
                String firstname = resultSet.getString("FIRST_NAME");
                u = new User(firstname, lastname, username, password);

            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllUseresultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps1 != null && ps2 !=null)
                {
                    ps1.close();
                    ps2.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return u;
    }
    public User updatePassword(String usernamen,String passwordn) throws DaoException{
        Connection connection = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet resultSet = null;
        // List<User> usersList = new ArrayList<>();
        User u = null;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query1 = "UPDATE USER  SET PASSWORD=? WHERE USERNAME=?;";
            String query2="SELECT * FROM USER WHERE USERNAME=?";
            ps1 = connection.prepareStatement(query1);
            ps2 = connection.prepareStatement(query2);
            ps1.setString(1,passwordn);
            ps1.setString(2,usernamen);
            //Using a PreparedStatement to execute SQL...
            ps1.executeUpdate();
            resultSet = ps2.executeQuery();
            if (resultSet.next())
            {
                //int userId = resultSet.getInt("USER_ID");
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String lastname = resultSet.getString("LAST_NAME");
                String firstname = resultSet.getString("FIRST_NAME");
                u = new User(firstname, lastname, username, password);

            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllUseresultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps1 != null && ps2 !=null)
                {
                    ps1.close();
                    ps2.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return u;
    }
}

