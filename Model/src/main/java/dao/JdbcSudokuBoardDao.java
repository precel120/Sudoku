package dao;

import exceptions.JdbcDaoException;
import org.apache.log4j.Logger;
import zad2.SudokuBoard;

import java.io.IOException;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>,AutoCloseable{
    private static final String JDBC_URL = "jdbc:derby:Sudoku;create=true";
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private final static Logger logger = Logger.getLogger(JdbcSudokuBoardDao.class);
    private Statement statement;
    private String fileName;
    private Connection connection;

    public JdbcSudokuBoardDao(String fileName) throws JdbcDaoException {
        Locale.setDefault(new Locale("en"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("langModel");
        this.fileName=fileName;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(JDBC_URL);
            logger.debug(resourceBundle.getString("connection"));
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(resourceBundle.getString("notConn"));
            throw new JdbcDaoException("error",e);
        }
    }

    public void write(SudokuBoard sudokuBoard){
        Locale.setDefault(new Locale("en"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("langModel");
        PreparedStatement preparedStatement;
        try {
            statement=connection.createStatement();
            statement.execute("CREATE TABLE SudokuBoards(name varchar(25) PRIMARY KEY, fields varchar(81))");
            logger.debug(resourceBundle.getString("createTab"));
        } catch (SQLException e) {
            logger.error(resourceBundle.getString("notCreated"));
        }
        try {
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("UPDATE SudokuBoards SET fields =? WHERE name=?");
            preparedStatement.setString(1,sudokuBoard.stringOfFields());
            preparedStatement.setString(2,fileName);
            preparedStatement.executeUpdate();
            logger.debug(resourceBundle.getString("writeDat"));
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(resourceBundle.getString("sqlError"));
        }
        try {
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("INSERT INTO SudokuBoards values(?, ?)");
            preparedStatement.setString(1,fileName);
            preparedStatement.setString(2,sudokuBoard.stringOfFields());
            preparedStatement.executeUpdate();
            logger.debug(resourceBundle.getString("writeDat"));
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(resourceBundle.getString("sqlError"));
        }
    }

    public SudokuBoard read(){
        Locale.setDefault(new Locale("en"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("langModel");
        PreparedStatement preparedStatement;
        SudokuBoard sudokuBoard = new SudokuBoard();
        String fields;
        ResultSet resultSet;
        int[] tab = new int[81];
        try {
            statement = connection.createStatement();
            logger.debug(resourceBundle.getString("readDat"));
            preparedStatement = connection.prepareStatement("SELECT SudokuBoards.name, SudokuBoards.fields from SudokuBoards where SudokuBoards.name=?");
            preparedStatement.setString(1, fileName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fields = resultSet.getString(2);
            } else {
                logger.error(resourceBundle.getString("ioe"));
                throw new IOException(resourceBundle.getString("ioe"));
            }
            for (int i = 0; i < 81; i++) {
                tab[i] = (Character.getNumericValue(fields.charAt(i)));
            }
        } catch (SQLException |IOException e) {
            logger.error(resourceBundle.getString("ioe"));
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, tab[i * 9 + j]);
            }
        }
        return sudokuBoard;
    }

    public void close(){
        Locale.setDefault(new Locale("en"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("langModel");
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error(resourceBundle.getString("sqlError"));
        }
    }

    @Override
    public void finalize() throws Throwable{
        super.finalize();
    }
}
