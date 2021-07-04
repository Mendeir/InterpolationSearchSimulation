import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class InputWindow extends JFrame implements ActionListener {

    // Instance variable declarations
    String [] sizes = {"1","2","3","4","5","6","7","8","9","10"};
    String usersInput;
    String [] randomValues;
    boolean r = false;
    String [] values;
    int num;
    static int arrayCounter = 0;

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
        panelMenu.setBackground(Color.white);
        panelNumbers.setBounds(0,60,1000,185);
        panelNumbers.setBackground(Color.PINK);
        panelNumbers.setLayout(null);
        panelArrows.setBounds(0,185,1000,150);
        panelArrows.setBackground(Color.GRAY);
        panelFormula.setBounds(0,350,400,250);
        panelComputation.setBounds(400,350,600,250);
        panelComputation.setLayout(null);
        panelFormula.setBackground(Color.WHITE);
        panelComputation.setBackground(Color.DARK_GRAY);

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
        frame.add(panelMenu);
        frame.add(panelNumbers);
        frame.add(panelArrows);
        frame.add(panelFormula);
        frame.add(panelComputation);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == size){
            // Get the users desired size
            num = Integer.parseInt((String) size.getSelectedItem());
            randomValues = new String[num];
            setNum(num);

        }

        if(e.getSource() == random){
            r = true;
            for(int i=0;i<getNum();i++){
                Random rand = new Random();
                randomValues[i] = Integer.toString(rand.nextInt(15));
            }
            inputNumbers.setText(Arrays.toString(randomValues));

        }

        if(e.getSource() == enter){
            values = new String[getNum()];

            if(r){
                setValues(randomValues);
            }else {
                usersInput = inputNumbers.getText();
                setValues(usersInput.split(" "));
            }

            for(int i=0;i<getNum();i++){
                labelValues = new JLabel(values[i],JLabel.CENTER);
                labelValues.setBounds(i*50,120,50,50);
                labelValues.setBorder(BorderFactory.createLineBorder(Color.red));
                panelNumbers.add(labelValues);
            }


        }

        if(e.getSource() == right){
            panelNumbers.removeAll();
            panelNumbers.revalidate();
            panelComputation.removeAll();
            panelComputation.revalidate();
            frame.repaint();

            arrayCounter++;

        }

        if(e.getSource() == left){
            panelNumbers.removeAll();
            panelNumbers.revalidate();
            panelComputation.removeAll();
            panelComputation.revalidate();
            frame.repaint();

            arrayCounter--;
        }
    }

    // nextButton displays
    public void nextButton(){
        switch (arrayCounter){
            case 0:
                // first next
                break;

            case 1:
                // next
                break;

            case 2:
                // last next
                break;
        }
    }

    // Setters and Getters
    public void setNum(int n){
        this.num = n;
    }

    public int getNum(){
        return this.num;
    }

    public void setValues(String []v){
        this.values = v;
    }

    public String[] getValues(){
        return this.values;
    }


}
