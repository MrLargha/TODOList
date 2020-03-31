package ru.mrlagha.data;

import org.jetbrains.annotations.NotNull;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Данный класс объединяет несколько источников данных,
 * в данный момент в нем нет особой логики работы, так как используется только 1 источник.
 * Но возможно дольнейшее расширение и добваление более сложной логики
 */
public class TODORepository {
    private LinkedList<TODOInterpreter> mTODOInterpreters = new LinkedList<>();

    public TODORepository() {
        mTODOInterpreters.add(new TODOJsonInterpreter(new TODOFileSource("todo-list.json")));
    }

    /**
     * Этот метод возвращает список из уникальных дел из всех доступных источников
     *
     * @return Список дел
     * @throws EntryReadException когда возникла ошибка чтения из одного из источников
     */
    @NotNull
    public LinkedList<TODOEntry> getTODOList() throws EntryReadException {
        var result = new HashSet<TODOEntry>();
        for (TODOInterpreter interpreter : mTODOInterpreters) {
            result.addAll(interpreter.getEntries());
        }
        return new LinkedList<>(result);
    }

    /**
     * Этот метод записывает список дел во все возможные источники
     *
     * @param list Список дел
     * @throws EntryWriteException если произошла ошибка записи в однин из источников
     */
    public void writeTODOList(@NotNull LinkedList<TODOEntry> list) throws EntryWriteException {
        for (TODOInterpreter interpreter : mTODOInterpreters) {
            interpreter.writeEntries(list);
        }
    }

}
