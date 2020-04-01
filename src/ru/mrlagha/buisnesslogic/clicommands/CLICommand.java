package ru.mrlagha.buisnesslogic.clicommands;

import ru.mrlagha.buisnesslogic.ConsoleIO;
import ru.mrlagha.buisnesslogic.ILogicHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Класс представляющй собой обработчки консольной команды
 */
public abstract class CLICommand extends ConsoleIO {

    /**
     * Создает обработчик консольной команды
     *
     * @param inputStream  поток входных данных
     * @param outputStream поток выходных данных
     * @param logicHandler обработчк логики
     */
    public CLICommand(InputStream inputStream, OutputStream outputStream, ILogicHandler logicHandler) {
        super(inputStream, outputStream, logicHandler);
    }

    /**
     * Метод выполняющий обработку команды
     *
     * @throws IOException если возникли ошибки чтения / записи в поток
     */
    public abstract void execute() throws IOException;
}
