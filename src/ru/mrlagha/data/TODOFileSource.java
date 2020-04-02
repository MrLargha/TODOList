package ru.mrlagha.data;

import ru.mrlagha.data.exceptions.EntryFileNotFoundException;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.io.*;
import java.util.stream.Collectors;

/**
 * Класс представляющий собой файловый источник данных
 */
public class TODOFileSource implements ITODODataSource {
    private String filename;

    /**
     * @param filename имя файла, с которым будет производится работа
     */
    public TODOFileSource(String filename) {
        this.filename = filename;
    }

    @Override
    public String readData() throws EntryReadException {
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            return in.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (FileNotFoundException e) {
            throw new EntryFileNotFoundException();
        } catch (IOException e) {
            throw new EntryReadException();
        }
    }

    @Override
    public void writeData(String data) throws EntryWriteException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            out.write(data);
            out.flush();
        } catch (IOException e) {
            throw new EntryWriteException();
        }
    }
}
