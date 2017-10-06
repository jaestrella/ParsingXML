/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.sax;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;


/**
 *
 * @author matinal
 */
public class SaxParser extends DefaultHandler {

   
    public void startDocument() throws SAXException {
        System.out.println("SAX Event: START DOCUMENT");
    }

    
    public void endDocument() throws SAXException {
        System.out.println("SAX Event: END DOCUMENT");
    }

    
    public void startElement(String namespaceURI,
            String localName,
            String qName,
            Attributes attr) throws SAXException {
        System.out.println("SAX Event: START ELEMENT[ " + localName + " ]");
        if (attr!=null){
            for (int i=0; i<attr.getLength(); i++){
                System.out.println("  ATTR: "+attr.getLocalName(i)
                        +" = "+attr.getValue(i));
            }
        }
    }

    
    public void endElement(String namespaceURI,
            String localName,
            String qName) throws SAXException {
        System.out.println("SAX Event: END ELEMENT[ " + localName + " ]");
    }

    
    public void characters(char[] ch,
            int start,
            int length) throws SAXException {
        System.out.print("SAX Event: CHARACTERS[ ");

        try {
            OutputStreamWriter output = new OutputStreamWriter(System.out);
            output.write(ch, start, length);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" ]");
    }

    /**
     * @param argv the command line arguments
     */
    public static void main(String[] argv) {
        if ( argv.length == 1 ) {
            String inputFile = argv[0]; // argv[0];
            System.out.println("Processing '" + inputFile + "'.");
            System.out.println("SAX Events:");
            try {
                XMLReader reader = XMLReaderFactory.createXMLReader();
                reader.setContentHandler(new SaxParser());
                reader.parse(new InputSource(
                        new FileReader(inputFile)));
            } catch (SAXException | IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        } else {
            System.out.println("Uso del programa: SaxParser fichero.xml");
        }
    }
    
}
