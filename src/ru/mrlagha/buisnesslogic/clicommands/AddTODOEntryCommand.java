package ru.mrlagha.buisnesslogic.clicommands;

import ru.mrlagha.buisnesslogic.ILogicHandler;
import ru.mrlagha.data.TODOEntry;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Команда добавляющая дело в список
 */
@CLICommandAnnotation(commandName = "добавить", commandDescription = "добавить новое дело")
public class AddTODOEntryCommand extends CLICommand {

    /**
     * Создает обработчик консольной команды
     *
     * @param inputStream  поток входных данных
     * @param outputStream поток выходных данных
     * @param logicHandler обработчк логики
     */
    public AddTODOEntryCommand(InputStream inputStream, OutputStream outputStream, ILogicHandler logicHandler) {
        super(inputStream, outputStream, logicHandler);
    }

    @Override
    public void execute() throws IOException {
        write("Введите название дела: ");
        String caption = getLine();
        write("Введите содержимое дела: ");
        String content = getLine();
        try {
            TODOEntry entry = new TODOEntry(caption, content);
            mLogicHandler.addTODOEntry(entry);
            writeln("Дело добавлено");
        } catch (EntryWriteException e) {
            writeln("Невозможно добавить запись!");
        }
    }
}
