package org.antonsyzko.shibstedtest.model;

/**
 * Created by Admin on 19.11.2016.
 * for functioanl Chunk 55 List approach
 */
public class MyMarvelCharacter {
    String name;
    int comicsAppearance;

    public String getName() {
        return name;
    }

    public MyMarvelCharacter(String name, int comicsAppearance) {
        this.name = name;
        this.comicsAppearance = comicsAppearance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getComicsAppearance() {
        return comicsAppearance;
    }

    public void setComicsAppearance(int comicsAppearance) {
        this.comicsAppearance = comicsAppearance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyMarvelCharacter that = (MyMarvelCharacter) o;

        if (getComicsAppearance() != that.getComicsAppearance()) return false;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getComicsAppearance();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MARVEL CHARACTER Name : ").append(name).append(" has  appeared in ").append(comicsAppearance).append(" comics issues ");
        return sb.toString();
    }
}
