package com.dds.oee.api;

import com.dds.oee.common.CommonQuery;
import com.dds.oee.payload.HistoryErrorModel;
import com.dds.oee.processor.HistoryErrorProcessor;
import com.dds.oee.security.CurrentUser;
import com.dds.oee.security.UserPrincipal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Author : duybv
 * Sep 17, 2019
 */

@Api(tags = "HistoryErrorApi")
@RestController
@RequestMapping("/api")
public class HistoryErrorApi {

	@Autowired
	private HistoryErrorProcessor processor;

	@PostMapping("/machines/{machineId}/history-errors")
	@ApiOperation(value = "Create new history error.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Create new history error successfully"),
			@ApiResponse(code = 500, message = "Server error")
	})
	@PreAuthorize("hasRole('USER')")
	public HistoryErrorModel create(@PathVariable Long machineId, @Valid @RequestBody CreateModel model, @CurrentUser UserPrincipal currentUser) {
		return processor.create(machineId, model, currentUser);
	}

	@GetMapping("/machines/{machineId}/history-errors")
	@ApiOperation(value = "Query history errors and return Page<HistoryErrorModel>", response = Page.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Query history errors successfully"),
			@ApiResponse(code = 500, message = "Server error")
	})
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public Page<HistoryErrorModel> query(@PathVariable Long machineId, @Valid @ModelAttribute QueryModel model) {
		return processor.query(machineId, model);
	}

	@Getter @Setter
	public static class CreateModel {

		@NotNull
		private Long startedAt;

		@NotNull
		private Long endedAt;

		@NotBlank
		@Size(max = 1028)
		private String cause;

	}

	@Getter @Setter
	public static class QueryModel extends CommonQuery {

	}

}
