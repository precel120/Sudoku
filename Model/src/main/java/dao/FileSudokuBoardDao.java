package dao;

import zad2.SudokuBoard;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private String fileName;
   // private ResourceBundle resourceBundle = ResourceBundle.getBundle("langModel_pl");
   final static Logger logger = Logger.getLogger(FileSudokuBoardDao.class);

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) {
        ResourceBundle bundle;
        if(Locale.getDefault().toString().equals("pl")) bundle = ResourceBundle.getBundle("langModel_pl");
        else bundle = ResourceBundle.getBundle("langModel");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(sudokuBoard);
            objectOutputStream.flush();
            logger.debug(bundle.getString("boardSaved"));
        } catch (IOException ioe) {
            logger.error(bundle.getString("ioe"), ioe);
            ioe.getCause();
        }
    }

    @Override
    public SudokuBoard read(){
        ResourceBundle bundle;
        if(Locale.getDefault().toString().equals("pl")) bundle = ResourceBundle.getBundle("langModel_pl");
        else bundle = ResourceBundle.getBundle("langModel");
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            SudokuBoard sudokuBoard = (SudokuBoard) objectInputStream.readObject();
            logger.debug(bundle.getString("readBoard"));
            return sudokuBoard;
        } catch (EOFException ex) {
           // System.out.print(resourceBundle.getObject("endFile"));
            logger.error(bundle.getString("endFile"), ex);
            ex.getCause();
        }catch (IOException ioe){
          ///  System.out.print(resourceBundle.getObject("ioe"));
            logger.error(bundle.getString("ioe"), ioe);
            ioe.getCause();
        }catch (ClassNotFoundException cnfe){
           // System.out.print(resourceBundle.getObject("klass"));
            logger.error(bundle.getString("klass"), cnfe);
            cnfe.getCause();
        }
        //resourceBundle.getString("nullPointer")
        throw new NullPointerException(bundle.getString("nullPointer"));
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}