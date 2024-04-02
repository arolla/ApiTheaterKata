package com.example.bestesttheaters.data;

import java.util.Objects;

public class Booking {

	private int id;
	private Show show;
	private int numberOfTickets;

	public static Booking createBooking(int id, Show show, int numberOfTickets) {
		Booking booking = new Booking();
		booking.setId(id);
		booking.setShow(show);
		booking.setNumberOfTickets(numberOfTickets);
		return booking;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Show getShow() {
		return this.show;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Booking booking = (Booking) o;

		if (id != booking.id) return false;
		if (numberOfTickets != booking.numberOfTickets) return false;
        return Objects.equals(show, booking.show);
    }

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (show != null ? show.hashCode() : 0);
		result = 31 * result + numberOfTickets;
		return result;
	}

	@Override
	public String toString() {
		return "Booking{" +
			   "id=" + id +
			   ", show=" + show +
			   ", numberOfTickets=" + numberOfTickets +
			   '}';
	}
}
