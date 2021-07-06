import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;

public class InputWindow extends JFrame implements ActionListener {

    // Calling the Logic class for logic process
    Logic logic = new Logic();

    // Instance variable declarations
    String [] sizes = {"1","2","3","4","5","6","7","8","9","10"};
    String usersInput;
    String [] usersNumbers;
    ArrayList randomValues = new ArrayList<Integer>();
    boolean r = false;
    ArrayList values = new ArrayList<Integer>();
    ArrayList<Integer> displayLow;
    ArrayList<Integer> displayHigh;
    ArrayList<Integer> displayPosition;
    int num;
    int key;
    int found;
    public static int randomCounter = 0;
    public static int arrayCounter = -1;
    public static boolean status = false;

    // JText area variables
    String indexLow;
    String indexHigh;
    String indexPos ;
    String valueLow;
    String valueHigh;
    String valueKey;


    // Calling PanelNumbers class for displaying the whole process
    //PanelNumbers panelNumbers = new PanelNumbers();

    // Instantiation of objects for GUI
    JFrame frame = new JFrame("Interpolation Search");
    JPanel panelMenu = new JPanel();
    JPanel panelFormula = new JPanel();
    JPanel panelComputation = new JPanel();
    JPanel panelNumbers = new JPanel();
    JPanel panelArrows = new JPanel();
    JLabel labelValues;
    JLabel labelIndex;
    JLabel labelLow = new JLabel("Low",JLabel.CENTER);
    JLabel labelHigh = new JLabel("High",JLabel.CENTER);
    JLabel labelPosition = new JLabel("Position",JLabel.CENTER);
    JComboBox size = new JComboBox(sizes);
    JTextField inputNumbers = new JTextField("Enter Values");
    JTextField inputKey = new JTextField("Key");
    JButton random = new JButton("Random");
    JButton enter = new JButton("Enter");
    JButton left = new JButton("<");
    JButton right = new JButton(">");
    ImageIcon formula = new ImageIcon(Objects.requireNonNull(getClass().getResource("formulaT.png")));
    JLabel imageFormula = new JLabel(formula);
    JTextArea areaComputation = new JTextArea("Computation");


    // Constructor
    InputWindow(){

        // Frame format and sizes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.setLayout(null);

        // Panel format and sizes
        panelMenu.setBounds(0,0,1000,60);
        panelMenu.setLayout(null);
        //panelMenu.setBackground(Color.white);
        panelNumbers.setBounds(0,60,1000,185);
        panelNumbers.setBackground(Color.WHITE);
        panelNumbers.setLayout(null);
        panelArrows.setBounds(0,185,1000,150);
        panelArrows.setBackground(Color.WHITE);
        panelArrows.setLayout(null);
        panelFormula.setBounds(0,350,400,250);
        panelComputation.setBounds(400,350,600,250);
        panelComputation.setLayout(null);
        //panelFormula.setBackground(Color.WHITE);
        //panelComputation.setBackground(Color.DARK_GRAY);

        // Combo Box bounds and size
        size.setBounds(20,10,90,30);
        size.addActionListener(this);


        // Buttons bounds and sizes
        random.setBounds(120,10,100,30);
        enter.setBounds(580,10,80,30);
        left.setBounds(670,10,60,30);
        right.setBounds(750,10,60,30);
        random.addActionListener(this);
        enter.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);

        // Text Field sizes
        inputNumbers.setBounds(230,10,250,30);
        inputKey.setBounds(490,10,50,30);

        // Image Formula size and bounds
        imageFormula.setBounds(0,10,400,300);

        // TextArea format
        areaComputation.setBounds(30,0,500,200);
        areaComputation.setLineWrap(true);
        areaComputation.setWrapStyleWord(true);



        // frame visibility and components
        panelMenu.add(size);
        panelMenu.add(random);
        panelMenu.add(inputNumbers);
        panelMenu.add(inputKey);
        panelMenu.add(enter);
        panelMenu.add(left);
        panelMenu.add(right);
        panelComputation.add(areaComputation);
        panelFormula.add(imageFormula);
        panelArrows.add(labelLow);
        panelArrows.add(labelHigh);
        panelArrows.add(labelPosition);
        frame.add(panelMenu);
        frame.add(panelNumbers);
        frame.add(panelArrows);
        frame.add(panelFormula);
        frame.add(panelComputation);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        // Action for size combo box button
        if(e.getSource() == size){
            // Get the users desired size
            num = Integer.parseInt((String) size.getSelectedItem());
            setNum(num);
            usersNumbers = new String[getNum()];

            areaComputation.setText("pos" + " = " + "low" + "+ ((" + "k" + "-" + " - " + "lowArr" + ") * (" + "high" + " - " + "low + ) / (" + "highArr" + " - " + "lowArr" + "))" + "\n=" + "pos");
            areaComputation.setBounds(30,0,500,200);
            areaComputation.setLineWrap(true);
            areaComputation.setWrapStyleWord(true);
            panelComputation.add(areaComputation);
        }

