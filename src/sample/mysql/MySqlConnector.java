package sample.mysql;

import sample.model.Product;
import sample.model.ShoppingList;
import sample.model.Task;
import sample.model.User;

import java.sql.*;

public class MySqlConnector {

    private final String url = "jdbc:mysql://localhost:3306/machinetodo1";
    private final String user = "root";
    private final String password = "";
    private final String dataBase = "machinetodo1";

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

    public void creatingDB() throws SQLException {
        setDataBase();
        useDB();
        createTableUsers();
        createTableTasks();
        createTableProducts();
        createTableShoppingList();
        createUsersTasks();
        createUsersProducts();
        createUsersShoppings();
    }

    public void setDataBase() throws SQLException {
        Statement statement = connection.createStatement();

        String databaseCreation = "CREATE DATABASE IF NOT EXISTS machinetodo;";
        statement.execute(databaseCreation);
    }

    public void useDB() throws SQLException {
        Statement statement = connection.createStatement();
        String database = "USE machinetodo1";
        statement.execute(database);

    }

    public void createTableUsers() throws SQLException {
        Statement statement = connection.createStatement();
        String database = "CREATE TABLE IF NOT EXISTS users (\n" +
                "\tuser_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "\tname VARCHAR(100) NOT NULL DEFAULT 'None',\n" +
                "\tsurname VARCHAR(100) NOT NULL DEFAULT 'None',\n" +
                "    login VARCHAR(100) NOT NULL,\n" +
                "    password VARCHAR(100) NOT NULL\n" +
                ");";
        statement.execute(database);
    }

    public void createTableTasks() throws SQLException {
        Statement statement = connection.createStatement();
        String database = "CREATE TABLE IF NOT EXISTS tasks (\n" +
                "\ttask_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "    user_id INT NOT NULL,\n" +
                "\ttask_name VARCHAR(100) NOT NULL DEFAULT 'None',\n" +
                "\ttask_desc VARCHAR(100) NOT NULL DEFAULT 'None',\n" +
                "\ttask_date TIMESTAMP DEFAULT NOW(),\n" +
                "\tFOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE\n" +
                ");";
        statement.execute(database);
    }

    public void createTableProducts() throws SQLException {
        Statement statement = connection.createStatement();
        String database = "CREATE TABLE IF NOT EXISTS products (\n" +
                "\tproduct_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "\tname VARCHAR(100) NOT NULL DEFAULT 'None',\n" +
                "\tquantity VARCHAR(100) NOT NULL DEFAULT 'None',\n" +
                "\tuser_id INT NOT NULL,\n" +
                "\tFOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE\n" +
                ");";
        statement.execute(database);
    }

    public void createTableShoppingList() throws SQLException {
        Statement statement = connection.createStatement();
        String database = "CREATE TABLE IF NOT EXISTS shopping_list (\n" +
                "\tproduct_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "\tname VARCHAR(100) NOT NULL DEFAULT 'None',\n" +
                "\tquantity VARCHAR(100) NOT NULL DEFAULT 'None',\n" +
                "\tuser_id INT NOT NULL,\n" +
                "\tFOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE\n" +
                ");";
        statement.execute(database);
    }

    public void createUsersTasks() throws SQLException {
        Statement statement = connection.createStatement();
        String database = "CREATE TABLE IF NOT EXISTS users_tasks (\n" +
                "    user_task_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "    user_id INT NOT NULL,\n" +
                "    task_id INT NOT NULL,\n" +
                "    FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE,\n" +
                "    FOREIGN KEY(task_id) REFERENCES tasks(task_id) ON DELETE CASCADE\n" +
                ");";
        statement.execute(database);
    }

    public void createUsersProducts() throws SQLException {
        Statement statement = connection.createStatement();
        String database = "CREATE TABLE IF NOT EXISTS users_products (\n" +
                "    user_product_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "    user_id INT NOT NULL,\n" +
                "    product_id INT NOT NULL,\n" +
                "    FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE,\n" +
                "    FOREIGN KEY(product_id) REFERENCES products(product_id) ON DELETE CASCADE\n" +
                ");";
        statement.execute(database);
    }

    public void createUsersShoppings() throws SQLException {
        Statement statement = connection.createStatement();
        String database = "CREATE TABLE IF NOT EXISTS users_shopping (\n" +
                "    user_shopping_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "    user_id INT NOT NULL,\n" +
                "    product_id INT NOT NULL,\n" +
                "    FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE,\n" +
                "    FOREIGN KEY(product_id) REFERENCES shopping_list(product_id) ON DELETE CASCADE\n" +
                ");";
        statement.execute(database);
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
                ConstDataBase.tasksTaskId + "=" + id + ";";
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
                " AND " + ConstDataBase.tasksTaskId + " = " + +taskId +
                ";";
        statement.execute(query);

    }

    public void removeProduct(int userId, int productId) throws SQLException {

        Statement statement = connection.createStatement();

        String query = "DELETE FROM " + ConstDataBase.productsTable +
                " WHERE " + ConstDataBase.productUserID + " = " + userId +
                " AND " + ConstDataBase.productId + " = " + productId +
                ";";

        statement.execute(query);
    }

    public void removeShoppingListItem(int userId, int itemId) throws SQLException {

        Statement statement = connection.createStatement();

        String query = "DELETE FROM " + ConstDataBase.shoppingListTable +
                " WHERE " + ConstDataBase.shoppingUserID + " = " + userId +
                " AND " + ConstDataBase.shoppingProductID + " = " + itemId +
                ";";

        statement.execute(query);
    }

    public void disconnect() throws SQLException {
        Statement statement = connection.createStatement();
        statement.close();
    }
}