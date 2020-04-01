package ru.mrlagha.buisnesslogic;

import org.jetbrains.annotations.NotNull;
import ru.mrlagha.data.TODOEntry;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.util.ArrayList;

/**
 * Интерефес, описывающий обработчик логики приложения
 */
public interface ILogicHandler {
    /**
     * Метод для получения всего списка дел
     *
     * @return список
     * @throws EntryReadException если возникли ошибки чтения
     */
    @NotNull ArrayList<TODOEntry> getAllTODOsList() throws EntryReadException;

    /**
     * Метод для получения списка незавершённых дел
     *
     * @return список незавершенных дел
     * @throws EntryReadException если возникли ошибки при чтении
     */
    @NotNull ArrayList<TODOEntry> getActiveTODOsList() throws EntryReadException;

    /**
     * Метод, отмечающий дело как выполненное
     *
     * @param todoEntry дело, которое следует отметить
     * @throws EntryWriteException если возникли ошибки при записи
     */
    void setTODOComplete(@NotNull TODOEntry todoEntry) throws EntryWriteException;

    /**
     * Метод добавляющий дело в список
     *
     * @param todoEntry дело, котрое следует добавить
     * @throws EntryWriteException если возникли ошибки при записи
     */
    void addTODO(@NotNull TODOEntry todoEntry) throws EntryWriteException;

    /**
     * Метод для удаления дела из списка
     *
     * @param todoEntry дело, которое требуется удалить
     * @throws EntryWriteException если возникли ошибки при записи
     */
    void deleteTODO(@NotNull TODOEntry todoEntry) throws EntryWriteException;
}
