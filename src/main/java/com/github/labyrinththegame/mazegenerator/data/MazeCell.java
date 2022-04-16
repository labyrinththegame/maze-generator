package com.github.labyrinththegame.mazegenerator.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

public class MazeCell {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean n;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean e;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean s;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean w;

    public MazeCell(Boolean n, Boolean e, Boolean s, Boolean w) {
        this.n = n;
        this.e = e;
        this.s = s;
        this.w = w;
    }

    public Boolean getN() {
        return n;
    }

    public void setN(Boolean n) {
        this.n = n;
    }

    public Boolean getE() {
        return e;
    }

    public void setE(Boolean e) {
        this.e = e;
    }

    public Boolean getS() {
        return s;
    }

    public void setS(Boolean s) {
        this.s = s;
    }

    public Boolean getW() {
        return w;
    }

    public void setW(Boolean w) {
        this.w = w;
    }

    @Override
    public String toString() {
        return "MazeCell{" +
                "n=" + n +
                ", e=" + e +
                ", s=" + s +
                ", w=" + w +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeCell mazeCell = (MazeCell) o;
        return Objects.equals(n, mazeCell.n) && Objects.equals(e, mazeCell.e) && Objects.equals(s, mazeCell.s) && Objects.equals(w, mazeCell.w);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, e, s, w);
    }
}
