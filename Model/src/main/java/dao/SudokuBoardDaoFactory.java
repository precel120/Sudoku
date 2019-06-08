package dao;

import exceptions.JdbcDaoException;
import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class SudokuBoardDaoFactory {

    final static Logger logger = Logger.getLogger(JdbcSudokuBoardDao.class);

    public FileSudokuBoardDao getFileDao(String fileName) {
        FileSudokuBoardDao dao = new FileSudokuBoardDao(fileName);
        return dao;
    }

    public JdbcSudokuBoardDao getJdbcDao(String fileName) {
        ResourceBundle bundle;
        if (Locale.getDefault().toString().equals("pl")) bundle = ResourceBundle.getBundle("langModel_pl");
        else bundle = ResourceBundle.getBundle("langModel");
        JdbcSudokuBoardDao jdbcSudokuBoardDao = null;
        try {
            jdbcSudokuBoardDao = new JdbcSudokuBoardDao(fileName);
        } catch (JdbcDaoException e) {
            logger.error(bundle.getString("jdbc"), e);
        }
        logger.debug(bundle.getString("jdbcFact"));
        return jdbcSudokuBoardDao;
    }

}
