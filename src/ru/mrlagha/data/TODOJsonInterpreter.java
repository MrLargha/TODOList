package ru.mrlagha.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;
import ru.mrlagha.data.exceptions.TooMuchTODOsException;

import java.util.ArrayList;

/**
 * Класс для работы со списком дел в JSON-формате
 * Для работы с файлами используется {@link TODOFileSource}
 */
public class TODOJsonInterpreter extends TODOInterpreter {

    private static final int MAX_ENTRIES = 99999;

    public TODOJsonInterpreter(ITODODataSource dataSource) {
        super(dataSource);
    }

    @Override
    public @NotNull ArrayList<TODOEntry> getEntries() throws EntryReadException {
        try {
            ArrayList<TODOEntry> list = new Gson().fromJson(mDataSource.readData(),
                    new TypeToken<ArrayList<TODOEntry>>() {
                    }.getType());
            if (list.size() > MAX_ENTRIES) {
                throw new TooMuchTODOsException();
            }
            return list;
        } catch (JsonSyntaxException e) {
            throw new EntryReadException();
        }
    }

    @Override
    public void writeEntries(@NotNull ArrayList<TODOEntry> entries) throws EntryWriteException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        mDataSource.writeData(gson.toJson(entries));
    }
}