        // Actions for Random Button
        if(e.getSource() == random){
            if(randomCounter != 0)
                randomValues.clear();

            r = true;
            String displayRandom;
            for(int i=0;i<getNum();i++){
                Random rand = new Random();
                randomValues.add(rand.nextInt(100));
            }
            Collections.sort(randomValues);
            displayRandom = randomValues.toString();
            inputNumbers.setText(displayRandom);

            randomCounter++;
        }

        // Action for enter button
        if(e.getSource() == enter){
            try {
                key = Integer.parseInt(inputKey.getText());
                if (r) {
                    displayRandomGiven();
                    found = logic.interpolationSearch(randomValues, key);
                } else {
                    usersInput = inputNumbers.getText();
                    usersNumbers = usersInput.split(" ");
                    for (int i = 0; i < getNum(); i++) {
                        values.add(Integer.parseInt(usersNumbers[i]));
                    }
                    displayUserGiven();
                    found = logic.interpolationSearch(values, key);
                }
                displayLow = logic.getStoredLowerBound();
                displayHigh = logic.getStoredHigherBound();
                displayPosition = logic.getStoredIndexPosition();
            }catch (NumberFormatException er){
                ErrorWindow error = new ErrorWindow();
            }
        }
        // Action for next button
        if(e.getSource() == right) {
            panelNumbers.removeAll();
            panelNumbers.revalidate();
            panelComputation.removeAll();
            panelComputation.revalidate();
            frame.repaint();
            if(arrayCounter != displayLow.size())
                arrayCounter++;
            if (arrayCounter < displayLow.size()) {
                displayBox(arrayCounter);
            }else {
                if (found == -999999) {
                    if(arrayCounter != 0)
                        displayBox(arrayCounter-1);
                    displayNotFound();
                } else {
                    if(arrayCounter != 0)
                        displayBox(arrayCounter-1);
                    displayAnswer(indexLow,indexHigh,indexPos,valueLow,valueHigh,valueKey);
                }
            }

        }

