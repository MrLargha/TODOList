package ru.mrlagha.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.util.LinkedList;

/**
 * Класс для работы со списком дел в JSON-формате
 * Для работы с файлами используется {@link TODOFileSource}
 */
public class TODOJsonInterpreter extends TODOInterpreter {

    public TODOJsonInterpreter(ITODODataSource dataSource) {
        super(dataSource);
    }

    @Override
    public @NotNull LinkedList<TODOEntry> getEntries() throws EntryReadException {
        return new Gson().fromJson(mDataSource.readData(), new TypeToken<LinkedList<TODOEntry>>() {
        }.getType());
    }

    @Override
    public void writeEntries(@NotNull LinkedList<TODOEntry> entries) throws EntryWriteException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        mDataSource.writeData(gson.toJson(entries));
    }
}
