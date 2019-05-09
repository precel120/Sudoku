package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class daoFactoryTest {

    @Test
    void getFileDaoTest() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        FileSudokuBoardDao dao1 = factory.getFileDao("1.bin");
        FileSudokuBoardDao dao2 = factory.getFileDao("2.bin");
        FileSudokuBoardDao dao3 = factory.getFileDao("3.bin");
        Assertions.assertNotNull(dao1);
        Assertions.assertEquals(dao1.getFileName(), "1.bin");
        Assertions.assertNotNull(dao2);
        Assertions.assertEquals(dao2.getFileName(), "2.bin");
        Assertions.assertNotNull(dao3);
        Assertions.assertEquals(dao3.getFileName(),"3.bin");
    }
}