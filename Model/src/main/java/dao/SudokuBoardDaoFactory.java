package dao;

import exceptions.JdbcDaoException;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class SudokuBoardDaoFactory {

    private final static Logger logger = Logger.getLogger(JdbcSudokuBoardDao.class);

    public FileSudokuBoardDao getFileDao(String fileName) {
        FileSudokuBoardDao dao=new FileSudokuBoardDao(fileName);
        return dao;
    }
    public JdbcSudokuBoardDao getJdbcDao(String fileName){
        Locale.setDefault(new Locale("en"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("langModel");
        JdbcSudokuBoardDao jdbcSudokuBoardDao = null;
        try {
            jdbcSudokuBoardDao = new JdbcSudokuBoardDao(fileName);
            logger.debug(resourceBundle.getString("jdbcFact"));
        } catch (JdbcDaoException e) {
            logger.error(resourceBundle.getString("jdbc"));
        }
        return  jdbcSudokuBoardDao;
    }

}
