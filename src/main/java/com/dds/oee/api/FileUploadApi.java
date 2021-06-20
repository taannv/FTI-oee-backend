package com.dds.oee.api;

import com.dds.oee.common.ThrowingConsumer;
import com.dds.oee.service.FileStoreageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Api(tags = "FileUploadApi")
@RestController
@RequestMapping("/api")
public class FileUploadApi {

	@Value("${file.upload.dir}")
	private String fileUploadDir;

	private FileStoreageService service;

	@PostConstruct
	private void init() throws Exception {
		this.service = new FileStoreageService(fileUploadDir);
	}

	@PostMapping("/files/upload")
	@ApiOperation(value = "Upload a file")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Upload a File successfully"),
			@ApiResponse(code = 404, message = "Not found folder"),
			@ApiResponse(code = 500, message = "Server error")
	})
	public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) throws Exception {
		service.write(file);
	}

	@PostMapping("/files/upload/multiple")
	@ApiOperation(value = "Upload multiple files.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Upload multiple files successfully"),
			@ApiResponse(code = 404, message = "Not found folder"),
			@ApiResponse(code = 500, message = "Server error")
	})
	public void uploadMultiple(@RequestParam("file") MultipartFile[] files, HttpServletRequest httpServletRequest) throws Exception {
		Arrays.asList(files).stream()
				.forEach(ThrowingConsumer
						.acceptThrow(file -> upload(file, httpServletRequest)));
	}

}
