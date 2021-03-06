package ru.mrlagha.tests;

import ru.mrlagha.data.TODOEntry;

import java.util.ArrayList;
import java.util.Random;

public class TestUtil {
    public static ArrayList<TODOEntry> generateTODOs(int amount, int begin_index, boolean complete) {
        var entries = new ArrayList<TODOEntry>();
        for (int i = begin_index; i < amount + begin_index; i++) {
            entries.add(new TODOEntry("Дело " + i, "Содержиое дела " + i + ", lorem ipsum", complete));
        }
        return entries;
    }

    public static ArrayList<TODOEntry> generateTODOsWithRandomStatus(int amount) {
        var entries = new ArrayList<TODOEntry>();
        Random rand = new Random();
        for (int i = 0; i < amount; i++) {
            entries.add(new TODOEntry("Дело " + i, "Содержиое дела " + i + ", lorem ipsum",
                    rand.nextBoolean()));
        }
        return entries;
    }
}
