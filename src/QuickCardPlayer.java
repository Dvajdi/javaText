// Created by twice on 06.10.16.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuickCardPlayer implements CardPlayerInterface{
  private JTextArea display;
  private JTextArea answer;
  private ArrayList<QuickCard> cardList;
  private QuickCard currentCard;
  private int currentCardIndex;
  private JFrame frame;
  private JButton nextButton;
  private boolean isShowAnswer;

  public static void main(String[] args) {
    new QuickCardPlayer().go();
  }

  @Override
  public void go() {
    frame = new JFrame("QuickCardPlayer");
    JPanel panel  =new JPanel();
    Font bigFont = new Font("sanserif",Font.BOLD,24);

    display = new JTextArea(10,20);
    display.setFont(bigFont);
    display.setLineWrap(true);
    display.setEditable(false);

    JScrollPane qScroller =  new JScrollPane(display);
    qScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    qScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    nextButton = new JButton("Show question");
    panel.add(qScroller);
    panel.add(nextButton);
    nextButton.addActionListener(new NextCardListener());

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem loadMenuItem = new JMenuItem("loadCard");
    loadMenuItem.addActionListener(new OpenMenuListener());
    menu.add(loadMenuItem);
    menuBar.add(menu);
    frame.setJMenuBar(menuBar);
    frame.getContentPane().add(BorderLayout.CENTER,panel);
    frame.setSize(550,600);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  @Override
  public void LoadFile(File file) {
    cardList = new ArrayList<>();
    try{
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line =null;
      while ((line=reader.readLine())!=null){
        makeCard(line);
      }
      reader.close();
    }catch (IOException e){}
    showNextCard();

  }

  @Override
  public void makeCard(String lineToPars) {
    String [] result = lineToPars.split("/");
    QuickCard card = new QuickCard(result[0],result[1]);
    cardList.add(card);
    System.out.println("карточки готовы");
  }

  private void showNextCard(){
    currentCard = cardList.get(currentCardIndex);
    currentCardIndex++;
    display.setText(currentCard.getQuestion());
    nextButton.setText("show Answer");
    isShowAnswer = true;
  }

  class NextCardListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

    }
  }

  class OpenMenuListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {

    }
  }
}
