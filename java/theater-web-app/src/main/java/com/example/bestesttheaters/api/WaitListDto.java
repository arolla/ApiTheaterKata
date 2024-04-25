package com.example.bestesttheaters.api;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

public final class WaitListDto extends RepresentationModel<WaitListDto> {
	private final List<WaitListItemDto> items;

	public WaitListDto(List<WaitListItemDto> items) {
		this.items = items;
	}

	public List<WaitListItemDto> getItems() {
		return items;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (WaitListDto) obj;
		return Objects.equals(this.items, that.items);
	}

	@Override
	public int hashCode() {
		return Objects.hash(items);
	}

	@Override
	public String toString() {
		return "WaitListDto[" +
			   "items=" + items + ']';
	}

}
