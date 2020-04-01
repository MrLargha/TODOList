package ru.mrlagha.buisnesslogic;

import java.io.*;

/**
 * Класс, упрощающий взимодействие с консолью
 * Рекомендуется использовать как суперкласс для классов где есть такое взаимодействие
 */
public class ConsoleIO {
    protected BufferedReader mBufferedReader;
    protected BufferedWriter mBufferedWriter;
    protected ILogicHandler mLogicHandler;

    /**
     * @param inputStream  входной поток данных
     * @param outputStream выходной поток данных
     * @param logicHandler обработчик логики
     */
    public ConsoleIO(InputStream inputStream, OutputStream outputStream, ILogicHandler logicHandler) {
        mBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        mBufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        mLogicHandler = logicHandler;
    }

    /**
     * Метод печатающий в консоль текст и переводящий строку
     *
     * @param s строка которую требуется напечатать
     * @throws IOException если возникли ошибки вывода
     */
    protected void writeln(String s) throws IOException {
        write(s + "\n");
    }

    /**
     * Метод печатающий в консоль текст и НЕ переводящий строку
     *
     * @param s строка которую требуется напечатать
     * @throws IOException если возникли ошибки вывода
     */
    protected void write(String s) throws IOException {
        mBufferedWriter.write(s);
        mBufferedWriter.flush();
    }

    /**
     * Метод, гарантированно получающий от пользователя число
     *
     * @return число из пользовательского ввода
     * @throws IOException если возникли ошибки вывода
     */
    protected int getInt() throws IOException {
        while (true) {
            try {
                return Integer.parseInt(mBufferedReader.readLine());
            } catch (NumberFormatException e) {
                writeln("Введите число!");
            }
        }
    }

    /**
     * Метод читающий 1 строку из консоли
     *
     * @return строка
     * @throws IOException если возникли ошибки вывода
     */
    protected String getLine() throws IOException {
        return mBufferedReader.readLine();
    }

    /**
     * Метод, гарантированно получающий букву от пользователя
     *
     * @return одна буква
     * @throws IOException если возникли ошибки вывода
     */
    protected char getLetter() throws IOException {
        while (true) {
            try {
                String data = mBufferedReader.readLine();
                if (Character.isLetter(data.charAt(0))) {
                    return data.charAt(0);
                }
            } catch (NumberFormatException e) {
                writeln("Введите букву!");
            }
        }
    }

    /**
     * Задает пользователю вопрос. И запрашивает ответ в формате "Да/Нет"
     *
     * @param question вопрос
     * @return ответ пользователя
     * @throws IOException если возникли ошибки вывода
     */
    protected boolean getYesNo(String question) throws IOException {
        writeln(question + " [Y/N]");
        char c = Character.toLowerCase(getLetter());
        while (c != 'y' && c != 'n') {
            writeln("Введите Y или N!");
            c = Character.toLowerCase(getLetter());
        }
        return c == 'y';
    }

}
