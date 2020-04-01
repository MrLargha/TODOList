package ru.mrlagha.data;

import org.jetbrains.annotations.NotNull;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.util.ArrayList;

/**
 * Интерфес, представляющий собой интерперататор списка дел в(из) определенный формат
 * В качестве источника данных используется {@link ITODODataSource}
 */
public abstract class TODOInterpreter {
    protected ITODODataSource mDataSource;

    TODOInterpreter(ITODODataSource dataSource) {
        mDataSource = dataSource;
    }

    /**
     * Метод для получения и десериализации в {@link ArrayList<TODOEntry>} списка дел из источника
     *
     * @return список дел
     * @throws EntryReadException если возникла ошибка при чтении или десериализации
     */
    @NotNull
    public abstract ArrayList<TODOEntry> getEntries() throws EntryReadException;

    /**
     * Метод для записи данных в источник с сериализацией в определенный формат
     *
     * @param entries список дел для записи
     * @throws EntryWriteException если возникла ошибка при записи
     */
    public abstract void writeEntries(@NotNull ArrayList<TODOEntry> entries) throws EntryWriteException;
}
