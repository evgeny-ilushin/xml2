package com.zw.util;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class xml2 {

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            System.out.println("Usage: xml2 <file1> <file2> ...");
            return;
        }

        for (String file: args) { process(file); }
    }

    private static void process(String file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(file));
            process(doc);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void process(Node node) {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node g = node.getChildNodes().item(i);
            process("", g);
        }
    }

    private static void process(String path, Node node) {
        String name = node.getNodeName();
        if (node.getChildNodes().getLength() == 0) {
            String content = node.getTextContent();
            if (StringUtils.isNotBlank(content)) {
                System.out.println(path + "=" + content);
            }
        } else {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                Node g = node.getChildNodes().item(i);
                process(path + "/" + name, g);
            }
        }
    }
}
