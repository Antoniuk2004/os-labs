package org.example;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GUI {
    public void showGUI(CommandManager commandManager, String path) throws IOException {
        JFrame jFrame = new JFrame();

        JTextField jTextField = new JTextField(30);

        Font font = new Font("Arial", Font.PLAIN, 30);
        jTextField.setFont(font);

        jFrame.setUndecorated(true);

        String commandsPath = path + "/commands.json";
        commandManager.readData(commandsPath);
        List<String> listOfCommands = commandManager.getCommands();

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList list = createList(listOfCommands, listModel, jFrame, commandManager);

        JScrollPane scrollPane = new JScrollPane(list);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(jTextField);
        panel.add(scrollPane);

        jFrame.getContentPane().add(panel);
        jFrame.pack();
        jFrame.setVisible(true);

        scrollPane.setVisible(false);

        centerFrameOnScreen(jFrame);
        changeFrameSize(jFrame, 1);
        handleKeyPress(jTextField, listOfCommands, listModel, scrollPane, jFrame, list);
    }
    private void changeFrameSize(JFrame jFrame, int numberOfRows){
        int width = jFrame.getWidth();

        if(numberOfRows == 1){
            jFrame.setSize(width, 30);
        }
        else{
            jFrame.setSize(width, 30 + numberOfRows * 20);
        }
    }
    private JList createList(List<String> listOfCommands, DefaultListModel<String> listModel, JFrame jFrame, CommandManager commandManager){
        listModel.addAll(listOfCommands);

        Font font = new Font("Arial", Font.PLAIN, 20);

        JList list = new JList(listModel);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setFont(font);
        list.setVisibleRowCount(7);

        list.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String val = list.getSelectedValue().toString();
                    try {
                        commandManager.executeCommand(val);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    jFrame.dispose();
                }
            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    String val = list.getSelectedValue().toString();
                    try {
                        commandManager.executeCommand(val);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    jFrame.dispose();
                }
            }
        });

        return list;
    }

    private void changeModelValues(DefaultListModel<String> listModel, String text, List<String> listOfCommands){
        List<String> selectedCommands = new ArrayList<>();

        for (String element : listOfCommands) {
            if(element.toLowerCase().contains(text.toLowerCase())){
                selectedCommands.add(element);
            }
        }

        listModel.removeAllElements();
        listModel.addAll(selectedCommands);
    }

    private void handleKeyPress(JTextField jTextField, List<String> listOfCommands, DefaultListModel<String> listModel, JScrollPane scrollPane, JFrame jFrame, JList list) {
        jTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    list.setSelectedIndex(0);
                    list.requestFocusInWindow();
                }

                String text = jTextField.getText();
                changeModelValues(listModel, text, listOfCommands);

                if(text.equals("")){
                    scrollPane.setVisible(false);
                    changeFrameSize(jFrame, 1);
                }
                else {
                    scrollPane.setVisible(true);
                    changeFrameSize(jFrame, 7);
                }
            }
        });
    }

    private void centerFrameOnScreen(JFrame jFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = jFrame.getWidth();
        int frameHeight = jFrame.getHeight();

        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

        jFrame.setLocation(x, y);
    }
}