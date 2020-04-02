package ru.mrlagha.buisnesslogic;

import org.jetbrains.annotations.NotNull;
import ru.mrlagha.data.TODOEntry;
import ru.mrlagha.data.TODORepository;
import ru.mrlagha.data.exceptions.EntryFileNotFoundException;
import ru.mrlagha.data.exceptions.EntryNotFoundException;
import ru.mrlagha.data.exceptions.EntryReadException;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Класс, отвечающий за обработку логики.
 * Работает под управлением {@link ConsoleHandler}
 */
public class LogicHandler implements ILogicHandler {

    private TODORepository mRepository = TODORepository.getInstance("todo-list.json");

    @NotNull
    @Override
    public ArrayList<TODOEntry> getAllTODOsList() throws EntryReadException {
        return mRepository.getTODOList();
    }

    @NotNull
    @Override
    public ArrayList<TODOEntry> getActiveTODOsList() throws EntryReadException {
        return (ArrayList<TODOEntry>) mRepository.getTODOList()
                .stream().filter(x -> !x.completed).collect(Collectors.toList());
    }

    @Override
    public void setTODOComplete(String todoTitle) throws EntryWriteException {
        try {
            var list = mRepository.getTODOList();
            boolean isOk = false;
            for (TODOEntry entry : list) {
                if (entry.caption.equals(todoTitle)) {
                    entry.completed = true;
                    isOk = true;
                }
            }
            if (!isOk) {
                throw new EntryNotFoundException();
            }
            mRepository.writeTODOList(list);
        } catch (EntryReadException e) {
            throw new EntryWriteException();
        }
    }


    @Override
    public void addTODOEntry(@NotNull TODOEntry todoEntry) throws EntryWriteException {
        ArrayList<TODOEntry> list;
        try {
            list = mRepository.getTODOList();
        } catch (EntryFileNotFoundException e) {
            list = new ArrayList<>();
        } catch (EntryReadException e) {
            throw new EntryWriteException();
        }
        list.add(todoEntry);
        mRepository.writeTODOList(list);
    }

    @Override
    public void deleteTODO(String todoTitle) throws EntryWriteException {
        try {
            var list = mRepository.getTODOList();
            if (!list.removeIf(entry -> entry.caption.equals(todoTitle))) {
                throw new EntryNotFoundException();
            }
            mRepository.writeTODOList(list);
        } catch (EntryReadException e) {
            throw new EntryWriteException();
        }
    }
}
