package ru.mrlagha.data;

import java.util.Objects;


/**
 * Класс, представляющий одно дело в списке, также служит для сериализации в JSON
 */
public class TODOEntry {
    public String caption;
    public String content;
    public boolean completed;

    /**
     * Конструктор невыполненного дела
     *
     * @param caption заголовок
     * @param content содержимое
     */
    public TODOEntry(String caption, String content) {
        this(caption, content, false);
    }

    /**
     * Стадартный конструктор дела
     *
     * @param caption   заголовк
     * @param content   содержимое
     * @param completed заверешнность дела
     */
    public TODOEntry(String caption, String content, boolean completed) {
        this.caption = caption;
        this.content = content;
        this.completed = completed;
    }

    public String toString() {
        return "Дело: " + caption + "\n" + content + "\nЗавершено:" + (completed ? "Да" : "Нет") + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TODOEntry)) return false;
        TODOEntry todoEntry = (TODOEntry) o;
        return completed == todoEntry.completed &&
                caption.equals(todoEntry.caption) &&
                content.equals(todoEntry.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caption, content, completed);
    }

    public static int compare(TODOEntry first, TODOEntry second) {
        return first.caption.compareTo(second.caption);
    }
}
