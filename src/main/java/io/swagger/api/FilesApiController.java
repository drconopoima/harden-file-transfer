package io.swagger.api;

import io.swagger.model.FileRepository;

import io.swagger.model.File;
import io.swagger.model.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-02-27T07:39:21.717Z[GMT]")
@RestController
public class FilesApiController implements FilesApi {

    private static final Logger log = LoggerFactory.getLogger(FilesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private FileRepository repository;

    @Value("${upload.path}")
    private String uploadPath;

    @org.springframework.beans.factory.annotation.Autowired
    public FilesApiController(ObjectMapper objectMapper, HttpServletRequest request, FileRepository repository) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.repository = repository;
    }

    // Create File Method.
    public ResponseEntity<File> createFile(
            @Parameter(in = ParameterIn.DEFAULT, description = "Send the File Object not the File content", required = true, schema = @Schema()) @Valid @RequestBody File body) {
        File file = this.repository.save(body);
        return new ResponseEntity<File>(file, HttpStatus.CREATED);
    }

    // Dwnld File Methd.
    public ResponseEntity<byte[]> downloadFile(
            @Parameter(in = ParameterIn.PATH, description = "The id of the file to retrieve", required = true, schema = @Schema()) @PathVariable("fileId") String fileId,
            @Parameter(in = ParameterIn.QUERY, description = "", schema = @Schema()) @Valid @RequestParam(value = "token", required = false) String token)
            throws IOException {
        Optional<File> fileObj = this.repository.findById(Long.parseLong(fileId));

        if (!fileObj.isPresent()) {
            return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }

        File fileRecord = fileObj.get();
        java.io.File file = new java.io.File(uploadPath + "/" + fileId + "." + fileRecord.getExtension());

        if (file.exists()) {
            byte[] fileData = java.nio.file.Files.readAllBytes(file.toPath());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(fileData, HttpStatus.OK);
        }

        return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Files> listFiles(
            @Parameter(in = ParameterIn.QUERY, description = "How many items to return at one time (max 100)", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Files>(objectMapper.readValue(
                        "[ {\n  \"descr\" : \"descr\",\n  \"signature\" : \"signature\",\n  \"name\" : \"name\",\n  \"id\" : 0,\n  \"virus\" : true,\n  \"ownedBy\" : 6\n}, {\n  \"descr\" : \"descr\",\n  \"signature\" : \"signature\",\n  \"name\" : \"name\",\n  \"id\" : 0,\n  \"virus\" : true,\n  \"ownedBy\" : 6\n} ]",
                        Files.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Files>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Files>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Boolean> recoverFile(
            @Parameter(in = ParameterIn.PATH, description = "The id of the file to recover", required = true, schema = @Schema()) @PathVariable("fileId") String fileId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Boolean>(objectMapper.readValue("false", Boolean.class),
                        HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Boolean>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Resource> shareableURL(
            @Parameter(in = ParameterIn.PATH, description = "The id of the file to retrieve", required = true, schema = @Schema()) @PathVariable("fileId") String fileId,
            @Parameter(in = ParameterIn.QUERY, description = "", schema = @Schema()) @Valid @RequestParam(value = "userName", required = false) String userName,
            @Parameter(in = ParameterIn.QUERY, description = "", schema = @Schema()) @Valid @RequestParam(value = "expires", required = false) String expires) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Resource>(objectMapper.readValue("\"\"", Resource.class),
                        HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Resource>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<File> showFileById(
            @Parameter(in = ParameterIn.PATH, description = "The id of the file to retrieve", required = true, schema = @Schema()) @PathVariable("fileId") String fileId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<File>(objectMapper.readValue(
                        "{\n  \"descr\" : \"descr\",\n  \"signature\" : \"signature\",\n  \"name\" : \"name\",\n  \"id\" : 0,\n  \"virus\" : true,\n  \"ownedBy\" : 6\n}",
                        File.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<File>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<File>(HttpStatus.NOT_IMPLEMENTED);
    }

    // Delet File Methd.
    public ResponseEntity<Void> deleteFileById(
            @Parameter(in = ParameterIn.PATH, description = "The id of the file to retrieve", required = true, schema = @Schema()) @PathVariable("fileId") String fileId) {
        Optional<File> fileObj = this.repository.findById(Long.parseLong(fileId));

        if (!fileObj.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        File fileRecord = fileObj.get();

        java.io.File file = new java.io.File(uploadPath + "/" + fileId + "." + fileRecord.getExtension());
        this.repository.delete(fileRecord);

        if (!file.exists()) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        file.delete();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // Uploa File Methd.
    public ResponseEntity<String> uploadFile(
            @Parameter(in = ParameterIn.PATH, description = "The id of the file to retrieve", required = true, schema = @Schema()) @PathVariable("fileId") String fileId,
            @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
        Optional<File> fileObj = this.repository.findById(Long.parseLong(fileId));

        if (!fileObj.isPresent()) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        File fileRecord = fileObj.get();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        fileRecord.setExtension(extension);
        this.repository.save(fileRecord);

        java.io.File directory = new java.io.File(uploadPath);

        if (!directory.exists()) {
            directory.mkdir();
        }

        file.transferTo(new java.io.File(uploadPath + "/" + fileId + "." + extension));
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }
}
