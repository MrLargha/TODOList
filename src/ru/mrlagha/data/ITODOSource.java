package ru.mrlagha.data;

import org.jetbrains.annotations.NotNull;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.util.LinkedList;

public interface ITODOSource {
    @NotNull LinkedList<TODOEntry> getEntries() throws EntryReadException;
    void writeEntries(@NotNull LinkedList<TODOEntry> entries) throws EntryWriteException;
}
