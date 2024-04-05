package com.example.bestesttheaters.api;

import org.springframework.hateoas.RepresentationModel;

public class WaitListItemDto extends RepresentationModel<WaitListItemDto> {
	private final int showId;
	private final int numberOfTickets;

	public WaitListItemDto(int showId, int numberOfTickets) {
		this.showId = showId;
		this.numberOfTickets = numberOfTickets;
	}

	public int getShowId() {
		return showId;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		WaitListItemDto that = (WaitListItemDto) o;

		if (showId != that.showId) return false;
        return numberOfTickets == that.numberOfTickets;
    }

	@Override
	public int hashCode() {
		int result = showId;
		result = 31 * result + numberOfTickets;
		return result;
	}

}
