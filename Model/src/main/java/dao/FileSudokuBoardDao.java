package dao;

import zad2.SudokuBoard;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private String fileName;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("langModel");

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(sudokuBoard);
            objectOutputStream.flush();
        } catch (IOException ioe) {
            System.out.print(resourceBundle.getObject("ioe"));
            ioe.getCause();
        }
    }

    @Override
    public SudokuBoard read(){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            SudokuBoard sudokuBoard = (SudokuBoard) objectInputStream.readObject();
            return sudokuBoard;
        } catch (EOFException ex) {
            System.out.print(resourceBundle.getObject("endFile"));
            ex.getCause();
        }catch (IOException ioe){
            System.out.print(resourceBundle.getObject("ioe"));
            ioe.getCause();
        }catch (ClassNotFoundException cnfe){
            System.out.print(resourceBundle.getObject("klass"));
            cnfe.getCause();
        }
        throw new NullPointerException("Null ptr exception");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
