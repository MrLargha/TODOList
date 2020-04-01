package ru.mrlagha.buisnesslogic;

import ru.mrlagha.buisnesslogic.clicommands.CLICommand;
import ru.mrlagha.buisnesslogic.clicommands.CLICommandFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.InvocationTargetException;

/**
 * Класс, отвечающий за взаимодействие с пользователм через консоль
 * Работает с ILogicHandler, с помощью которого и совершает все операции
 */
public class ConsoleHandler extends ConsoleIO {

    private CLICommandFactory mCommandFactory;

    public ConsoleHandler(InputStream inputStream, OutputStream outputStream, ILogicHandler logicHandler) {
        super(inputStream, outputStream, logicHandler);
        mCommandFactory = new CLICommandFactory(inputStream, outputStream, logicHandler);
    }

    /**
     * Метод выполняющий последовательное считывание и обработку команд
     */
    public void run() {
        boolean continueWork = true;
        try {
            writeln("Добро пожаловать в TODOList!\n");
            while (continueWork) {
                writeln("Список доступных команд:");
                write(mCommandFactory.getCommandsList());
                writeln("Напишите 'Выйти' для выхода");
                var command = getLine();
                if (command.toLowerCase().equals("выйти")) {
                    continueWork = false;
                } else {
                    CLICommand cliCommand = mCommandFactory.create(command);
                    if (cliCommand != null) {
                        cliCommand.execute();
                    } else {
                        writeln("Команда не найдена!!!");
                    }
                }
                writeln("\n");
            }
        } catch (IOException | IllegalClassFormatException | InstantiationException
                | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
