package sixwords;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SixWords extends JFrame { // наследуем в классе JFrame

    private JFileChooser fileChooser = new JFileChooser();
    private File current = null;
    JTextArea jTextArea = new JTextArea();

    public SixWords() { // инициализатор. здесь задаем первоначальные параметры окна приложения, после запуска
        // Создаем окно, задаем размер, центрируем на экране, добавляем верхнее меню
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(createFileMenu());
        jMenuBar.add(createAboutMenu());
        setJMenuBar(jMenuBar);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        jTextArea.setText("");
        jTextArea.setCaretPosition(0);

        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jPanel.add(jScrollPane, BorderLayout.CENTER);

        setContentPane(jPanel);

        setSize(600, 600);
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((s.width - getWidth()) / 2, (s.height - getHeight()) / 2);
        setTitle("Six Words - новый файл");

        setIconImage(Toolkit.getDefaultToolkit().getImage(new File("resources/feather.png").toString()));

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

        open.addActionListener(new ActionListener() { // cобытие нажатия на кнопку "открыть"
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выберите текстовый файл");
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
                fileChooser.addChoosableFileFilter(filter);
                try {
                    fileChooser.showOpenDialog(SixWords.this); // выводим окно выбора файла

                    current = fileChooser.getSelectedFile();
                    jTextArea.setText("");

                    try(FileReader reader = new FileReader(current)) { // читаем посимвольно, вписываем в блокнот
                        setTitle("Six Words - " + current.getName());
                        int c;
                        while((c = reader.read()) != -1) {
                            jTextArea.setText(jTextArea.getText() + ((char)c));
                        }
                    }
                    catch(IOException ex) { // вывод диалогого окна, при возникновении ошибки при чтении
                        JOptionPane.showMessageDialog(SixWords.this, ex.getMessage(), "File reading error", JOptionPane.WARNING_MESSAGE);
                    }
                } catch(NullPointerException ex) {}; // если пользователь не выбрал файл, ничего не делаем
            }
        });

        save.addActionListener(new ActionListener() { // cобытие нажатия на кнопку "сохранить"
            @Override
            public void actionPerformed(ActionEvent e) {
                if(current == null) {
                    JOptionPane.showMessageDialog(SixWords.this, "Вы не выбрали файл.", "File writing error", JOptionPane.WARNING_MESSAGE);
                } else { // если выбранный файл имеется, делаем в него запись
                    try(FileWriter writer = new FileWriter(current, false)) {
                        writer.write(jTextArea.getText());
                        writer.flush();
                    }
                    catch(IOException ex) {
                        JOptionPane.showMessageDialog(SixWords.this, ex.getMessage(), "File writing error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        saveAs.addActionListener(new ActionListener() { // cобытие нажатия на кнопку "сохранить как"
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выберите текстовый файл");
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
                fileChooser.addChoosableFileFilter(filter);

                try {
                    fileChooser.showSaveDialog(SixWords.this); // выводим окно выбора файла

                    current = fileChooser.getSelectedFile();

                    try(FileWriter writer = new FileWriter(current, false)) {
                        setTitle("Six Words - " + current.getName());
                        writer.write(jTextArea.getText());
                        writer.flush();
                    }
                    catch(IOException ex) {
                        JOptionPane.showMessageDialog(SixWords.this, ex.getMessage(), "File writing error", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NullPointerException ex) {}; // ничего не делаем в случае ошибки
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

