// Created by twice on 06.10.16.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class QuickCardBuilder {
  private JTextArea question;
  private JTextArea answer;
  private ArrayList<QuickCard> cardList;
  private JFrame frame;

  public static void main(String[] args) {
    QuickCardBuilder builder = new QuickCardBuilder();
    builder.go();
  }


  public void go(){
    cardList  =new ArrayList<QuickCard>();

    frame = new JFrame("QuickCardBuilder");
    JPanel panel = new JPanel();
    Font bigFont = new Font("sanserif",Font.BOLD,24);

    question = new JTextArea(6,20);
    question.setLineWrap(true);
    question.setFont(bigFont);

    JScrollPane qScroller = new JScrollPane(question);
    qScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    qScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    answer = new JTextArea(6,20);
    answer.setLineWrap(true);
    answer.setFont(bigFont);

    JScrollPane aScroller = new JScrollPane(answer);
    aScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    aScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    JButton nextButton = new JButton("Следующий");
    JLabel qLabel = new JLabel("Введите вопрос");
    JLabel aLabel = new JLabel("ОТВЕТ");

    panel.add(qLabel);
    panel.add(qScroller);
    panel.add(aLabel);
    panel.add(aScroller);
    panel.add(nextButton);

    nextButton.addActionListener(new NextCardListener());

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu  = new JMenu("File");
    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem saveMenuItem = new JMenuItem("Save");

    newMenuItem.addActionListener(new NewMenuListener());
    saveMenuItem.addActionListener(new SaveMenuListener());

    fileMenu.add(newMenuItem);
    fileMenu.add(saveMenuItem);
    menuBar.add(fileMenu);

    frame.setJMenuBar(menuBar);
    frame.getContentPane().add(BorderLayout.CENTER,panel);
    frame.setSize(550,600);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  private class NextCardListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      QuickCard card = new QuickCard(question.getText(),answer.getText());
      cardList.add(card);
      clearCard();

    }
  }
  private class SaveMenuListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      QuickCard card = new QuickCard(question.getText(),answer.getText());
      cardList.add(card);

      JFileChooser fileSave = new JFileChooser();
      fileSave.showSaveDialog(frame);
      saveFile(fileSave.getSelectedFile());

    }
  }
  private class NewMenuListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        cardList.clear();
    }
  }

  private void clearCard(){
    question.setText("");
    answer.setText("");
    question.requestFocus();
  }

  private void saveFile(File file){
    try{
      BufferedWriter writer= new BufferedWriter(new FileWriter(file));
      for(QuickCard card:cardList){
        writer.write(card.getQuestion()+"/");
        writer.write(card.getAnswer() +"\n");
      }
      writer.close();
    }catch(IOException e){
      System.out.printf("не записал");
    }
  }
}
