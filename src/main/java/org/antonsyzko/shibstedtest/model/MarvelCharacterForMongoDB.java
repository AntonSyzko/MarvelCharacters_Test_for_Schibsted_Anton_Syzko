package org.antonsyzko.shibstedtest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by Admin on 20.11.2016.
 * Marvel Character for Mongo DB approach
 * @name - character name bt Marvel API
 * @id - character id by Marvel api
 * @appearance - has  appeared in this many comics
 *
 */
@Document(collection = "marvel_characters_table")
public class MarvelCharacterForMongoDB {

    @Indexed
    String name;
    @Id
    long id;
    @Indexed
    int appearance;

    public MarvelCharacterForMongoDB(long id, String name, int appearance) {
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

    public int getAppearance() {
        return appearance;
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
        sb.append("appearance=").append(appearance);
        sb.append(", name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
