package ru.mrlagha.buisnesslogic.clicommands;

import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import ru.mrlagha.buisnesslogic.ILogicHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

/**
 * Класс-фабрика для создания экземпляров {@link CLICommand}.
 * Создает экземпляр по переданному названию команды
 */
public class CLICommandFactory {

    private HashSet<Class<? extends CLICommand>> mCommands;
    private InputStream mIn;
    private OutputStream mOut;
    private ILogicHandler mHandler;

    /**
     * @param in      входной потоко данных
     * @param out     выходной поток данных
     * @param handler обработчик
     */
    public CLICommandFactory(InputStream in, OutputStream out, ILogicHandler handler) {
        mIn = in;
        mOut = out;
        mHandler = handler;

        // Используя библиотеку Reflections ищем всех наследников CLICommand
        Reflections reflections = new Reflections("ru.mrlagha.buisnesslogic.clicommands");
        mCommands = (HashSet<Class<? extends CLICommand>>) reflections.getSubTypesOf(CLICommand.class);
    }

    /**
     * Метод создающий команду по данному имени, имя команды не обязательно передавать полностью
     * можно ввести только первые букву команды
     *
     * @param commandName полное или частичное имя команды
     * @return соответсвующий экземпляр {@link CLICommand} или {@code null}  если команда не найдена
     * @throws IllegalClassFormatException -
     * @throws IllegalAccessException      -
     * @throws InstantiationException      -
     * @throws NoSuchMethodException       -
     * @throws InvocationTargetException   -
     */
    @Nullable
    public CLICommand create(String commandName)
            throws IllegalClassFormatException, IllegalAccessException, InstantiationException, NoSuchMethodException,
            InvocationTargetException {
        for (var commandClass : mCommands) {
            CLICommandAnnotation annotation = commandClass.getAnnotation(CLICommandAnnotation.class);
            if (annotation == null) {
                throw new IllegalClassFormatException("Команада " + commandClass.getCanonicalName() +
                        "должна иметь аннотацию CLICommandAnnotation");
            }
            if (annotation.commandName().startsWith(commandName)) {
                return commandClass.getDeclaredConstructor(InputStream.class, OutputStream.class, ILogicHandler.class)
                        .newInstance(mIn, mOut, mHandler);
            }
        }
        return null;
    }

    /**
     * Метод для получения списка всех доступных команд
     *
     * @return форматированный список доступных команд
     * @throws IllegalClassFormatException -
     */
    public String getCommandsList() throws IllegalClassFormatException {
        StringBuilder result = new StringBuilder();
        for (var commandClass : mCommands) {
            CLICommandAnnotation annotation = commandClass.getAnnotation(CLICommandAnnotation.class);
            if (annotation == null) {
                throw new IllegalClassFormatException("Команада " + commandClass.getCanonicalName() +
                        "должна иметь аннотацию CLICommandAnnotation");
            }
            result.append(annotation.commandName()).append(" - ").append(annotation.commandDescription()).append('\n');
        }
        return result.toString();
    }
}
