package com.tougher.app.dto.criteria;

import lombok.Data;

public @Data class PageResultMetadataDTO {
	private Integer currentPage;
	private Integer totalItems;
	private Integer totalPages;
}
