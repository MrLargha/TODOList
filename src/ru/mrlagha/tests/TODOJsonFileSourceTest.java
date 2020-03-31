package ru.mrlagha.tests;

import org.junit.jupiter.api.Test;
import ru.mrlagha.data.TODOEntry;
import ru.mrlagha.data.TODOJsonFileSource;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TODOJsonFileSourceTest {
    @Test
    void jsonRWTest() throws Exception{
        var testList = new LinkedList<TODOEntry>();
        testList.add(new TODOEntry("Task1", "Content1", true));
        testList.add(new TODOEntry("Task2", "Content2", false));
        testList.add(new TODOEntry("Task3", "Content3", true));
        testList.add(new TODOEntry("Task4", "Content4"));
        TODOJsonFileSource todoJsonFileSource = new TODOJsonFileSource("TODOJSONFileSourceTest_RWTest.json");
        todoJsonFileSource.writeEntries(testList);
        assert testList.equals(todoJsonFileSource.getEntries());
    }
}