package me.soeren;

public class Substantiv {
    Genus genus;
    String genitiv;
    String bedeutung;
    String grundform;

    public Substantiv(String grundform, Genus genus, String genitiv, String bedeutung){
        this.grundform = grundform;
        this.genus = genus;
        this.genitiv = genitiv;
        this.bedeutung = bedeutung;
    }
}
