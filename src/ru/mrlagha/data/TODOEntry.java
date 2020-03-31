package ru.mrlagha.data;

import java.util.Objects;

public class TODOEntry {
    public String caption;
    public String content;
    public boolean completed;

    public TODOEntry(String caption, String content) {
        this(caption, content, false);
    }

    public TODOEntry(String caption, String content, boolean completed) {
        this.caption = caption;
        this.content = content;
        this.completed = completed;
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
}
