package com.link_intersystems.sakilla.film.rating;

import com.link_intersystems.sakilla.person.Age;

import java.time.Clock;

public interface Rating {
    String getName();

    boolean isAgeAllowed(Age age, Clock clock);
}
