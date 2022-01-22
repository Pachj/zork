package ch.bbw.zork;

public enum Item {
    GRILL(20000, "grill"),
    PALME(18000, "palme"),
    FERNSEHER(15000, "fernseher"),
    SCHROTFLINTE(6500, "schrotflinte"),
    ERDE(6000, "erde"),
    HOCKER(5500, "hocker"),
    SCHAUFEL(3000, "schaufel"),
    WLAN_ROUTER(2000, "wlan_router"),
    BIERKRUG(1000, "bierkrug"),
    PUTZMITTEL(1000, "putzmittel"),
    MONOPOLY(1000, "monopoly"),
    BUCH(300, "buch"),
    HANDY(150, "handy"),
    FLEISCH(150, "fleisch"),
    SANDWICH(150, "sandwich"),
    APFEL(100, "apfel"),
    STROMKABEL(100, "stromkabel"),
    ZAUBERTRANK(80, "zaubertrank"),
    WC_PAPIER(80, "wc_papier"),
    REAGENZGLAS(80, "reagenzglas"),
    SCHLUESSEL(20, "schluessel"),
    SKALPELL(20, "skalpell"),
    ROSE(20, "rose"),
    PENNY(5, "penny"),
    KAKERLAKE(1, "kakerlake"),
    RAUPE(1, "raupe"),
    MESSER(300, "messer"),
    WASSERFLASCHE(1500, "wasserflasche"),
    SCHMETTERLING(1, "schmetterling"),
    KRUEMEL(1, "kruemel"),
    LAUCH(100, "lauch");

    public final String name;
    public final int weight;

    Item(int weight, String name) {
        this.name = name;
        this.weight = weight;
    }
}
