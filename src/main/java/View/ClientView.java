package View;

import services.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class ClientView {

    private Service service;
    private JFrame frame = new JFrame("Translater");
    private JPanel panel = new JPanel();
//    private JPanel panel2 = new JPanel();
    private JTextField wordFieldInput = new JTextField("", 30);
    private JTextField scryptFieldInput = new JTextField("[ ]", 29);
    private JComboBox levelComboBox = new JComboBox();
    private JTextArea translateField = new JTextArea();
    private JScrollPane scrollTranslateField =  new JScrollPane(translateField);

    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JLabel label3 = new JLabel();
    private JLabel label4 = new JLabel();

    private JButton addToVocabularyButton = new JButton("Add to Vocabulary");
    private JButton translateButton = new JButton("Translate");
    private JButton deleteFromVocabularyButton = new JButton("Delete from Vocabulary");
    private JButton selectFromVocabularyButtonByLevel = new JButton("Select by level");

    private JMenuBar menuBar = new JMenuBar();
    private JMenu levelMenu = new JMenu("Выбери слова по уровню");
    private JMenuItem levelMenu1 = new JMenuItem("<= 1 level");
    private JMenuItem levelMenu2 = new JMenuItem("<= 2 level");
    private JMenuItem levelMenu3 = new JMenuItem("<= 3 level");
    private JMenuItem levelMenu4 = new JMenuItem("<= 4 level");
    private JMenuItem levelMenu5 = new JMenuItem("<= 5 level");

    private ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButtonMenuItem radioTranslate = new JRadioButtonMenuItem("Перевести", true);
    JRadioButtonMenuItem radioAddToVocab = new JRadioButtonMenuItem("Добавить в словарь");

    private final int[] dataLevel = { 1,2,3,4,5};

    public ClientView(Service service) {
        this.service = service;
        initView();
    }

    private void initView() {
        frame.setResizable(false);
        panel.setBackground(Color.CYAN);

        // Определение менеджера расположения *******************************************
        GroupLayout layout3 = new GroupLayout(panel);
        panel.setLayout(layout3);
        layout3.setAutoCreateGaps(true);
        layout3.setAutoCreateContainerGaps(true);
        // Создание горизонтальной группы
        layout3.setHorizontalGroup(layout3.createSequentialGroup()
                .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(label1)
                                        .addComponent(label2)
                                        .addComponent(label3)
                                        .addComponent(label4)
                                        .addComponent(translateButton)
                                        .addComponent(deleteFromVocabularyButton)
                                        .addComponent(addToVocabularyButton))
                                .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(wordFieldInput)
                                        .addComponent(scryptFieldInput)
                                        .addComponent(levelComboBox)
                                        .addComponent(radioTranslate)
                                        .addComponent(scrollTranslateField)
                                        .addComponent(radioAddToVocab))

        );

//        layout3.linkSize(SwingConstants.HORIZONTAL, scrollTranslateField);
        // Создание вертикальной группы
        layout3.setVerticalGroup(layout3.createSequentialGroup()
                .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(wordFieldInput))
                .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout3.createSequentialGroup()
                                .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2)
                                        .addComponent(scryptFieldInput))
                                .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3)
                                        .addComponent(levelComboBox))
                                .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label4)
                                        .addComponent(radioTranslate))
                                .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(translateButton)
                                        .addComponent(addToVocabularyButton)
                                        .addComponent(radioAddToVocab))
                                .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(deleteFromVocabularyButton)
                                        .addComponent(scrollTranslateField))))
        );
