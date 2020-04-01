package ru.mrlagha.buisnesslogic;

import ru.mrlagha.data.TODOEntry;
import ru.mrlagha.data.exceptions.EntryFileNotFoundException;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.TooMuchTODOsException;

import java.io.*;
import java.util.ArrayList;

/**
 * Класс, отвечающий за взаимодействие с пользователм через консоль
 * Работает с ILogicHandler, с помощью которого и совершает все операции
 */
public class ConsoleHandler {
    private BufferedReader mBufferedReader;
    private BufferedWriter mBufferedWriter;
    private ILogicHandler mLogicHandler;

    public ConsoleHandler(InputStream inputStream, OutputStream outputStream, ILogicHandler logicHandler) {
        mBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        mBufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        mLogicHandler = logicHandler;
    }

    public void run() {
        boolean continueWork = true;
        try {
            writeln("Добро пожаловать в TODOList!\n");
            while (continueWork) {
                writeln("Выберите действие и введите его номер:");
                writeln("[1] - Посмотреть список дел");
                writeln("[2] - Посмотреть список невыполненных дел");
                writeln("[3] - Добавить дело");
                writeln("[4] - Отметить дело как выполненное");
                writeln("[5] - Удалить дело");
                writeln("[0] - Выйти");

                int choose = getInt();

                switch (choose) {
                    case 0:
                        continueWork = false;
                        break;
                    case 1:
                        showAllList();
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAllList() throws IOException {
        try {
            ArrayList<TODOEntry> todoEntries = mLogicHandler.getAllTODOsList();
            if (todoEntries.isEmpty()) {
                writeln("В спсике нет ни одного дела!");
                return;
            }
            for (int i = 0; i < todoEntries.size(); ) {
                for (int j = 0; i < todoEntries.size() && j < 3; j++) {
                    write(todoEntries.get(i + j).toString());
                    i++;
                }
                if (i < todoEntries.size()) {
                    writeln("Показать следующую страницу [Y/n]");
                    char c = Character.toLowerCase(getLetter());
                    while (c != 'y' && c != 'n') {
                        writeln("Введите Y или N!");
                        c = Character.toLowerCase(getLetter());
                    }
                    if (c == 'n') {
                        break;
                    }
                } else {
                    writeln("Конец списка!");
                }
            }
        } catch (EntryFileNotFoundException e) {
            writeln("Файл со списокм дел не найден");
            writeln("Добавьте дело чтобы его создать");
        } catch (TooMuchTODOsException e) {
            writeln("В файле со списокм дел слишком много дел...");
        } catch (EntryReadException e) {
            writeln("Невозможно прочитать файл, возможно он используется другим приложением");
        }
    }

    private void writeln(String s) throws IOException {
        write(s + "\n");
    }

    private void write(String s) throws IOException {
        mBufferedWriter.write(s);
    }

    private int getInt() throws IOException {
        while (true) {
            try {
                return Integer.parseInt(mBufferedReader.readLine());
            } catch (NumberFormatException e) {
                writeln("Введите число!");
            }
        }
    }

    private char getLetter() throws IOException {
        while (true) {
            try {
                String data = mBufferedReader.readLine();
                if (Character.isLetter(data.charAt(0))) {
                    return data.charAt(0);
                }
            } catch (NumberFormatException e) {
                writeln("Введите букву!");
            }
        }
    }
}
