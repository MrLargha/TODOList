package ru.mrlagha.buisnesslogic.clicommands;

import ru.mrlagha.buisnesslogic.ILogicHandler;
import ru.mrlagha.data.TODOEntry;
import ru.mrlagha.data.exceptions.EntryFileNotFoundException;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.TooMuchTODOsException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * Команда выводящая список дел (всех или только активных)
 */
@CLICommandAnnotation(commandName = "список", commandDescription = "отображает список дел")
public class ShowListCommand extends CLICommand {
    public ShowListCommand(InputStream inputStream, OutputStream outputStream, ILogicHandler logicHandler) {
        super(inputStream, outputStream, logicHandler);
    }

    @Override
    public void execute() throws IOException {
        try {
            if (getYesNo("Показать только невыполненные дела?")) {
                showTODOList(mLogicHandler.getActiveTODOsList());
            } else {
                showTODOList(mLogicHandler.getAllTODOsList());
            }
        } catch (EntryFileNotFoundException e) {
            writeln("Файл со списокм дел не найден");
            writeln("Добавьте хотя бы одно дело");
        } catch (TooMuchTODOsException e) {
            writeln("В файле со списокм дел слишком много дел...");
        } catch (EntryReadException e) {
            writeln("Невозможно прочитать список дел");
        }
    }

    private void showTODOList(ArrayList<TODOEntry> todoEntries) throws IOException {
        if (todoEntries.isEmpty()) {
            writeln("В списке нет ни одного дела!");
            return;
        }
        for (int i = 0; i < todoEntries.size(); ) {
            for (int j = 0; i < todoEntries.size() && j < 3; j++) {
                write(todoEntries.get(i + j).toString());
                i++;
            }
            if (i < todoEntries.size()) {
                if (!getYesNo("Показать следующую страницу?"))
                    break;
            } else {
                writeln("Конец списка!");
            }
        }
    }
}
