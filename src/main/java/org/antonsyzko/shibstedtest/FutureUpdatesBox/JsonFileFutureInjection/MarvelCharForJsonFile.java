package org.antonsyzko.shibstedtest.FutureUpdatesBox.JsonFileFutureInjection;

import org.antonsyzko.shibstedtest.model.MarvelCharacter;

/**
 * Created by Admin on 20.11.2016.
 */
public class MarvelCharForJsonFile {

    String name;
    long id;
       int appearance;


    public MarvelCharForJsonFile(long id, String name, int appearance) {
        this.id = id;
        this.name = name;
        this.appearance = appearance;
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
        final StringBuffer sb = new StringBuffer("MarvelCharForJsonFile{");
        sb.append("appearance=").append(appearance);
        sb.append(", name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
