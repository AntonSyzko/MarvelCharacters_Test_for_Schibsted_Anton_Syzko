package org.antonsyzko.shibstedtest.model;

/**
 * Created by Admin on 20.11.2016.
 * Base Case Marvel Caharcter
 * @name - character name bt Marvel API
 * @id - character id by Marvel api
 */

public class MarvelCharacter {
    String name;
    long id;

    public MarvelCharacter(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarvelCharacter that = (MarvelCharacter) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MarvelCharacter{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
