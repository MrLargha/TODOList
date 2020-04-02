package ru.mrlagha.tests;

import org.junit.jupiter.api.Test;
import ru.mrlagha.data.TODOEntry;
import ru.mrlagha.data.TODOFileSource;
import ru.mrlagha.data.TODOJsonInterpreter;

import java.util.ArrayList;

class TODOJsonInterpreterTest {
    @Test
    void jsonRWTest() throws Exception {
        var testList = TestUtil.generateTODOsWithRandomStatus(100);
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