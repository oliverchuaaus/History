package com.tougher.app.v1.dto.criteria;

import java.util.List;

import lombok.Data;

@Data
public class PageableDTO {
	private Integer offset;
	private Integer pageSize;
	private SortDTO sort;

	public PageableDTO() {
	}

	@Data
	public static class SortDTO {
		private List<OrderDTO> orderList;

		public static enum DirectionDTO {
			ASC, DESC
		}

		public static enum NullHandlingDTO {
			NATIVE, NULLS_FIRST, NULLS_LAST;
		}

		@Data
		public static class OrderDTO {
			private DirectionDTO direction = DirectionDTO.ASC;
			private String property;
			private boolean ignoreCase;
			private NullHandlingDTO nullHandling = NullHandlingDTO.NULLS_FIRST;
		}
	}
}
