package ru.mrlagha.tests;

import org.junit.jupiter.api.Test;
import ru.mrlagha.data.TODOEntry;
import ru.mrlagha.data.TODOFileSource;
import ru.mrlagha.data.TODOJsonInterpreter;

import java.util.ArrayList;

class TODOJsonFileSourceTest {
    @Test
    void jsonRWTest() throws Exception {
        var testList = new ArrayList<TODOEntry>();
        testList.add(new TODOEntry("Task1", "Content1", true));
        testList.add(new TODOEntry("Task2", "Content2", false));
        testList.add(new TODOEntry("Task3", "Content3", true));
        testList.add(new TODOEntry("Task4", "Content4"));
        TODOJsonInterpreter todoJsonFileSource =
                new TODOJsonInterpreter(new TODOFileSource("TODOJSONFileSourceTest_RWTest.json"));
        todoJsonFileSource.writeEntries(testList);
        assert testList.equals(todoJsonFileSource.getEntries());
    }

    @Test
    void emptyJSONRWTest() throws Exception {
        var testList = new ArrayList<TODOEntry>();
        TODOJsonInterpreter todoJsonFileSource =
                new TODOJsonInterpreter(new TODOFileSource("TODOJSONFileSourceTest_RWTest.json"));
        todoJsonFileSource.writeEntries(testList);
        var readResult = todoJsonFileSource.getEntries();
        assert readResult.isEmpty();
    }
}