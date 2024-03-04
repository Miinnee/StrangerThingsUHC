package fr.mine.roles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stats {
    int dm;
    int rm;
    int dd;
    int rd;

    public Stats(int dm, int rm, int dd, int rd) {
        this.dm = dm;
        this.rm = rm;
        this.dd = dd;
        this.rd = rd;
    }

}
