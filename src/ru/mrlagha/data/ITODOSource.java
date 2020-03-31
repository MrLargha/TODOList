package ru.mrlagha.data;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public interface ITODOSource {
    @NotNull LinkedList<TODOEntry> getEntries() throws EntryReadException;
    void writeEntries(@NotNull LinkedList<TODOEntry> entries) throws EntryWriteException;
}
