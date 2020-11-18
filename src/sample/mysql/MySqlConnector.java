package sample.mysql;

import sample.model.Product;
import sample.model.ShoppingList;
import sample.model.Task;
import sample.model.User;

import java.sql.*;

public class MySqlConnector {

    private final String url = "jdbc:mysql://localhost:3306/machinetodo";
    private final String user = "root";
    private final String password = "";
    private final String dataBase = "machinetodo";

    Connection connection;

    public Connection connect() throws ClassNotFoundException, SQLException {

        connection = DriverManager.getConnection(url, user, password);
        Class.forName("com.mysql.jdbc.Driver");

        return connection;
    }

    public void signUpUser(User user) {

        try {
            Statement statement = connection.createStatement();

            String insert = "INSERT INTO " + ConstDataBase.usersTable +
                    "(" + ConstDataBase.usersName + ", " + ConstDataBase.usersSurname + ", " +
                    ConstDataBase.usersLogin + ", " + ConstDataBase.usersPassword +
                    ") VALUES ('" + user.getFirstName() + "', '"
                    + user.getLastName() + "', '"
                    + user.getLogin() + "', '"
                    + user.getPassword() + "');";
            statement.execute(insert);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet loginInUser(User user) throws SQLException {
        ResultSet resultSet = null;
        Statement statement = connection.createStatement();

        if (!user.getLogin().equals("") && !user.getPassword().equals("")) {
            String query = "SELECT * FROM " + ConstDataBase.usersTable
                    + " WHERE " + ConstDataBase.usersLogin + " = '" + user.getLogin()
                    + "' AND " + ConstDataBase.usersPassword + " = '" + user.getPassword() + "';";
            resultSet = statement.executeQuery(query);
        } else {
            System.out.println("Missing login or password.");
        }
        return resultSet;
    }

    public ResultSet loginIdCheck(User user) throws SQLException {
        ResultSet resultSet = null;
        Statement statement = connection.createStatement();

        String query = "SELECT " + ConstDataBase.tasksUserId + " FROM "
                + ConstDataBase.tasksTable
                + " WHERE " + ConstDataBase.usersLogin + " = '" + user.getLogin()
                + "' AND " + ConstDataBase.usersPassword + " = '" + user.getPassword() + "';";
        resultSet = statement.executeQuery(query);

        return resultSet;
    }

    public void insertTask(Task task) {
        try {
            Statement statement = connection.createStatement();

            String insert = "INSERT INTO " + ConstDataBase.tasksTable +
                    " ("
                    + ConstDataBase.tasksUserId + ", "
                    + ConstDataBase.tasksName + ", "
                    + ConstDataBase.tasksDesc +
                    ") VALUES ("
                    + task.getUserId() + ", '"
                    + task.getTaskName() + "', '"
                    + task.getTaskDesc() + "');";
            statement.execute(insert);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertProducts(Product product) {
        try {
            Statement statement = connection.createStatement();

            String insert = "INSERT INTO " + ConstDataBase.productsTable +
                    " ("
                    + ConstDataBase.productUserID + ", "
                    + ConstDataBase.productName + ", "
                    + ConstDataBase.productQuantity +
                    ") VALUES ("
                    + product.getUser_id() + ", '"
                    + product.getName() + "', '"
                    + product.getQuantity() + "');";
            statement.execute(insert);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertIntoShoppingList(ShoppingList list) {
        try {
            Statement statement = connection.createStatement();

            String insert = "INSERT INTO " + ConstDataBase.shoppingListTable +
                    " ("
                    + ConstDataBase.shoppingUserID + ", "
                    + ConstDataBase.shoppingName + ", "
                    + ConstDataBase.shoppingQuantity +
                    ") VALUES ("
                    + list.getUserId() + ", '"
                    + list.getName() + "', '"
                    + list.getQuantity() + "');";
            statement.execute(insert);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateTask(Timestamp timestamp, String name, String desc, int id) throws SQLException {
        Statement statement = connection.createStatement();

        String query = "UPDATE " + ConstDataBase.tasksTable + " SET " +
                ConstDataBase.tasksName + "='" + name + "', " +
                ConstDataBase.tasksDesc + "='" + desc + "' WHERE " +
                ConstDataBase.tasksTaskId + "=" + id +";";
        statement.execute(query);

    }

    public int getAllTasks(int userId) throws SQLException {
        Statement statement = connection.createStatement();

        String query = "SELECT COUNT(*) FROM " + ConstDataBase.tasksTable +
                " WHERE " + ConstDataBase.tasksUserId +
                "=" + userId + ";";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return resultSet.getInt(1);
    }

    public ResultSet getTasksByUser(int userId) throws SQLException {
        ResultSet resultTasks = null;
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM " + ConstDataBase.tasksTable
                + " WHERE " + ConstDataBase.usersId + " = " + userId + ";";
        resultTasks = statement.executeQuery(query);

        return resultTasks;
    }

    public ResultSet getProductsByUser(int userId) throws SQLException {
        ResultSet resultTasks = null;
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM " + ConstDataBase.productsTable
                + " WHERE " + ConstDataBase.productUserID + " = " + userId + ";";
        resultTasks = statement.executeQuery(query);

        return resultTasks;
    }

    public ResultSet getShoppingByUser(int userId) throws SQLException {
        ResultSet resultTasks = null;
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM " + ConstDataBase.shoppingListTable
                + " WHERE " + ConstDataBase.shoppingUserID + " = " + userId + ";";
        resultTasks = statement.executeQuery(query);

        return resultTasks;
    }

    public void removeTask(int userId, int taskId) throws SQLException {
        Statement statement = connection.createStatement();

        String query = "DELETE FROM " + ConstDataBase.tasksTable +
                " WHERE " + ConstDataBase.usersId + " = " + userId +
                " AND " + ConstDataBase.tasksTaskId + " = " +  + taskId +
                ";";
        statement.execute(query);

    }

    public void disconnect() throws SQLException {
        Statement statement = connection.createStatement();
        statement.close();
    }
}