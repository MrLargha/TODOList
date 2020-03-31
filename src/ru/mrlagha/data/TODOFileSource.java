package ru.mrlagha.data;

import ru.mrlagha.data.exceptions.EntryFileNotFoundException;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.io.*;
import java.util.stream.Collectors;

public class TODOFileSource {
    private String filename;

    public TODOFileSource(String filename) {
        this.filename = filename;
    }

    public String readFile() throws EntryReadException {
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            return in.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (FileNotFoundException e) {
            throw new EntryFileNotFoundException();
        } catch (IOException e){
            throw new EntryReadException();
        }
    }

    public void writeFile(String content) throws EntryWriteException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            out.write(content);
        } catch (IOException e) {
            throw new EntryWriteException();
        }
    }
}
