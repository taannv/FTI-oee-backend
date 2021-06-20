package com.dds.oee.service;

import com.dds.oee.exception.UndefinedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by duy.bui
 * Date: 11/15/19
 * Email: duy.bui@ivnd.com.vn
 **/
public class FileStoreageService {

    private Logger logger;
    private String folder;
    private Path rootLocation;

    public FileStoreageService(final String folder) throws Exception {
        this.logger = LoggerFactory.getLogger(getClass());

        this.folder = "data" + File.separatorChar
                + (folder.charAt(folder.length() - 1) == File.separatorChar ? folder : folder + File.separatorChar);

        initRootLocation();
    }

    private void initRootLocation() throws Exception {
        rootLocation = Paths.get(this.folder);

        initFolder(rootLocation);
    }

    public void initFolder(Path path) throws UnexpectedException {
        if (Files.exists(path)) {
            return;
        }

        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new UndefinedException();
        }
    }

    public Path write(MultipartFile file) throws Exception {
        try {
            Path path = Paths.get(folder + createFileName(file));

            Files.write(path, file.getBytes());

            return path;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UnexpectedException(e.getMessage());
        }
    }

    private String createFileName(MultipartFile file) {
        return String.join(".", UUID.randomUUID().toString(), getExtension(file));
    }

    private String getExtension(MultipartFile file) {
        return getExtension(file.getOriginalFilename());
    }

    private String getExtension(String fileName) {
        return fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0
                ? fileName.substring(fileName.lastIndexOf(".")+1)
                : "";
    }

    public byte[] read(String fileName) {
        try {
            return Files.readAllBytes(Paths.get(folder + fileName));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new byte[0];
        }
    }

    public boolean delete(String fileName) {
        try {
            Path path = Paths.get(folder, fileName);
            return Files.deleteIfExists(path);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public boolean isExisted(String fileName) {
        File[] files = new File(folder).listFiles();
        return Arrays.stream(files).anyMatch(file -> file.getName().equals(fileName));
    }

    public Resource loadAsResource(String fileName) throws Exception {
        try {
            Resource resource = new UrlResource(load(fileName).toUri());
            return (resource.exists() || resource.isReadable()) ? resource : null;
        } catch (Exception e) {
            throw new UnexpectedException(e.getMessage());
        }
    }

    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    public Stream<Path> loadAll() throws Exception {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new UndefinedException();
        }
    }
}
