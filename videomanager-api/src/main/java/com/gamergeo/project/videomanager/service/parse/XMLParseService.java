package com.gamergeo.project.videomanager.service.parse;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gamergeo.project.videomanager.VideoManagerException;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.VideoService;

import lombok.AllArgsConstructor;

/**
 * Specific class to manage XML Parsing
 */
@Service
@AllArgsConstructor
public class XMLParseService {
	
	private final VideoService videoService;
	
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
    
    /**
     * Parse a folder
     */
    private Folder parseFolder(Node titleNode) {
    	Folder folder = new Folder(titleNode.getTextContent());

    	// Find corresponding content node
		Node contentNode = findFolderContent(titleNode);
		folder.setContentNode(contentNode);
    	
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
    
    /** 
     * Find the node containing the content associated to titlenode, i.e. the closest DL Tag
     */
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
    
    /**
     * Parse and import all videos for selected folder and childs
     */
    public ParseResult parseVideos(Folder selectedFolder) {

    	ParseResult result = new ParseResult();
    	
    	if (!isFolderContent(selectedFolder.getContentNode())) {
    		throw new VideoManagerException("Folder: " + selectedFolder + " is incorrect. Content node is not a DL tag");
    	}

        NodeList videoNodes = ((Element) selectedFolder.getContentNode()).getElementsByTagName(ParseConstant.VIDEO_TAG);
        
        for (int i = 0; i < videoNodes.getLength(); i++) {
        	result.incrementParsed();
        	Element videoElement = (Element) videoNodes.item(i);
        	Video video = parseVideo(videoElement);
        	video = videoService.addVideo(video);
        	
        	if (video != null) {
        		result.incrementImported();
        	}
        }
        
        return result;
    }
    
    private Video parseVideo(Element videoElement) {
    	Video video = new Video();
    	
    	video.setUrl(getAttribute(videoElement, ParseConstant.LINK_ATTRIBUTE));
    	
    	String date = getAttribute(videoElement, ParseConstant.DATE_ATTRIBUTE);
        LocalDate localDate = Instant.ofEpochSecond(Long.valueOf(date)).atZone(ZoneId.systemDefault()).toLocalDate();
    	video.setAddedDate(localDate);
    	
    	video.setTitle(videoElement.getTextContent());
    	
    	return video;
    }
    
    /**
     * Raise errror if attribute not found
     */
    private String getAttribute(Element element, String attribute) {
    	String result = element.getAttribute(attribute);
    	
    	if (result == null || result.isEmpty()) {
    		throw new VideoManagerException("Attribute " + attribute + " not found for " + element);
    	}
    	
    	return result;
    		
    }
    
    private boolean isFolder(Node node) {
    	return node.getNodeType() == Node.ELEMENT_NODE && ParseConstant.FOLDER_TAG.equals(node.getNodeName());
    }
    
    private boolean isFolderContent(Node node) {
    	return node.getNodeType() == Node.ELEMENT_NODE && ParseConstant.FOLDER_CONTENT_TAG.equals(node.getNodeName());
    }
}
