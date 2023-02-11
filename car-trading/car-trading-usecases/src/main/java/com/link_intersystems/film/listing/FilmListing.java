package com.link_intersystems.film.listing;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class FilmListing extends AbstractList<ListedFilm> {

    private List<ListedFilm> listedFilms = new ArrayList<>();

    void addListedFilm(ListedFilm listedFilm) {
        listedFilms.add(listedFilm);
    }

    @Override
    public ListedFilm get(int index) {
        return listedFilms.get(index);
    }

    @Override
    public int size() {
        return listedFilms.size();
    }
}