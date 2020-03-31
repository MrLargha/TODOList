package ru.mrlagha.data;

import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;

/**
 * Интерфейс, описывающий источник данных
 */
public interface ITODODataSource {
    /**
     * Метод для чтения данных из источника
     *
     * @return данные
     * @throws EntryReadException если возникли ошибки чтения
     */
    String readData() throws EntryReadException;

    /**
     * Метод для записи данных в источник
     *
     * @param data данные для записи
     * @throws EntryWriteException если возникли ошибки записи
     */
    void writeData(String data) throws EntryWriteException;
}
