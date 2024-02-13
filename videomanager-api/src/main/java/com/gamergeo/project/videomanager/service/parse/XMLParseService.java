package com.gamergeo.project.videomanager.service.parse;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gamergeo.project.videomanager.VideoManagerException;
import com.gamergeo.project.videomanager.model.parse.Folder;

/**
 * Specific class to manage XML Parsing
 */
@Service
public class XMLParseService {
	
	private Document createDocument(File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
	        return builder.parse(file);
		} catch (Exception e) {
			throw new VideoManagerException("Impossible to parse xml file: " + file.getName(), e);
		}
	}

    public Folder parseFolders(File file) {
    	Document document = createDocument(file);
        try {
            // Root folder for all node (first title)
            Node rootElement = getRootElement(document.getDocumentElement());
            return parseFolder(rootElement);
        } catch (Exception e) {
        	throw new VideoManagerException("Impossible to parse folders: " + file.getName(), e);
        }
    }
    
    /** Return the parent folder element */
    private Node getRootElement(Node node) {

        if (isFolder(node)) {
        	return node;
        } else {
            // Recursive call to get children
            NodeList children = node.getChildNodes();
            
            for (int i = 0; i < children.getLength(); i++) {
            	Node root = getRootElement(children.item(i));
            	
            	if (root != null) {
            		return root;
            	}
            }
        }
        
        return null;
    }
    
    
//    private Folder parseFolder(Node node) {
//    	Folder folder = new Folder();
//    	
//        NodeList children = node.getChildNodes();
//        
//        for (int i = 0; i < children.getLength(); i++) {
//        	Node child = children.item(i);
//            if (isFolder(child)) {
//            	folder.setName(child.getTextContent());
//            	
//            } else if (isFolderContent(child)) {
//            	 Folder childFolder = parseFolder(child);
//            	
//            	// We add child folder only its a folder
//            	if (childFolder.getName() != null && !childFolder.getName().isEmpty()) {
//                	folder.getChildFolder().add(childFolder);
//            	}
//            }
//        }
//        
//        return folder;
//    }

    /**
     * Parse a folder
     */
    private Folder parseFolder(Node titleNode) {
    	Folder folder = new Folder(titleNode.getTextContent());

    	// Find corresponding content node
		Node contentNode = findFolderContent(titleNode);
    	
		// Parse folder childs
        NodeList children = contentNode.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
        	Node child = children.item(i);
        	
        	if (isFolder(child)) {
        		Folder childFolder = parseFolder(child);
        		folder.getChildFolder().add(childFolder);
        		
        	}
        }
        
        return folder;
    }
    
    private Node findFolderContent(Node titleNode) {
		Node sibling = titleNode.getNextSibling();
		
        while (sibling != null) {
        	if (isFolderContent(sibling)) {
                return sibling;
        	}
        	sibling = sibling.getNextSibling();
        }
        
        throw new VideoManagerException("Folder content not found :" + titleNode.getTextContent());
    }
    
    
    
    
    
    
    
    
    
//    
//    private Folder parseFolder(Node node) {
//        Folder rootFolder = new Folder();
//        NodeList children = node.getChildNodes();
//        
//        for (int i = 0; i < children.getLength(); i++) {
//        	Node child = children.item(i);
//            if (isFolder(child)) {
//            	rootFolder.setName(child.getTextContent());
//            } else if (isFolderContent(child)) {
//            	parseContent(child, rootFolder);
//            }
//        }
//        return rootFolder;
//    }
//    
//    private void parseFolder(Node node, Folder currentFolder) {
//        // Folder are in H3 Node
//        if (isFolder(node)) {
//            Folder folder = new Folder();
//            folder.setName(node.getTextContent());
//            currentFolder.getChildFolder().add(folder);
//
//            NodeList children = node.getChildNodes();
//            for (int i = 0; i < children.getLength(); i++) {
//            	parseContent(children.item(i), folder);
//            }
//
//            Node sibling = node.getNextSibling();
//            while (sibling != null) {
//            	parseContent(sibling, folder);
//                sibling = sibling.getNextSibling();
//            }
//        }
//    }
//    
//    private void parseContent(Node node, Folder folder) {
//    	if (isFolderContent(node)) {
//            // Recursive call to get children
//            NodeList children = node.getChildNodes();
//            for (int i = 0; i < children.getLength(); i++) {
//                parseFolder(children.item(i), folder);
//            }
//    	}
//    }
//    
    private boolean isFolder(Node node) {
    	return node.getNodeType() == Node.ELEMENT_NODE && ParseConstant.FOLDER_TAG.equals(node.getNodeName());
    }
    
    private boolean isFolderContent(Node node) {
    	return node.getNodeType() == Node.ELEMENT_NODE && ParseConstant.FOLDER_CONTENT_TAG.equals(node.getNodeName());
    }
}
