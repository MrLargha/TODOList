package ru.mrlagha.tests;

import org.junit.jupiter.api.Test;
import ru.mrlagha.data.TODOFileSource;

class TODOFileSourceTest {

    @Test
    void readWriteFile() throws Exception {
        TODOFileSource todoFileSource = new TODOFileSource("TODOFileSourceTest_RWTest.data");
        String testStr = "Lorem ipsum \r\n dolor sit amet";
        todoFileSource.writeFile(testStr);
        assert todoFileSource.readFile().equals(testStr);
    }
}