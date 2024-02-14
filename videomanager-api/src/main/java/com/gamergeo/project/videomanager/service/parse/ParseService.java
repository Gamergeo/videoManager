package com.gamergeo.project.videomanager.service.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.VideoManagerException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for importting file
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ParseService {
	
	private final XMLParseService xmlParseService;
	
	/**
	 * Copy file in /parse/
	 */
	public File importFile(File file) {
        File rawFile = copyFile(file, getFileName(file.getName(), ParseConstant.RAW_SUFFIX, ""));
        File xmlFile = copyFile(rawFile, getFileName(file.getName(), "", ParseConstant.XML_EXTENSION));
        cleanFile(xmlFile);
        
        return xmlFile;
	}
	
	private File copyFile(File file, String destinationName) {
		
        // Keep file name
        Path destinationPath = ParseConstant.FILE_DIRECTORY.resolve(destinationName);
        
        try {
            if (Files.notExists(ParseConstant.FILE_DIRECTORY)) {
                Files.createDirectories(ParseConstant.FILE_DIRECTORY);
            }
            
            // Copier le fichier
            Files.copy(file.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            log.info("File has been copied to " + destinationPath);
            return destinationPath.toFile();
        } catch (IOException e) {
	        throw new VideoManagerException("Erreur reading file: " + file.getPath(), e);
        }
	}
	
	/**
	 * Validate individual file
	 */
	public boolean validateFile(File file) {
	    String fileName = file.getName();
	    String extension = getFileExtension(fileName);

		// File should be html
	    if ("html".equalsIgnoreCase(extension) || "htm".equalsIgnoreCase(extension)) {
	    	
	    	// File should start like expected
	    	return fileContentStartsWith(file);
	    }
	    
	    return false;
	}
	
	/**
	 * @param initialFileName original file name
	 * @param suffix if file will be suffixed
	 * @param extension if file will change extension
	 * @return the proper file name
	 */
	private String getFileName(String initialFileName, String suffix, String extension) {
		
	    // Déterminez le point de séparation pour l'extension
	    int dotIndex = initialFileName.lastIndexOf('.');
	    String baseName = dotIndex == -1 ? initialFileName : initialFileName.substring(0, dotIndex);
	    String originalExtension = dotIndex == -1 ? "" : initialFileName.substring(dotIndex);

	    // Construisez le nouveau nom de fichier
	    String destinationFileName = baseName + (suffix != null ? suffix : "");
	    
	    // Ajoutez la nouvelle extension ou conservez l'originale si aucune nouvelle extension n'est fournie
	    if (extension != null && !extension.isEmpty()) {
	        // Assurez-vous que la nouvelle extension commence par un point
	        if (!extension.startsWith(".")) {
	        	extension = "." + extension;
	        }
	        destinationFileName += extension;
	    } else {
	        destinationFileName += originalExtension;
	    }

	    return destinationFileName;
	}
	
	private String getFileExtension(String fileName) {
	    int dotIndex = fileName.lastIndexOf('.');
	    
	    if(dotIndex >= 0) {
	        return fileName.substring(dotIndex + 1);
	    }
	    return "";
	}
	
	private boolean fileContentStartsWith(File file) {
	    StringBuilder contentBuilder = new StringBuilder();
	    
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        while ((line = reader.readLine()) != null && contentBuilder.length() < ParseConstant.EXPECTED_START.length()) {
	            contentBuilder.append(line).append("\n");
	        }
	    } catch (IOException e) {
	        throw new VideoManagerException("Erreur reading file: " + file.getPath(), e);
	    }
	    
	    return contentBuilder.toString().startsWith(ParseConstant.EXPECTED_START);
	}

	
	/**
	 * Chrome bookmarks file are not a proper xml.
	 * @param file
	 */
	private void cleanFile(File file) {
        try {
            Path path = file.toPath();
            String content = Files.readString(path);
            content = content.replace("<DT>", "");
            content = content.replace("<p>", "");
            content = content.replaceAll("&(?!amp;)", "&amp;");
            
            content += "</META>";
            
            Files.writeString(path, content);
		} catch (IOException e) {
	        throw new VideoManagerException("Erreur reading file: " + file.getPath(), e);
		}
	}
	
    public Folder parseFolders(File xmlFile) {
    	return xmlParseService.parseFolders(xmlFile);
    }

    public ParseResult parseVideos(Folder selectedFolder) {
    	return xmlParseService.parseVideos(selectedFolder);
    }
}