//******************************************************************************
        for (int item : dataLevel) {  levelComboBox.addItem(item);  } // заполняем список с уровнями

        label1.setText("Введите English слово");
        label1.setFont(new Font("Arial!",Font.ITALIC,14));
        label1.setForeground(Color.blue);
        label2.setText("Введите транскрипцию");
        label2.setFont(new Font("Arial!",Font.ITALIC,14));
        label3.setText("Введите уровень сложности");
        label3.setFont(new Font("Arial!",Font.ITALIC,14));
        label4.setText("Введите переводы слова, через ENTER");
        label4.setFont(new Font("Arial!",Font.ITALIC,14));

        wordFieldInput.setEditable(true);
        scryptFieldInput.setEditable(true);
        translateField.setEditable(true);
        levelComboBox.setEnabled(true);
        levelComboBox.setFont(new Font("Arial Black",Font.ITALIC,10));
        levelComboBox.setMaximumSize(new Dimension(1,1));

        translateField.setRows(8);
        translateField.setColumns(35);

        translateButton.setBackground(Color.green);
        translateButton.setForeground(Color.BLUE);
        addToVocabularyButton.setBackground(Color.green);
        addToVocabularyButton.setForeground(Color.BLUE);
        deleteFromVocabularyButton.setBackground(Color.RED);
        deleteFromVocabularyButton.setForeground(Color.WHITE);

        radioTranslate.setFont(new Font("Arial!",Font.ITALIC,13));
        radioTranslate.setMaximumSize(new Dimension(150,20));
        radioTranslate.setBackground(Color.CYAN);
        radioAddToVocab.setFont(new Font("Arial!",Font.ITALIC,13));
        radioAddToVocab.setMaximumSize(new Dimension(150,20));
        radioAddToVocab.setBackground(Color.CYAN);
        buttonGroup.add(radioTranslate);// объединение в 1 группу
        buttonGroup.add(radioAddToVocab);// объединение в 1 группу
        radioTranslate.setSelected(true);
        setRadioTranslate();

        levelMenu.add(levelMenu1);
        levelMenu.add(levelMenu2);
        levelMenu.add(levelMenu3);
        levelMenu.add(levelMenu4);
        levelMenu.add(levelMenu5);
        levelMenu.setMnemonic(KeyEvent.VK_L);

        menuBar.add(levelMenu);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // установить по центру
        frame.setVisible(true);

        radioTranslate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                translateField.setText("");
                setRadioTranslate();
            }
        });

        radioAddToVocab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setRadioAddToVocab();
            }
        });

        addToVocabularyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String engWord =  wordFieldInput.getText();
                String script = scryptFieldInput.getText();
                int level = (int) levelComboBox.getSelectedItem();
                String translate = translateField.getText();
                String[] setTranlate;
                Set<String> set = new TreeSet<>();
                if (service.isExist(engWord)) {
                    infoMessage( "\"" + engWord + "\" -слово уже есть в словаре");
//                    radioTranslate.setSelected(true);
                    clearFormWithoutWord();
                } else {

                    if ( !engWord.isEmpty() && (!script.isEmpty()) && (!translate.isEmpty()) ) {
                        setTranlate = translate.split("\n");
                        set.addAll(Arrays.asList(setTranlate));
                        System.out.printf("Создание полноценного обьекта %s с транскрипцией %s уровнем %d переводы: \n"
                                ,engWord, script,level);

                        service.inputData(engWord,script,level,set);
                        infoMessage("Слово добавлено");
                        clearForm();
                    } else {
                        if ( !engWord.isEmpty() && (!translate.isEmpty()) ) {
                            setTranlate = translate.split("\n");
                            set.addAll(Arrays.asList(setTranlate));
                            System.out.println("Создание не полного обьекта");
                            service.inputData(engWord,level, set);
                            infoMessage("Слово добавлено");
                            clearForm();
                        } else infoMessage("Обязательно введите Слово и Перевод");
                    }
                }
            }
        });

        translateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String engWord =  wordFieldInput.getText();
                translateField.setText("");
                if (!service.isExist(engWord)) {
                    infoMessage( "\"" + engWord + "\" -слова нет в словаре.\n Выберите \"Добавить\"");
                    clearFormWithoutWord();
                } else {
                    translateField.setText(service.getTranstate(engWord));
                }
            }
        });

        deleteFromVocabularyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String engWord =  wordFieldInput.getText();
                clearFormWithoutWord();
                if (!service.isExist(engWord) || (engWord.isEmpty())) {
                    infoMessage( "\"" + engWord + "\" -слова нет в словаре");
                } else {
                       int choise = confirmationWindow("Уверены, что удалить слово?");
                       if (choise == 0) {
                           service.delete(engWord);
                           clearForm();
                       }
                }
            }
        });

        levelMenu1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (levelMenu1.isArmed()) {
                    wordFieldInput.setText("");
                    translateField.setText("выбран 1 уровень:");
                    translateField.setText(service.getEngWordsByLevel(1));

                }
            }
        });

        levelMenu2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (levelMenu2.isArmed()) {
                    wordFieldInput.setText("");
                    translateField.setText(service.getEngWordsByLevel(2));
                }
            }
        });

        levelMenu3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (levelMenu3.isArmed()) {
                    wordFieldInput.setText("");
                    translateField.setText(service.getEngWordsByLevel(3));
                }
            }
        });

        levelMenu4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (levelMenu4.isArmed()) {
                    wordFieldInput.setText("");
                    translateField.setText(service.getEngWordsByLevel(4));
                }
            }
        });

        levelMenu5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (levelMenu5.isArmed()) {
                    wordFieldInput.setText("");
                    translateField.setText(service.getEngWordsByLevel(5));
                }
            }
        });

    }
     private void setRadioTranslate(){
         translateButton.setVisible(true);
         scryptFieldInput.setVisible(false);
         levelComboBox.setVisible(false);
         translateField.setVisible(true);

         label2.setForeground(Color.CYAN);
         label3.setForeground(Color.CYAN);
         label4.setForeground(Color.CYAN);
         addToVocabularyButton.setVisible(false);
         deleteFromVocabularyButton.setVisible(true);
         selectFromVocabularyButtonByLevel.setVisible(false);
         translateButton.setFocusable(true);
     }

    private void setRadioAddToVocab(){
        translateField.setText("");
        translateButton.setVisible(false);
        scryptFieldInput.setVisible(true);
        levelComboBox.setVisible(true);
        translateField.setVisible(true);
//        label2.setVisible(true);
        label2.setForeground(Color.blue);
        label3.setVisible(true);
        label3.setForeground(Color.blue);
        label4.setVisible(true);
        label4.setForeground(Color.blue);
        addToVocabularyButton.setVisible(true);
        deleteFromVocabularyButton.setVisible(true);
        selectFromVocabularyButtonByLevel.setVisible(false);
        addToVocabularyButton.setFocusable(true);
    }

    public void infoMessage(String str) {
         JOptionPane.showMessageDialog(
                frame,
                str,
                "Внимание!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public int confirmationWindow(String str) {
        return JOptionPane.showConfirmDialog(
                frame,
                str,
                "Внимание!",
                JOptionPane.YES_NO_OPTION);
    }

    private void clearForm(){
        wordFieldInput.setText("");
        scryptFieldInput.setText("[ ]");
        levelComboBox.setSelectedItem(1);
        translateField.setText("");
    }

    private void clearFormWithoutWord(){
        scryptFieldInput.setText("[ ]");
        levelComboBox.setSelectedItem(1);
        translateField.setText("");
    }

}
