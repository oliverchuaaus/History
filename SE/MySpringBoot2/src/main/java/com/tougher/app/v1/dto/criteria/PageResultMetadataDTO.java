package com.tougher.app.v1.dto.criteria;

import lombok.Data;

@Data
public class PageResultMetadataDTO {
	private Integer currentPage;
	private Integer totalItems;
	private Integer totalPages;
}
