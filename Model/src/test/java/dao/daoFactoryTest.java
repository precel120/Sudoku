package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class daoFactoryTest {

    @Test
    void fileDaoTest() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        FileSudokuBoardDao dao1 = factory.getFileDao("1.bin");
        FileSudokuBoardDao dao2 = factory.getFileDao("2.bin");
        FileSudokuBoardDao dao3 = factory.getFileDao("3.bin");
        Assertions.assertNotNull(dao1);
        Assertions.assertEquals(dao1.getFileName(), "1.bin");
        Assertions.assertNotNull(dao2);
        Assertions.assertEquals(dao2.getFileName(), "2.bin");
        Assertions.assertNotNull(dao3);
        Assertions.assertEquals(dao3.getFileName(), "3.bin");
    }

    @Test
    void jdbcDaoTest() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        JdbcSudokuBoardDao dao1 = factory.getJdbcDao("jdbcTestFile1");
        JdbcSudokuBoardDao dao2 = factory.getJdbcDao("jdbcTestFile2");
        JdbcSudokuBoardDao dao3 = factory.getJdbcDao("jdbcTestFile3");
        Assertions.assertNotNull(dao1);
        Assertions.assertEquals(dao1.getFileName(), "jdbcTestFile1");
        Assertions.assertNotNull(dao2);
        Assertions.assertEquals(dao2.getFileName(), "jdbcTestFile2");
        Assertions.assertNotNull(dao3);
        Assertions.assertEquals(dao3.getFileName(), "jdbcTestFile3");

    }
}