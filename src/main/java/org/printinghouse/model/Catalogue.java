package org.printinghouse.model;

import lombok.Getter;

import java.util.*;

@Getter
public class Catalogue {
    private final Set<Publication> publications;

    public Catalogue(Set<Publication> publications) {
        this.publications = new HashSet<>(publications);
    }
}
