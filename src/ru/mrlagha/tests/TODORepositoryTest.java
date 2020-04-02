package ru.mrlagha.tests;

import org.junit.jupiter.api.Test;
import ru.mrlagha.data.TODORepository;


class TODORepositoryTest {
    @Test
    public void complexRWTest() throws Exception {
        TODORepository todoRepository = TODORepository.getInstance("todo-list-test.json");

        assert todoRepository != null;

        var todos = TestUtil.generateTODOsWithRandomStatus(5);
        todoRepository.writeTODOList(todos);
        var readTodos = todoRepository.getTODOList();

        assert todos.equals(readTodos);
    }
}