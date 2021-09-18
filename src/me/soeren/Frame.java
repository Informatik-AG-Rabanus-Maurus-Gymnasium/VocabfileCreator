package me.soeren;

import org.w3c.dom.*;

import javax.swing.*;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Frame {

    public static void main(String[] args){
        final Genus[] genus = new Genus[1];
        java.util.List<Substantiv> substantivList = new ArrayList<>();
        java.util.List<Verb> verbList = new ArrayList<>();
        java.util.List<Adjektiv> adjektivList = new ArrayList<>();

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
        JButton createVerbBtn = new JButton("Verb hinzufügen");
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

        buttons.add(createVerbBtn);
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

        JLabel bedeutungLabel = new JLabel("Bedeutung (bei mehreren Bedeutungen mit Semicolon (;) trenen):: ");

        JTextField bedeutungInput = new JTextField();


        JButton exitSubstantivMenu = new JButton("Zurück");
        JButton addSubstantiv = new JButton("Eingabe Bestätigen");

        createSubstantivFrame.add(grundFormLabel);
        createSubstantivFrame.add(grundFormInput);

        createSubstantivFrame.add(genusLabel);
        createSubstantivFrame.add(genusChooser);

        createSubstantivFrame.add(genitivLabel);
        createSubstantivFrame.add(genitivInput);

        createSubstantivFrame.add(bedeutungLabel);
        createSubstantivFrame.add(bedeutungInput);

        createSubstantivFrame.add(exitSubstantivMenu);
        createSubstantivFrame.add(addSubstantiv);

        createSubstantivFrame.pack();


        createSubstantivBtn.addActionListener(e -> {
            createSubstantivFrame.setVisible(true);
            menu.setVisible(false);
        });

        exitSubstantivMenu.addActionListener(e -> {
            createSubstantivFrame.setVisible(false);
            menu.setVisible(true);

            grundFormInput.setText("");
            genusChooser.setSelectedIndex(0);
            bedeutungInput.setText("");
            genitivInput.setText("");

        });
        addSubstantiv.addActionListener(e -> {

            createSubstantivFrame.setVisible(false);
            menu.setVisible(true);
            switch (genusChooser.getSelectedIndex()) {
                case 0 -> genus[0] = Genus.MASKULINUM;
                case 1 -> genus[0] = Genus.FEMININUM;
                case 2 -> genus[0] = Genus.NEUTRUM;
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
            //clearing Input
            grundFormInput.setText("");
            genusChooser.setSelectedIndex(0);
            bedeutungInput.setText("");
            genitivInput.setText("");

        });
        //endregion

        //Region VerbJFrameComponents
        JFrame verbCreationFrame = new JFrame("Verb erstellen");
        verbCreationFrame.setLayout(new GridLayout(6,2,30,20));

        JLabel verbInfinitiv = new JLabel("Infinitiv: ");
        JTextField verbInfinitivInput = new JTextField();

        JLabel praesensIndikativ = new JLabel("Praesens Indikativ: ");
        JTextField praesensIndikativInput = new JTextField();

        JLabel perfektIndikativ = new JLabel("Perfekt Indikativ: ");
        JTextField perfektIndikativInput = new JTextField();

        JLabel supinstamm = new JLabel("Supinstamm ");
        JTextField supinstammInput = new JTextField();

        JLabel verbBedeutung = new JLabel("Bedeutung (bei mehreren Bedeutungen mit Semicolon (;) trenen): ");
        JTextField verbBedeutungInput = new JTextField();

        JButton exitVerbCreation = new JButton("zurück zum Menü");
        JButton addVerb = new JButton("Eingabe Bestätigen");

        verbCreationFrame.add(verbInfinitiv);
        verbCreationFrame.add(verbInfinitivInput);
        verbCreationFrame.add(praesensIndikativ);
        verbCreationFrame.add(praesensIndikativInput);
        verbCreationFrame.add(perfektIndikativ);
        verbCreationFrame.add(perfektIndikativInput);
        verbCreationFrame.add(supinstamm);
        verbCreationFrame.add(supinstammInput);
        verbCreationFrame.add(verbBedeutung);
        verbCreationFrame.add(verbBedeutungInput);
        verbCreationFrame.add(exitVerbCreation);
        verbCreationFrame.add(addVerb);
        verbCreationFrame.pack();

        createVerbBtn.addActionListener(e -> {
            menu.setVisible(false);
            verbCreationFrame.setVisible(true);
        });

        exitVerbCreation.addActionListener(e -> {
            menu.setVisible(true);
            verbCreationFrame.setVisible(false);

            verbInfinitivInput.setText("");
            praesensIndikativInput.setText("");
            perfektIndikativInput.setText("");
            verbBedeutungInput.setText("");
            supinstammInput.setText("");
        });

        addVerb.addActionListener(e -> {
            menu.setVisible(true);
            verbCreationFrame.setVisible(false);

            System.out.println("---Creating Verb---");
            System.out.println("Bedeutung: " + verbBedeutungInput.getText() );
            System.out.println("Infintiv: " + verbInfinitivInput.getText());
            System.out.println("Praesens Indikativ: " + praesensIndikativInput.getText());
            System.out.println("Perfekt Indikativ: " + perfektIndikativInput.getText());
            System.out.println("Supinstamm: " + supinstammInput.getText());

            verbList.add(new Verb(verbInfinitivInput.getText(),
                    praesensIndikativInput.getText(),
                    perfektIndikativInput.getText(),
                    verbBedeutungInput.getText(),
                    supinstammInput.getText()));

            verbInfinitivInput.setText("");
            praesensIndikativInput.setText("");
            perfektIndikativInput.setText("");
            verbBedeutungInput.setText("");
            supinstammInput.setText("");
        });
        //endregion

        //Region AdjektivJFrameCreation
        JFrame adjektivCreationFrame = new JFrame("Adjektiv hinzufügen");

        JLabel adjektivNominativ = new JLabel("Nominativ:");
        JTextField adjektivNominativInput = new JTextField();

        JLabel genitivMaskulin = new JLabel("Genitiv m.");
        JTextField genitivMaskulinInput = new JTextField();

        JLabel genitivFeminin = new JLabel("Genitiv f. ");
        JTextField genitivFemininInput = new JTextField();

        JLabel genitivNeutrum = new JLabel("Genitiv n. ");
        JTextField genitivNeutrumInput = new JTextField();

        JLabel adjektivBedeutung = new JLabel("Bedeutung: ");
        JTextField adjektivBedeutngInput = new JTextField();

        JButton exitAdjektivCreation = new JButton("zurück zum Menü");
        JButton addAdjektiv = new JButton("Eingabe bestätiger");


        adjektivCreationFrame.add(adjektivNominativ);
        adjektivCreationFrame.add(adjektivNominativInput);
        adjektivCreationFrame.add(genitivMaskulin);
        adjektivCreationFrame.add(genitivMaskulinInput);
        adjektivCreationFrame.add(genitivFeminin);
        adjektivCreationFrame.add(genitivFemininInput);
        adjektivCreationFrame.add(genitivNeutrum);
        adjektivCreationFrame.add(genitivNeutrumInput);
        adjektivCreationFrame.add(adjektivBedeutung );
        adjektivCreationFrame.add(adjektivBedeutngInput);
        adjektivCreationFrame.add(exitAdjektivCreation);
        adjektivCreationFrame.add(addAdjektiv);

        exitAdjektivCreation.addActionListener(e -> {
            adjektivCreationFrame.setVisible(false);
            menu.setVisible(true);

            System.out.println("---Creating Adjektiv---");
            System.out.println("Nominativ: " + adjektivNominativInput.getText());
            System.out.println("Genitiv Maskulin: " + genitivMaskulinInput.getText());
            System.out.println("Genitiv Feminin: " + genitivFeminin.getText());
            System.out.println("Genitiv Neutrum: " + genitivNeutrumInput.getText());
            System.out.println("Bedeutung: " +adjektivBedeutung.getText());

            adjektivList.add(new Adjektiv(
                    adjektivNominativ.getText(),
                    genitivMaskulinInput.getText(),
                    genitivFemininInput.getText(),
                    genitivNeutrumInput.getText(),
                    adjektivBedeutngInput.getText()
            ));

            adjektivNominativInput.setText("");
            genitivMaskulinInput.setText("");
            genitivFemininInput.setText("");
            genitivNeutrumInput.setText("");
            adjektivBedeutngInput.setText("");
        });
        //endregion



        exit.addActionListener(e -> System.exit(0));
        createFile.addActionListener(e -> {
            System.out.println("Working Directory: " + System.getProperty("user.dir"));
            System.out.println("---List of Items---");
            System.out.println("#Substantiv");
            System.out.println(substantivList.size());
            System.out.println("#Verb");
            System.out.println(verbList.size());
            System.out.println("#Adjektiv");
            System.out.println(adjektivList.size());

            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = null;
            try {
                eventWriter = outputFactory
                        .createXMLEventWriter(new FileOutputStream(System.getProperty("user.dir")+ "/vocab.xml"));
            } catch (XMLStreamException ex) {
                ex.printStackTrace();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            try {
                eventWriter.add(startDocument);
            } catch (XMLStreamException ex) {
                ex.printStackTrace();
            }
            StartElement configStartElement = eventFactory.createStartElement("",
                    "", "vocabList");
            try {
                eventWriter.add(configStartElement);
                eventWriter.add(end);
            } catch (XMLStreamException ex) {
                ex.printStackTrace();
            }
            try{
                for (Substantiv s : substantivList){
                    CreateSubstantiv(eventWriter, s);
                }

                for(Verb v : verbList){
                    CreateVerb(eventWriter, v);
                }


                eventWriter.add(eventFactory.createEndElement("", "", "vocabList"));
                eventWriter.add(end);
                eventWriter.add(eventFactory.createEndDocument());
                eventWriter.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }



        });
    }
    private static void CreateAdjektiv(XMLEventWriter eventWriter, Adjektiv a){

    }

    private static void CreateVerb(XMLEventWriter eventWriter, Verb verbToAdd){

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");

        try{
            StartElement sElement = eventFactory.createStartElement("","","vocab");
            eventWriter.add(sElement);
            eventWriter.add(tab);
            StartElement typeStartElement = eventFactory.createStartElement("","","type");
            eventWriter.add(typeStartElement);
            eventWriter.add(eventFactory.createCharacters("verb"));
            EndElement typeEndElement = eventFactory.createEndElement("","","type");
            eventWriter.add(typeEndElement);

            StartElement infinitivStartElement = eventFactory.createStartElement("","","infinitiv");
            eventWriter.add(infinitivStartElement);
            eventWriter.add(eventFactory.createCharacters(verbToAdd.infiniv));
            EndElement infinitivEndElement = eventFactory.createEndElement("","","infinitiv");
            eventWriter.add(infinitivEndElement);

            StartElement praesensIndikativStart = eventFactory.createStartElement("","","praesensIndikativ");
            eventWriter.add(praesensIndikativStart);
            EndElement praesensIndikativEnd = eventFactory.createEndElement("","","praesensIndikativ");
            eventWriter.add(eventFactory.createCharacters(verbToAdd.praesensIndikativ));
            eventWriter.add(praesensIndikativEnd);

            StartElement perfektIndikativStart = eventFactory.createStartElement("","","perfektIndikativ");
            eventWriter.add(perfektIndikativStart);
            EndElement perfektIndikativEnd = eventFactory.createEndElement("","","perfektIndikativ");
            eventWriter.add(eventFactory.createCharacters(verbToAdd.perfektIndikativ));
            eventWriter.add(perfektIndikativEnd);

            StartElement supinstammStart = eventFactory.createStartElement("","","supinstamm");
            eventWriter.add(supinstammStart);
            EndElement supinstammEnd = eventFactory.createEndElement("","","supinstamm");
            eventWriter.add(eventFactory.createCharacters(verbToAdd.supinstamm));
            eventWriter.add(supinstammEnd);

            StartElement bedeutungStart = eventFactory.createStartElement("","","bedeutung");
            eventWriter.add(bedeutungStart);
            EndElement bedeutungEnd = eventFactory.createEndElement("","","bedeutung");
            eventWriter.add(eventFactory.createCharacters(verbToAdd.bedeutung));
            eventWriter.add(bedeutungEnd);

            EndElement eElement = eventFactory.createEndElement("", "", "vocab");
            eventWriter.add(eElement);
            eventWriter.add(end);

        }catch(XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private static void CreateSubstantiv( XMLEventWriter eventWriter, Substantiv substantivToAdd){
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node

        try {
            StartElement sElement = eventFactory.createStartElement("","","vocab");

            eventWriter.add(sElement);
            eventWriter.add(tab);
            StartElement typeStartElement = eventFactory.createStartElement("","","type");
            eventWriter.add(typeStartElement);
            eventWriter.add(eventFactory.createCharacters("Substantiv"));
            EndElement typeEndElement = eventFactory.createEndElement("","","type");
            eventWriter.add(typeEndElement);
            StartElement grundformStartElement = eventFactory.createStartElement("", "","grundform");
            eventWriter.add(grundformStartElement);
            eventWriter.add(eventFactory.createCharacters(substantivToAdd.grundform));
            EndElement grundformEndElement = eventFactory.createEndElement("", "","grundform");
            eventWriter.add(grundformEndElement);

            StartElement genusStartElement = eventFactory.createStartElement("","","genus");
            eventWriter.add(genusStartElement);
            switch (substantivToAdd.genus){
                case MASKULINUM:
                    eventWriter.add(eventFactory.createCharacters("maskulinum"));
                    break;
                case FEMININUM:
                    eventWriter.add(eventFactory.createCharacters("femininum"));
                    break;
                case NEUTRUM:
                    eventWriter.add(eventFactory.createCharacters("neutrum"));
                    break;
            }
            EndElement genusEndElement = eventFactory.createEndElement("","","genus");
            eventWriter.add(genusEndElement);

            StartElement genitivStartElement = eventFactory.createStartElement("","","genitiv");
            eventWriter.add(genitivStartElement);
            eventWriter.add(eventFactory.createCharacters(substantivToAdd.genitiv));
            EndElement genitivEndElement = eventFactory.createEndElement("","","genitiv");
            eventWriter.add(genitivEndElement);

            StartElement bedeutungStartElement = eventFactory.createStartElement("","","bedeutung");
            eventWriter.add(bedeutungStartElement);
            eventWriter.add(eventFactory.createCharacters(substantivToAdd.bedeutung));
            EndElement bedeutungEndElement = eventFactory.createEndElement("","","bedeutung");
            eventWriter.add(bedeutungEndElement);

            EndElement eElement = eventFactory.createEndElement("", "", "vocab");

            eventWriter.add(eElement);
            eventWriter.add(end);

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }


    }

}
