package me.soeren;

import org.w3c.dom.Document;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Frame {

    public static void main(String[] args){
        final Genus[] genus = new Genus[1];
        java.util.List<Substantiv> substantivList = new ArrayList<Substantiv>();
        java.util.List<Verb> verbList = new ArrayList<Verb>();
        java.util.List<Adjektiv> adjektivList = new ArrayList<Adjektiv>();

        System.out.println("TEST");
        JFrame menu = new JFrame("XML Creator");
        BorderLayout layout = new BorderLayout();

        JButton exit = new JButton("exit");
        JButton createFile = new JButton("Datei Erstellen");
        JPanel buttonMainFrame = new JPanel();
        buttonMainFrame.add(exit);
        buttonMainFrame.add(createFile);

        JLabel title = new JLabel("XML Creator");
        JButton createSubstantivBtn = new JButton("Substantiv hinzufügen");
        JButton createVerb = new JButton("Verb hinzufügen");
        JButton createAdjektiv = new JButton("Adjektiv hinzufügen");


        JPanel titleContainer = new JPanel();
        titleContainer.setLayout(new FlowLayout());
        titleContainer.add(title);

        createSubstantivBtn.setSize(200,40);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setLayout(layout);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(3,1,0,20));

        buttons.add(createSubstantivBtn);

        buttons.add(createVerb);
        buttons.add(createAdjektiv);

        menu.add(titleContainer, BorderLayout.PAGE_START);
        menu.add(buttons, BorderLayout.CENTER);
        menu.add(buttonMainFrame, BorderLayout.PAGE_END);


        menu.setSize(500,400);
        menu.setVisible(true);


        //region SubstantivJFrameComponents
        JFrame createSubstantivFrame = new JFrame("Substantiv hinzufügen");

        createSubstantivFrame.setLayout(new GridLayout(5,2,30,20));

        JLabel grundFormLabel = new JLabel("Grundform:");
        JTextField grundFormInput = new JTextField();
        //region Genus Inputs

        JLabel genusLabel = new JLabel("Genus:");
        String[] genusList = {"maskulin", "feminin", "neutrum"};

        JComboBox genusChooser = new JComboBox(genusList);
        //endregion

        JLabel genitivLabel = new JLabel("Genitiv: ");
        JTextField genitivInput = new JTextField();

        JLabel bedeutungLabel = new JLabel("Bedeutung (bei mehreren Bedeutungen mit Semicolon (;) trenen): ");
        JTextField bedeutungInput = new JTextField();

        JButton exitSubstantivMenu = new JButton("Zurück");
        JButton submitSubstantivCreation = new JButton("Eingabe Bestätigen");

        createSubstantivFrame.add(grundFormLabel);
        createSubstantivFrame.add(grundFormInput);

        createSubstantivFrame.add(genusLabel);
        createSubstantivFrame.add(genusChooser);

        createSubstantivFrame.add(genitivLabel);
        createSubstantivFrame.add(genitivInput);

        createSubstantivFrame.add(bedeutungLabel);
        createSubstantivFrame.add(bedeutungInput);

        createSubstantivFrame.add(exitSubstantivMenu);
        createSubstantivFrame.add(submitSubstantivCreation);

        createSubstantivFrame.pack();


        createSubstantivBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSubstantivFrame.setVisible(true);
                menu.setVisible(false);
            }
        });

        exitSubstantivMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSubstantivFrame.setVisible(false);
                menu.setVisible(true);

            }
        });
        submitSubstantivCreation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: add Logic for Substantiv Creation
                createSubstantivFrame.setVisible(false);
                menu.setVisible(true);
                switch (genusChooser.getSelectedIndex()){
                    case 0:
                        genus[0] = Genus.MASKULINUM;
                        break;
                    case 1:
                        genus[0] = Genus.FEMININUM;
                        break;
                    case 2:
                        genus[0] = Genus.NEUTRUM;
                        break;
                }

                System.out.println("---Creating Substantiv---");
                System.out.println("Bedeutung: " + grundFormInput.getText());
                System.out.println("Genus: " + genus[0]);
                System.out.println("Genitiv: " + genitivInput.getText());
                System.out.println("Bedeutung: " + bedeutungInput.getText());
                substantivList.add(new Substantiv(grundFormInput.getText(),
                        genus[0],
                        genitivInput.getText(),
                        bedeutungInput.getText() ));
            }
        });
        //endregion

        //Region VerbJFrameComponents




        //endregion




        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        createFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Working Directory: " + System.getProperty("user.dir"));
                System.out.println("---List of Items---");
                System.out.println("#Substantiv");
                System.out.println(substantivList.size());
                System.out.println("#Verb");
                System.out.println(verbList.size());
                System.out.println("#Adjektiv");
                System.out.println(adjektivList.size());


                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                try {
                    DocumentBuilder db = documentFactory.newDocumentBuilder();

                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
    void CreateXml(){

    }
}
