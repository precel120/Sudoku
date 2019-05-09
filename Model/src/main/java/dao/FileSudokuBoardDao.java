package dao;

import zad2.SudokuBoard;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputStream.writeObject(sudokuBoard);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException ioe) {
            System.out.print("ioe nie trybi");
        }
    }

    @Override
    public SudokuBoard read(){
        ObjectInputStream objectInputStream = null;
        SudokuBoard sudokuBoard = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
            objectInputStream.close();
            return sudokuBoard;
        } catch (EOFException ex) {
            System.out.print("Koniec pliku");
        }catch (IOException ioe){
            System.out.print("ioe nie bangla");
        }catch (ClassNotFoundException cnfe){
            System.out.print("kurcze kurcze");
        }
        throw new NullPointerException("faken zapisz to");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
