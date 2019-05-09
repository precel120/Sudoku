package dao;

import java.io.File;

public class SudokuBoardDaoFactory {

    public FileSudokuBoardDao getFileDao(String fileName) {
        FileSudokuBoardDao dao=new FileSudokuBoardDao(fileName);
        return dao;
    }

}
