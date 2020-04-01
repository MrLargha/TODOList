package ru.mrlagha.data;

import org.jetbrains.annotations.NotNull;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Данный синглетный класс объединяет несколько источников данных,
 * в данный момент в нем нет особой логики работы, так как используется только 1 источник.
 * Но возможно дольнейшее расширение и добваление более сложной логики
 */
public class TODORepository {
    private static TODORepository mInstance;
    private ArrayList<TODOInterpreter> mTODOInterpreters = new ArrayList<>();


    private TODORepository() {
        mTODOInterpreters.add(new TODOJsonInterpreter(new TODOFileSource("todo-list.json")));
    }

    /**
     * При необходимости создает и возвращает интсанцию {@link TODORepository}
     *
     * @return инстанция {@link TODORepository}
     */
    public static TODORepository getInstance() {
        if (mInstance == null) {
            mInstance = new TODORepository();
        }
        return mInstance;
    }

    /**
     * Этот метод возвращает список из уникальных дел из всех доступных источников
     *
     * @return Список дел
     * @throws EntryReadException когда возникла ошибка чтения из одного из источников
     */
    @NotNull
    public ArrayList<TODOEntry> getTODOList() throws EntryReadException {
        var result = new HashSet<TODOEntry>();
        for (TODOInterpreter interpreter : mTODOInterpreters) {
            result.addAll(interpreter.getEntries());
        }
        return new ArrayList<>(result);
    }

    /**
     * Этот метод записывает список дел во все возможные источники
     *
     * @param list Список дел
     * @throws EntryWriteException если произошла ошибка записи в однин из источников
     */
    public void writeTODOList(@NotNull ArrayList<TODOEntry> list) throws EntryWriteException {
        for (TODOInterpreter interpreter : mTODOInterpreters) {
            interpreter.writeEntries(list);
        }
    }

}
