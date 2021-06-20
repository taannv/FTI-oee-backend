package com.dds.oee.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dds.oee.payload.MachineModel;
import com.dds.oee.processor.MachineProcessor;
import com.dds.oee.security.CurrentUser;
import com.dds.oee.security.UserPrincipal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.Setter;

/**
 * Author : duybv
 * Sep 16, 2019
 */

@Api(tags = "MachineApi")
@RestController
@RequestMapping("/api")
public class MachineApi {

	private final MachineProcessor processor;

	public MachineApi(MachineProcessor processor) {
		this.processor = processor;
	}

	@PostMapping("/machines")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Create new machine.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Create new machine successfully"),
			@ApiResponse(code = 500, message = "Server error")
	})
	public void create(@Valid @RequestBody CreateModel model) {
		processor.create(model);
	}

	@GetMapping("/machines/all")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Get all machines.", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get all machines successfully"),
			@ApiResponse(code = 500, message = "Server error")
	})
	public List<MachineModel> all() {
		return processor.all();
	}

	@GetMapping("/users/{username}/machines")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "Get machines by username.", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get machines by username successfully"),
			@ApiResponse(code = 500, message = "Server error")
	})
	public List<MachineModel> getByUser(@PathVariable(value = "username") String username, @CurrentUser UserPrincipal currentUser) {
		return processor.getByUserName(username, currentUser);
	}

	@GetMapping("/users/me/machines/manage")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "Get machine by currrent user.", response = MachineModel.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get machine by currrent user successfully"),
			@ApiResponse(code = 500, message = "Server error")
	})
	public MachineModel getByCurrentUser(@CurrentUser UserPrincipal currentUser) {
		return processor.getByCurrentUser(currentUser);
	}

	@Getter @Setter @ApiModel
	public static class CreateModel {

		@NotBlank
		@Size(max = 60)
		private String name;

		@Size(max = 1028)
		private String description;
	}

}
