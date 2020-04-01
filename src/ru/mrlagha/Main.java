package ru.mrlagha;

import ru.mrlagha.buisnesslogic.ConsoleHandler;
import ru.mrlagha.buisnesslogic.LogicHandler;

public class Main {
    public static void main(String[] args) {
        new ConsoleHandler(System.in, System.out, new LogicHandler()).run();
    }
}
