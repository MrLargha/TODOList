package ru.mrlagha.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.util.LinkedList;

public class TODOJsonFileSource implements ITODOSource {
    private TODOFileSource mTodoFileSource;

    public TODOJsonFileSource(String filename) {
        mTodoFileSource = new TODOFileSource(filename);
    }

    @Override
    public @NotNull LinkedList<TODOEntry> getEntries() throws EntryReadException {
        return new Gson().fromJson(mTodoFileSource.readFile(), new TypeToken<LinkedList<TODOEntry>>() {}.getType());
    }

    @Override
    public void writeEntries(@NotNull LinkedList<TODOEntry> entries) throws EntryWriteException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        mTodoFileSource.writeFile(gson.toJson(entries));
    }
}