        // Action for previous button
        if(e.getSource() == left){
            panelNumbers.removeAll();
            panelNumbers.revalidate();
            panelComputation.removeAll();
            panelComputation.revalidate();
            frame.repaint();
            if(arrayCounter != 0)
                arrayCounter--;

                displayBox(arrayCounter);


        }
    }
    // Display users given values for computation
    public void displayUserGiven(){
        panelNumbers.removeAll();
        panelNumbers.revalidate();
        frame.repaint();

        displayColorFormat();
        for (int i = 0; i < getNum(); i++) {
            labelIndex = new JLabel(String.valueOf(i),JLabel.CENTER);
            labelValues = new JLabel(String.valueOf(values.get(i)),JLabel.CENTER);
            labelValues.setBounds(i*50,120,50,50);
            labelIndex.setBounds(i*50,70,50,50);
            labelValues.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panelNumbers.add(labelValues);
            panelNumbers.add(labelIndex);
        }
    }
    // Display random values for computation
    public void displayRandomGiven(){
        panelNumbers.removeAll();
        panelNumbers.revalidate();
        frame.repaint();

        displayColorFormat();
        for (int i = 0; i < getNum(); i++) {
            labelIndex = new JLabel(String.valueOf(i),JLabel.CENTER);
            labelValues = new JLabel(String.valueOf(randomValues.get(i)),JLabel.CENTER);
            labelValues.setBounds(i*50,120,50,50);
            labelIndex.setBounds(i*50,70,50,50);
            labelValues.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            labelValues.setOpaque(true);
            panelNumbers.add(labelValues);
            panelNumbers.add(labelIndex);
        }
    }

    // Change the background for borders of JLabel for indications
    public void displayBox(int counter){
        displayColorFormat();
        if(r) {
            for (int i = 0; i < getNum(); i++) {

                labelValues = new JLabel(String.valueOf(randomValues.get(i)),JLabel.CENTER);
                labelValues.setBounds(i*50,120,50,50);
                labelIndex = new JLabel(String.valueOf(i),JLabel.CENTER);
                labelIndex.setBounds(i*50,70,50,50);
                panelNumbers.add(labelIndex);

                if(i == displayLow.get(counter))
                    labelValues.setBackground(Color.YELLOW);

                if(i == displayHigh.get(counter))
                    labelValues.setBackground(Color.BLUE);

                if(i == displayPosition.get(counter))
                    labelValues.setBackground(Color.GREEN);

                labelValues.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labelValues.setOpaque(true);
                panelNumbers.add(labelValues);
            }
            indexLow = String.valueOf(displayLow.get(counter));
            indexHigh = String.valueOf(displayHigh.get(counter));
            indexPos = String.valueOf(displayPosition.get(counter));
            valueLow =  String.valueOf(randomValues.get(displayLow.get(counter)));
            valueHigh = String.valueOf(randomValues.get(displayHigh.get(counter)));
            valueKey = inputKey.getText();

            displayArea(indexLow,indexHigh,indexPos,valueLow,valueHigh,valueKey);
        }else{

            for (int i = 0; i < getNum(); i++) {

                labelValues = new JLabel(String.valueOf(values.get(i)),JLabel.CENTER);
                labelValues.setBounds(i*50,120,50,50);
                labelIndex = new JLabel(String.valueOf(i),JLabel.CENTER);
                labelIndex.setBounds(i*50,70,50,50);
                panelNumbers.add(labelIndex);

                if(i == displayLow.get(counter))
                    labelValues.setBackground(Color.YELLOW);

                if(i == displayHigh.get(counter))
                    labelValues.setBackground(Color.BLUE);

                if(i == displayPosition.get(counter))
                    labelValues.setBackground(Color.GREEN);

                labelValues.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labelValues.setOpaque(true);
                panelNumbers.add(labelValues);
            }
            indexLow = String.valueOf(displayLow.get(counter));
            indexHigh = String.valueOf(displayHigh.get(counter));
            indexPos = String.valueOf(displayPosition.get(counter));
            valueLow =  String.valueOf(values.get(displayLow.get(counter)));
            valueHigh = String.valueOf(values.get(displayHigh.get(counter)));
            valueKey = inputKey.getText();

            displayArea(indexLow,indexHigh,indexPos,valueLow,valueHigh,valueKey);
        }

    }

    // Display the content computation inside the text area
    public void displayArea(String low, String high, String pos, String lowArr, String highArr, String k){
        panelComputation.removeAll();
        panelComputation.revalidate();
        frame.repaint();


        areaComputation.setText("pos" + " = " + low + "+ ((" + k + "-"+ lowArr + ") * (" + high + " - " + low + ") / (" + highArr + " - " + lowArr + "))" + "\n=" + pos + "\n\n" + "low = " +low + "\nhigh = " + high );
        areaComputation.setBounds(30,0,500,200);
        areaComputation.setLineWrap(true);
        areaComputation.setWrapStyleWord(true);
        panelComputation.add(areaComputation);
    }

    // Method for Color backgrounds
    public void displayColorFormat(){
        labelLow.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelLow.setBackground(Color.YELLOW);
        labelLow.setOpaque(true);
        labelLow.setBounds(50,10,50,50);
        labelHigh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelHigh.setBackground(Color.BLUE);
        labelHigh.setOpaque(true);
        labelHigh.setBounds(150,10,50,50);
        labelPosition.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelPosition.setBackground(Color.GREEN);
        labelPosition.setOpaque(true);
        labelPosition.setBounds(250,10,50,50);
        panelNumbers.add(labelLow);
        panelNumbers.add(labelHigh);
        panelNumbers.add(labelPosition);
    }

    // Display the final answer and its index
    public void displayAnswer(String low, String high, String pos, String lowArr, String highArr, String k){
        panelComputation.removeAll();
        panelComputation.revalidate();
        frame.repaint();

        areaComputation.setText("pos" + " = " + low + "+ ((" + k + "-"+ lowArr + ") * (" + high + " - " + low + ") / (" + highArr + " - " + lowArr + "))" + "\n=" + pos + "\n" + "Found at index " + pos);
        areaComputation.setBounds(30,0,500,200);
        areaComputation.setLineWrap(true);
        areaComputation.setWrapStyleWord(true);
        panelComputation.add(areaComputation);
    }

    // Display Not found if the input key is not inside the array
    public void displayNotFound(){
        panelComputation.removeAll();
        panelComputation.revalidate();
        frame.repaint();

        areaComputation.setText("Key not found inside the array!!");
        areaComputation.setBounds(30,0,500,200);
        areaComputation.setLineWrap(true);
        areaComputation.setWrapStyleWord(true);
        panelComputation.add(areaComputation);
    }

    // Setters and Getters
    public void setNum(int n){
        this.num = n;
    }

    public int getNum(){
        return this.num;
    }



}
