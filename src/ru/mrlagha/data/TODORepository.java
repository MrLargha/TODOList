package ru.mrlagha.data;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class TODORepository {
    private LinkedList<ITODOSource> sources;

    public TODORepository() {

    }

    @NotNull public LinkedList<TODOEntry> getTODOList() throws EntryReadException {
        LinkedList<TODOEntry> result = new LinkedList<>();
        for(ITODOSource source : sources){
            result.addAll(source.getEntries());
        }
        return result;
    }

}
