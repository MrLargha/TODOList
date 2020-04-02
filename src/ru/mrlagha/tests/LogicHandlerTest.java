package ru.mrlagha.tests;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import ru.mrlagha.buisnesslogic.LogicHandler;
import ru.mrlagha.data.TODOEntry;
import ru.mrlagha.data.exceptions.EntryWriteException;

import java.io.File;
import java.util.ArrayList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class LogicHandlerTest {

    private LogicHandler mLogicHandler;
    private ArrayList<TODOEntry> completed;
    private ArrayList<TODOEntry> notCompleted;

    static void deleteFile() {
        File file = new File("todo-list.json");
        file.delete();
    }

    @AfterEach
    void tearDown() {
        deleteFile();
    }

    @BeforeEach
        // Включает тестирование добавления записи
    void setUp() {
        deleteFile();

        mLogicHandler = new LogicHandler();
        completed = TestUtil.generateTODOs(10, 0, true);
        notCompleted = TestUtil.generateTODOs(10, 10, false);

        completed.forEach(x -> {
            try {
                mLogicHandler.addTODOEntry(x);
            } catch (EntryWriteException e) {
                e.printStackTrace();
                assert false;
            }
        });
        notCompleted.forEach(x -> {
            try {
                mLogicHandler.addTODOEntry(x);
            } catch (EntryWriteException e) {
                e.printStackTrace();
                assert false;
            }
        });
    }

    @Test
    void getAllTODOsList() throws Exception {
        ArrayList<TODOEntry> all = (ArrayList<TODOEntry>) completed.clone();
        all.addAll(notCompleted);

        var read = mLogicHandler.getAllTODOsList();
        assert read.containsAll(all) && all.containsAll(read);
    }

    @Test
    void getActiveTODOsList() throws Exception {
        var read = mLogicHandler.getActiveTODOsList();
        assert read.containsAll(notCompleted) && notCompleted.containsAll(read);
    }

    @Test
    void setTODOComplete() throws Exception {
        notCompleted.forEach(x -> {
            try {
                mLogicHandler.setTODOComplete(x.caption);
            } catch (EntryWriteException e) {
                e.printStackTrace();
            }
        });
        assert mLogicHandler.getActiveTODOsList().isEmpty();
    }

    @Test
    void deleteTODO() throws Exception {
        mLogicHandler.getAllTODOsList().forEach(x -> {
            try {
                mLogicHandler.deleteTODO(x.caption);
            } catch (EntryWriteException e) {
                e.printStackTrace();
            }
        });
        assert mLogicHandler.getAllTODOsList().isEmpty();
    }
}