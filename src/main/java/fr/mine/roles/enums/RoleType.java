package fr.mine.roles.enums;

import fr.mine.roles.Stats;
import fr.mine.roles.enums.Camps;
import fr.mine.utils.AllUtils;
import lombok.Getter;

@Getter
public enum RoleType {

    ELEVEN("Eleven", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    MIKE("Mike", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(1, 2, 2, 2)),
    WILL("Will", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(1, 2, 2, 1)),
    HOPPER("Hopper", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    LUCAS("Lucas", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    MAX("Max", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    JOYCE("Joyce", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    NANCY("Nancy", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    ROBIN("Robin", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    STEVE("Steve", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    EDDIE("Eddie", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    DUSTIN("Dustin", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    BILLY("Billy", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    MURRAY("Murray", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    JOHNATHAN("Johnathan", Camps.HAWKINS, AllUtils.translateAlternateColorCodesAndHexes('&', "&#08b0ae"), new Stats(3, 2, 3, 2)),
    VECNA("Vecna", Camps.UPSIDE_DOWN, AllUtils.translateAlternateColorCodesAndHexes('&', "&#e54629"), new Stats(3, 2, 3, 2)),
    FLAGELLEUR("Flagelleur Mental", Camps.UPSIDE_DOWN, AllUtils.translateAlternateColorCodesAndHexes('&', "&#e54629"), new Stats(3, 2, 3, 2)),
    DEMOGORGON("Demogorgon", Camps.UPSIDE_DOWN, AllUtils.translateAlternateColorCodesAndHexes('&', "&#e54629"), new Stats(3, 2, 3, 2)),
    DEMOCHIEN("Demochien", Camps.UPSIDE_DOWN, AllUtils.translateAlternateColorCodesAndHexes('&', "&#e54629"), new Stats(3, 2, 3, 2)),
    CHAUVE_SOURIS("Chauve Souris", Camps.UPSIDE_DOWN, AllUtils.translateAlternateColorCodesAndHexes('&', "&#e54629"), new Stats(3, 2, 3, 2)),
    ENZO("Enzo", Camps.RUSSES, AllUtils.translateAlternateColorCodesAndHexes('&', "&#ec3ade"), new Stats(3, 2, 3, 2)),
    GRIGORI("Grigori", Camps.RUSSES, AllUtils.translateAlternateColorCodesAndHexes('&', "&#ec3ade"), new Stats(3, 2, 3, 2)),
    STEPANOV("Stepanov", Camps.RUSSES, AllUtils.translateAlternateColorCodesAndHexes('&', "&#ec3ade"), new Stats(3, 2, 3, 2)),
    SCIENTIFIQUE("Scientifique", Camps.RUSSES, AllUtils.translateAlternateColorCodesAndHexes('&', "&#ec3ade"), new Stats(3, 2, 3, 2)),
    GARDIEN("Gardien", Camps.RUSSES, AllUtils.translateAlternateColorCodesAndHexes('&', "&#ec3ade"), new Stats(3, 2, 3, 2)),
    SOLDAT("Soldat", Camps.RUSSES, AllUtils.translateAlternateColorCodesAndHexes('&', "&#ec3ade"), new Stats(3, 2, 3, 2)),
    DR_BRENNER("Dr Brenner", Camps.SOLITAIRES, AllUtils.translateAlternateColorCodesAndHexes('&', "&#51e312"), new Stats(3, 2, 3, 2)),
    WALLAS("Wallas", Camps.SOLITAIRES, AllUtils.translateAlternateColorCodesAndHexes('&', "&#51e312"), new Stats(3, 2, 3, 2)),
    DR_OWENS("Dr Owens", Camps.SOLITAIRES, AllUtils.translateAlternateColorCodesAndHexes('&', "&#51e312"), new Stats(3, 2, 3, 2)),
    JASON(" Jason ", Camps.SOLITAIRES, AllUtils.translateAlternateColorCodesAndHexes('&', "&#51e312"), new Stats(3, 2, 3, 2));

    private final String name;
    private final Camps camps;
    private final String color;
    private final Stats stats;

    RoleType(String name, Camps camps, String color, Stats stats) {
        this.name = name;
        this.camps = camps;
        this.color = color;
        this.stats = stats;
    }
}
