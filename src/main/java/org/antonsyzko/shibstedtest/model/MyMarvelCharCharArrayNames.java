package org.antonsyzko.shibstedtest.model;

import java.util.Arrays;

/**
 * Created by Admin on 19.11.2016.
 * gives + 10 % imporvement using char array instead of String object
 * result boost - 10 % average
 */
public class MyMarvelCharCharArrayNames {
    char[] name ;
    int comicsAppearance;

    public int getComicsAppearance() {
        return comicsAppearance;
    }

    public void setComicsAppearance(int comicsAppearance) {
        this.comicsAppearance = comicsAppearance;
    }

    public MyMarvelCharCharArrayNames(char[] name, int comicsAppearance) {
        this.name = name;
        this.comicsAppearance = comicsAppearance;
    }

    public char[] getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMarvelCharCharArrayNames that = (MyMarvelCharCharArrayNames) o;
        return Arrays.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getName());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyMarvelCharacter{");
        sb.append("name=");
        if (name == null) sb.append("null");
        else {
            for (int i = 0; i < name.length; ++i)
                sb.append(name[i]);
        }
        sb.append(", comicsAppearance=").append(comicsAppearance);
        sb.append('}');
        return sb.toString();
    }
}
