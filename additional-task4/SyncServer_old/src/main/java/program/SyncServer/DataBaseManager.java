package program.SyncServer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private Connection connection;

    public void connect() {
        String port = "8081";
        String host = "0.0.0.0";
        String database = "postgres";
        String user = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://%s:%s/%s".formatted(host, port, database);

        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertFile(String path, byte[] content) throws SQLException {
        String sql = "INSERT INTO files (path, content) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, path);
            preparedStatement.setBytes(2, content);

            preparedStatement.executeUpdate();
        }
    }

    public byte[] getContentByPath(String path) throws SQLException {
        String sql = "SELECT * FROM files WHERE path = '%s';".formatted(path);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return resultSet.getBytes("content");
            }
        }
        return null;
    }

    public void updateContentByPath(String path, byte[] content) throws SQLException {
        String sql = "UPDATE files SET content = ? WHERE path = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBytes(1, content);
            preparedStatement.setString(2, path);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteFile(String path) throws SQLException {
        String sql = "DELTE FROM files WHERE path = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, path);

            preparedStatement.executeUpdate();
        }
    }

    public void insertDirectory(String path) throws SQLException {
        String sql = "INSERT INTO directories (path) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, path);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteDirectory(String path) throws SQLException {
        String sql = "DELETE FROM directories WHERE path = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, path);

            preparedStatement.executeUpdate();
        }
    }

    public List<String> getListOfRootDirectories() throws SQLException {
        List<String> listOfRootDirectories = new ArrayList<>();

        String sql = "SELECT * FROM root_directories";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                listOfRootDirectories.add(resultSet.getString(2));
            }
        }
        return listOfRootDirectories;
    }

    public void deleteRootDirectory(String path) throws SQLException {
        String sql = "DELETE FROM roo_directories WHERE path = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, path);

            preparedStatement.executeUpdate();
        }
    }

    public void addRootDirectory(String path) throws SQLException {
        String sql = "INSERT INTO root_directory (path) VALUES ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, path);

            preparedStatement.executeUpdate();
        }
    }
}
