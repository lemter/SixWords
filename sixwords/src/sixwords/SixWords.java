package sixwords;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SixWords extends JFrame { // наследуем в классе JFrame

    private  JFileChooser fileChooser = new JFileChooser();

    public SixWords() { // инициализатор. здесь задаем первоначальные параметры окна приложения, после запуска
        // Создаем окно, задаем размер, центрируем на экране, добавляем верхнее меню
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(createFileMenu());
        jMenuBar.add(createAboutMenu());
        setJMenuBar(jMenuBar);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());


        JTextArea jTextArea = new JTextArea();
        jTextArea.setText("");
        jTextArea.setCaretPosition(0);

        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jPanel.add(jScrollPane, BorderLayout.CENTER);

        setContentPane(jPanel);

        setSize(600, 600);
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((s.width - getWidth()) / 2, (s.height - getHeight()) / 2);
        setVisible(true);
    }

    private JMenu createFileMenu() { // функция для создания пункта в меню
        JMenu file = new JMenu("Файл");
        JMenuItem open = new JMenuItem("Открыть");
        JMenuItem save = new JMenuItem("Сохранить");
        JMenuItem saveAs = new JMenuItem("Сохранить как...");
        file.add(open);
        file.add(save);
        file.add(saveAs);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выберите текстовый файл");
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
                fileChooser.addChoosableFileFilter(filter);
                fileChooser.showOpenDialog(SixWords.this);
            }
        });

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

