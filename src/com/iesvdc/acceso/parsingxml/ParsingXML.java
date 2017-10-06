package com.iesvdc.acceso.parsingxml;

import com.iesvdc.acceso.dom.DomUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParsingXML {
   static private Document documento;
    
    static public void prettyPrint(Node nodo){
        switch(nodo.getNodeType()) {
            case Node.TEXT_NODE:
                System.out.println("::TEXTO::"+nodo.getTextContent());
                break;
            case Node.ATTRIBUTE_NODE:
                System.out.println("::ATRIBUTO::"+nodo.getNodeName()+" = "+nodo.getNodeValue());
                break;
            case Node.ELEMENT_NODE:
                System.out.println("::ELEMENTO::"+nodo.getNodeName());
                break;
        }
    }
    
    static public void recursiveWalker(Node nodo){         
        prettyPrint(nodo);       
        if (nodo.hasAttributes()) {
            NamedNodeMap nnm = nodo.getAttributes();
            for (int i=0; i<nnm.getLength(); i++){
                prettyPrint(nnm.item(i));
            }
        }
        if (nodo.hasChildNodes()) {
            NodeList nm = nodo.getChildNodes();
            for (int i=0; i<nm.getLength();i++){
                Node tmp = nm.item(i);
                if (tmp.hasChildNodes()){
                    recursiveWalker(tmp);
                } else {
                    prettyPrint(tmp);
                }
            }
        } 
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        if (args.length==1) {
            try {
                documento = DomUtil.parse(args[0], false);
                
                
                // Vamos a imprimir la etiqueta del nodo ra�z.
                System.out.println("La etiqueta del nodo raiz es: "+
                        documento.getDocumentElement().getNodeName());
                
                /* 
                    Vamos a comprobar si el primer hijo del primer 
                    hijo existe (tienes nietos en el primer hijo). 
                    Si los hay, mostramos la etiqueta, sino, decimos 
                    que no hay.
                */
                Node raiz = documento.getDocumentElement();
                if (raiz.hasChildNodes()){
                    System.out.println("Raiz: "+raiz.getNodeName()+" tiene hijos");
                    Node hijo = raiz.getFirstChild();
                    
                    while (hijo!=null && hijo.getNodeType()!=Node.ELEMENT_NODE) 
                        hijo = hijo.getNextSibling();
                    
                    if (hijo.hasChildNodes()){
                        System.out.println(hijo.getNodeName()+" El hijo tiene a los nietos");
                        Node nieto = hijo.getFirstChild();
                        
                        while (nieto!=null && nieto.getNodeType()!=Node.ELEMENT_NODE) 
                        nieto = nieto.getNextSibling();
                        
                        if (nieto.hasChildNodes()){
                            System.out.println(nieto.getNodeName()+" El nieto tiene a los biznietos\n");
                            Node biznieto = nieto.getFirstChild();
                        }
                    }
                }
                
                recursiveWalker(raiz);
                
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }else{
            System.out.println("Uso del programa...\n"
                    + "ParsingXml [fichero.xml]\n");
        }
    }
    
}