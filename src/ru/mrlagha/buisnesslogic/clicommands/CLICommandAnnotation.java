package ru.mrlagha.buisnesslogic.clicommands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация для классов-наследников от {@link CLICommand}.
 * Описывает команду: ее название и описание
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CLICommandAnnotation {
    /**
     * Имя консольной команды
     *
     * @return имя команды
     */
    String commandName();

    /**
     * Описнаие консольной команды
     *
     * @return описание команды
     */
    String commandDescription();
}
