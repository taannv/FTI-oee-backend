package com.dds.oee.api;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

import com.dds.oee.common.CommonQuery;
import com.dds.oee.payload.VolumeModel;
import com.dds.oee.processor.VolumeProcessor;
import com.dds.oee.security.CurrentUser;
import com.dds.oee.security.UserPrincipal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Author : duybv
 * Sep 17, 2019
 */
@Api(tags = "VolumeApi")
@RestController
@RequestMapping("/api")
public class VolumeApi {

	@Autowired
	private VolumeProcessor processor;

	@PostMapping("/machines/{machineId}/volumes")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "Create new volume.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Create new volume successfully"),
			@ApiResponse(code = 500, message = "Server error")
	})
	public VolumeModel create(@PathVariable Long machineId, @Valid @RequestBody CreateModel model, @CurrentUser UserPrincipal currentUser) {
		return processor.create(machineId, model, currentUser);
	}

	@GetMapping("/volumes")
	@ApiOperation(value = "Query volumes and return Page<VolumeModel>", response = Page.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Query volumes successfully"),
			@ApiResponse(code = 500, message = "Server error")
	})
	public Page<VolumeModel> query(@Valid @ModelAttribute QueryModel model) {
		return processor.query(model);
	}

	@Builder @Getter @Setter
	public static class CreateModel {

		@NotNull
		@DecimalMin(value = "1.00", message = "Sản lượng đầu vào phải lớn hơn hoặc bằng {value} .")
		private BigDecimal input;

		@NotNull
		@DecimalMin(value = "1.00", message = "Sản lượng đầu ra phải lớn hơn hoặc bằng {value} .")
		private BigDecimal output;

		@NotNull
		private Long startedAt;

		@NotNull
		private Long endedAt;

		@NotBlank
		@Size(max = 60)
		private String command;

		@NotBlank
		@Size(max = 1028)
		private String note;
	}

	@Getter @Setter
	public static class QueryModel extends CommonQuery {

		private Long machineId;
		private BigDecimal input;
		private BigDecimal output;
		private Long startedAt;
		private Long endedAt;

	}

}
