package ru.mrlagha.buisnesslogic.clicommands;

import ru.mrlagha.buisnesslogic.ILogicHandler;
import ru.mrlagha.data.exceptions.EntryNotFoundException;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@CLICommandAnnotation(commandName = "удалить", commandDescription = "удалить дело из списка")
public class DeleteTODOCommand extends CLICommand {
    /**
     * Создает обработчик консольной команды
     *
     * @param inputStream  поток входных данных
     * @param outputStream поток выходных данных
     * @param logicHandler обработчк логики
     */
    public DeleteTODOCommand(InputStream inputStream, OutputStream outputStream, ILogicHandler logicHandler) {
        super(inputStream, outputStream, logicHandler);
    }

    @Override
    public void execute() throws IOException {
        writeln("Введите название дела, которое вы хотите удалить из списка: ");
        String todoTitle = getLine();
        try {
            mLogicHandler.deleteTODO(todoTitle);
        } catch (EntryNotFoundException e) {
            writeln("Дело не найдено, нечего удалять!");
        } catch (EntryWriteException e) {
            writeln("Невозможно записать список дел!");
        }
    }
}
