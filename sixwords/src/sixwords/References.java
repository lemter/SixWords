package sixwords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class References extends ServiceWindow {
    public References() {
        setName("Справка");
        setResizable(false);
        setSize(new Dimension(420, 320));

        JFrame ref = getjFrame();

        JTextArea description = new JTextArea();
        description.setText("Привет! Над данным проектом трудились Карим и Мади из группы CS-2101. " +
                "Эта программа представляет из себя минималистичный блокнот. Здесь можно открывать текстовые документы в формате txt, " +
                "менять размер шрифта, чтобы вам было удобнее читать свой текст, менять стиль текста ставя курсив и/или жирный шрифт. " +
                "Для проформы мы добавили подключение к базе данных. Во время запуска программа получает локальный ip-адрес пользователя и " +
                "проверяет есть ли он в базе данных. Если есть, то программа ставит тот размер шрифта, что привязан к этому пользователю. " +
                "Если это первый запуск пользователя, то шрифт устанавливается на стандартныф размер - 13. После закрытия программы, " +
                "она сохраняет в базе данных текущий локальный ip-адрес и размер шрифта для последующего использования. " +
                "В проекте использована библиотека Swing (для оконного интерфейса) и база данных Postgres. Все это выполнено под строгим " +
                "соблюдением правил и принципов ООП (Объектно-ориентированное программирование).");
        description.setEnabled(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        ref.add(description);

        ref.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                hide();
            }
        });
    }
}
