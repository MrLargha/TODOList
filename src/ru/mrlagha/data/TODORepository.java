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
    private LinkedList<ITODOSource> sources = new LinkedList<>();

    public TODORepository() {
        sources.add(new TODOJsonFileSource("todo-list.json"));
    }

    /**
     * Этот метод возвращает список из уникальных дел из всех доступных источников
     * @return Список дел
     * @throws EntryReadException когда возникла ошибка чтения из одного из источников
     */
    @NotNull public LinkedList<TODOEntry> getTODOList() throws EntryReadException {
        var result = new HashSet<TODOEntry>();
        for(ITODOSource source : sources){
            result.addAll(source.getEntries());
        }
        return new LinkedList<>(result);
    }

    /**
     * Этот метод записывает список дел во все возможные источники
     * @param list список дел
     * @throws EntryWriteException если произошла ошибка записи в однин из источников
     */
    public void writeTODOList(@NotNull LinkedList<TODOEntry> list) throws EntryWriteException {
        for(ITODOSource source : sources){
            source.writeEntries(list);
        }
    }

}
