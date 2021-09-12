package me.soeren;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame {

    public static void main(String[] args){

        System.out.println("TEST");
        JFrame menu = new JFrame("XML Creator");
        BorderLayout layout = new BorderLayout();
        JButton exit = new JButton("exit");
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
        menu.add(exit, BorderLayout.PAGE_END);

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

        //endregion
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
            }
        });
    }
}
