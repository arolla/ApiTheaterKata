/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.bestesttheaters.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

public class Show {

	private String title;
	private String date;
	private Integer id;
	private int capacity;

	public static Show createShowMediumCapacity(int id, LocalDateTime date, String title) {
		return createShow(id, date, title, 100);
	}

	public static Show createShow(int id, LocalDateTime date, String title, int capacity) {
		Show show = new Show();
		show.setId(id);
		show.setDate(date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
		show.setTitle(title);
		show.setCapacity(capacity);
		return show;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isNew() {
		return this.id == null;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Show show = (Show) o;

		if (capacity != show.capacity) return false;
		if (!Objects.equals(title, show.title)) return false;
		if (!Objects.equals(date, show.date)) return false;
        return Objects.equals(id, show.id);
    }

	@Override
	public int hashCode() {
		int result = title != null ? title.hashCode() : 0;
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + capacity;
		return result;
	}

	@Override
	public String toString() {
		return "Show{" +
			   "title='" + title + '\'' +
			   ", date='" + date + '\'' +
			   ", id=" + id +
			   ", capacity=" + capacity +
			   '}';
	}
}
