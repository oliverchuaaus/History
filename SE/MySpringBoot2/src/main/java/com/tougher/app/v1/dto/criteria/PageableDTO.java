package com.tougher.app.v1.dto.criteria;

import java.util.List;

import lombok.Data;

public @Data class PageableDTO {
	private Integer offset;
	private Integer pageSize;
	private SortDTO sort;

	public PageableDTO() {
	}

	public PageableDTO(Integer offset, Integer pageSize, SortDTO sort) {
		this.offset = offset;
		this.pageSize = pageSize;
		this.sort = sort;
	}

	public PageableDTO(Integer offset, Integer pageSize) {
		this.offset = offset;
		this.pageSize = pageSize;
	}

	public static PageableDTO of(Integer offset, Integer pageSize) {
		return new PageableDTO(offset, pageSize);
	}

	public static @Data class SortDTO {
		private List<OrderDTO> orderList;

		private static enum DirectionDTO {
			ASC, DESC
		}

		private static enum NullHandlingDTO {
			NATIVE, NULLS_FIRST, NULLS_LAST;
		}

		public @Data class OrderDTO {
			private DirectionDTO direction;
			private String property;
			private boolean ignoreCase;
			private NullHandlingDTO nullHandling;
		}
	}
}
