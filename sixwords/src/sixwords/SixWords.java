package sixwords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SixWords extends JFrame { // наследуем в классе JFrame

    final ImageIcon logoIcon = new ImageIcon(SixWords.class.getResource("/sixwords/resources/feather.jpg"));

    public SixWords() { // инициализатор. здесь задаем первоначальные параметры окна приложения, после запуска
        // Создаем окно, задаем размер, центрируем на экране, добавляем верхнее меню
        setVisible(true);
        setSize(500, 400);
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((s.width - getWidth()) / 2, (s.height - getHeight()) / 2);
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(createFileMenu());
        jMenuBar.add(createAboutMenu());
        setJMenuBar(jMenuBar);

        JPanel jPanel = new JPanel();

        JTextArea jTextArea = new JTextArea();
        jPanel.add(jTextArea);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setSize(this.getSize());
        jTextArea.setRows(getHeight() / 18);

        setContentPane(jPanel);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                jTextArea.setSize(getSize());
                jTextArea.setRows(getHeight() / 18);
            }
        });
    }

    private JMenu createFileMenu() { // функция для создания пункта в меню
        JMenu file = new JMenu("Файл");
        JMenuItem open = new JMenuItem("Открыть");
        JMenuItem save = new JMenuItem("Сохранить");
        JMenuItem create = new JMenuItem("Новый проект");
        file.add(open);
        file.add(save);
        file.add(create);
        return file;
    }

    private JMenu createAboutMenu() { // функция для создания пункта в меню
        JMenu help = new JMenu("Помощь");
        JMenuItem reference = new JMenuItem("Справка");
        help.add(reference);
        return help;
    }

    public static void main(String args[]) {
        new SixWords(); // запускаем инициализатор класса
    }
}
