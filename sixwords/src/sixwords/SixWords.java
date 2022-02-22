package sixwords;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
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

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.X_AXIS));
        jPanel.add(jPanel1, BorderLayout.NORTH);

        JTextArea jTextArea1 = new JTextArea();
        jTextArea1.setText(Integer.toString(jTextArea.getFont().getSize()));
        jTextArea1.setFont(new Font(jTextArea1.getFont().getName(), jTextArea1.getFont().getStyle(), 20));
        jPanel1.add(jTextArea1);

        JButton jButton = new JButton("B");
        jPanel1.add(jButton);
        jButton.setForeground(Color.BLACK);

        JButton jButton1 = new JButton("I");
        jPanel1.add(jButton1);
        jButton1.setForeground(Color.BLACK);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jButton.getForeground() == Color.BLACK) {
                    jButton.setForeground(Color.BLUE);
                    jTextArea.setFont(new Font(jTextArea.getFont().getName(), Font.BOLD, jTextArea.getFont().getSize()));
                    if(jButton1.getForeground() == Color.BLUE) {
                        jTextArea.setFont(new Font(jTextArea.getFont().getName(), 3, jTextArea.getFont().getSize()));
                    }
                } else {
                    jButton.setForeground(Color.BLACK);
                    jTextArea.setFont(new Font(jTextArea.getFont().getName(), Font.PLAIN, jTextArea.getFont().getSize()));
                    if(jButton1.getForeground() == Color.BLUE) {
                        jTextArea.setFont(new Font(jTextArea.getFont().getName(), Font.ITALIC, jTextArea.getFont().getSize()));
                    }
                }
            }
        });

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jButton1.getForeground() == Color.BLACK) {
                    jButton1.setForeground(Color.BLUE);
                    jTextArea.setFont(new Font(jTextArea.getFont().getName(), Font.ITALIC, jTextArea.getFont().getSize()));
                    if(jButton.getForeground() == Color.BLUE) {
                        jTextArea.setFont(new Font(jTextArea.getFont().getName(), 3, jTextArea.getFont().getSize()));
                    }
                } else {
                    jButton1.setForeground(Color.BLACK);
                    jTextArea.setFont(new Font(jTextArea.getFont().getName(), Font.PLAIN, jTextArea.getFont().getSize()));
                    if(jButton.getForeground() == Color.BLUE) {
                        jTextArea.setFont(new Font(jTextArea.getFont().getName(), Font.BOLD, jTextArea.getFont().getSize()));
                    }
                }
            }
        });

        PlainDocument numbers = (PlainDocument)jTextArea1.getDocument(); // фильтр для jTextArea1, чтобы можно было вводить только числа
        numbers.setDocumentFilter(new DocumentFilter() {
            private static final String DIGITS = "\\d+";

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {

                if (string.matches(DIGITS)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
                if (string.matches(DIGITS)) {
                    super.replace(fb, offset, length, string, attrs);
                }
            }
        });

        jTextArea1.getDocument().addDocumentListener(new DocumentListener() { // отслеживаем изменения в jTextArea1
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(!jTextArea1.getText().isEmpty()) {
                    jTextArea.setFont(new Font(jTextArea.getFont().getName(), jTextArea.getFont().getStyle(), Integer.parseInt(jTextArea1.getText())));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(!jTextArea1.getText().isEmpty()) {
                    jTextArea.setFont(new Font(jTextArea.getFont().getName(), jTextArea.getFont().getStyle(), Integer.parseInt(jTextArea1.getText())));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

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
