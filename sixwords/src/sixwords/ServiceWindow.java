package sixwords;

import javax.swing.*;
import java.awt.*;

// это суперкласс для удобного создания классов с окнами по некоторым шаблонам
public class ServiceWindow {
    private String name = "unkown";
    private boolean isResizable = true;
    private JFrame jFrame = new JFrame();
    private Dimension size = new Dimension(100, 100);

    public ServiceWindow() {

    }

    public void setName(String name) {
        this.name = name;
        jFrame.setTitle("SixWords - " + name);
    }

    public void setResizable(boolean resizable) {
        isResizable = resizable;
        jFrame.setResizable(resizable);
    }

    public void setSize(Dimension size) {
        this.size = size;
        jFrame.setSize(size);
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation((s.width - size.width) / 2, (s.height - size.height) / 2);
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public void hide() {
        jFrame.setVisible(false);
    }

    public void show() {
        jFrame.setVisible(true);
    }
}
