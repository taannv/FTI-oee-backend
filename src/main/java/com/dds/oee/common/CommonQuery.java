package com.dds.oee.common;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter @Setter
public class CommonQuery {

    @ApiModelProperty(required = false, value = "Page index", example = "0")
    @Range(min = 0)
    protected Integer page;

    @ApiModelProperty(required = false, value = "Number item per one page")
    @Range(min = 1, max = 50)
    protected Integer size;

    protected Long fromDate, toDate;

    @ApiModelProperty(required = false, value = "Keyword for query")
    protected String keyword;

}
