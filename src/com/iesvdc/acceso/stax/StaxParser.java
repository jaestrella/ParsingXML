package com.iesvdc.acceso.stax;

import javax.xml.stream.*;
// import javax.xml.stream.events.*;
import javax.xml.stream.XMLInputFactory;
import java.io.*;

public class StaxParser {

	public void parseXMLDocument(String docName) {
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream input = new FileInputStream(new

			File(docName));
			XMLStreamReader xmlStreamReader = inputFactory
					.createXMLStreamReader(input);

			while (xmlStreamReader.hasNext()) {
				int event = xmlStreamReader.next();

				if (event == XMLStreamConstants.START_DOCUMENT) {
					System.out.println("Event Type:START_DOCUMENT");
				}
				if (event == XMLStreamConstants.START_ELEMENT) {
					System.out.println("Event Type: START_ELEMENT");

					System.out.println("Element Local Name:"
							+ xmlStreamReader.getLocalName());

					for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {

						System.out.println("Attribute Local Name:"
								+ xmlStreamReader.getAttributeLocalName(i));
						System.out.println("Attribute Value:"
								+ xmlStreamReader.getAttributeValue(i));
					}

				}

				if (event == XMLStreamConstants.CHARACTERS) {
					System.out.println("Event Type: CHARACTERS");
					System.out.println("Text:" + xmlStreamReader.getText());
				}

				if (event == XMLStreamConstants.END_DOCUMENT) {
					System.out.println("Event Type:END_DOCUMENT");
				}
				if (event == XMLStreamConstants.END_ELEMENT) {
					System.out.println("Event Type: END_ELEMENT");
				}

			}
		} catch (FactoryConfigurationError e) {
			System.out.println("FactoryConfigurationError" + e.getMessage());
		} catch (XMLStreamException e) {
			System.out.println("XMLStreamException" + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("IOException" + e.getMessage());
		}

	}

	public static void main(String[] argv) {

		StaxParser staxParser = new StaxParser();
                if(argv.length==1){
                   staxParser.parseXMLDocument(argv[0]);
                }else{
                    System.out.println("Uso del programa: \nStaxParse ficehro.xml");
                }
		

	}
}
