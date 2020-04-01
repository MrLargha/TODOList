package ru.mrlagha.buisnesslogic;

import org.jetbrains.annotations.NotNull;
import ru.mrlagha.data.TODOEntry;
import ru.mrlagha.data.TODORepository;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.util.ArrayList;

/**
 * Класс, отвечающий за обработку логики.
 * Работает под управлением {@link ConsoleHandler}
 */
public class LogicHandler implements ILogicHandler {

    @NotNull
    @Override
    public ArrayList<TODOEntry> getAllTODOsList() throws EntryReadException {
        return TODORepository.getInstance().getTODOList();
    }

    @NotNull
    @Override
    public ArrayList<TODOEntry> getActiveTODOsList() throws EntryReadException {
        return null;
    }

    @Override
    public void setTODOComplete(@NotNull TODOEntry todoEntry) throws EntryWriteException {

    }

    @Override
    public void addTODO(@NotNull TODOEntry todoEntry) throws EntryWriteException {

    }

    @Override
    public void deleteTODO(@NotNull TODOEntry todoEntry) throws EntryWriteException {

    }
}
