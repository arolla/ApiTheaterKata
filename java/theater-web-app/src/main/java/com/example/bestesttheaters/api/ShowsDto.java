package com.example.bestesttheaters.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public final class ShowsDto {
    private final List<ShowDto> shows;

	@JsonCreator
    public ShowsDto(List<ShowDto> shows) {
        this.shows = shows;
    }

	@JsonProperty("shows")
    public List<ShowDto> shows() {
        return shows;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ShowsDto) obj;
        return Objects.equals(this.shows, that.shows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shows);
    }

    @Override
    public String toString() {
        return "ShowsDto[" +
               "shows=" + shows + ']';
    }

}
