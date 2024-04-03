package com.example.bestesttheaters.data;

import java.util.Objects;

public class Booking {

	private int id;
	private Show show;
	private int numberOfTickets;
	private BookingStatus status;

	public static Booking book(int id, Show show, int numberOfTickets) {
		return createBooking(id, show, numberOfTickets, BookingStatus.BOOKED);
	}

	public static Booking createBooking(int id, Show show, int numberOfTickets, BookingStatus status) {
		if (id <= 0) {
			throw new IllegalArgumentException("Booking ID must be greater than 0");
		}
		Objects.requireNonNull(show);
		if (numberOfTickets <= 0) {
			throw new IllegalArgumentException("Number of tickets must be greater than 0");
		}
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

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public BookingStatus getStatus() {
		return status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Booking booking = (Booking) o;

		if (id != booking.id) return false;
		if (numberOfTickets != booking.numberOfTickets) return false;
		if (!Objects.equals(show, booking.show)) return false;
        return status == booking.status;
    }

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (show != null ? show.hashCode() : 0);
		result = 31 * result + numberOfTickets;
		result = 31 * result + (status != null ? status.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Booking{" +
			   "id=" + id +
			   ", show=" + show +
			   ", numberOfTickets=" + numberOfTickets +
			   ", status=" + status +
			   '}';
	}
}
