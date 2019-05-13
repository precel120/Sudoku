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
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(sudokuBoard);
            objectOutputStream.flush();
        } catch (IOException ioe) {
            System.out.print("IOE nie dziala");
        }
    }

    @Override
    public SudokuBoard read(){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            SudokuBoard sudokuBoard = (SudokuBoard) objectInputStream.readObject();
            return sudokuBoard;
        } catch (EOFException ex) {
            System.out.print("Koniec pliku");
        }catch (IOException ioe){
            System.out.print("IOE exception");
        }catch (ClassNotFoundException cnfe){
            System.out.print("Class not found");
        }
        throw new NullPointerException("Null ptr exception");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
