package me.soeren;

public class Verb {
    String infiniv;
    String praesensIndikativ;
    String perfektIndikativ;
    String bedeutung;
    String supinstamm;

    public Verb(String infiniv, String praesensIndikativ, String perfektIndikativ, String bedeutung, String supinstamm){
        this.infiniv = infiniv;
        this.praesensIndikativ = praesensIndikativ;
        this.perfektIndikativ = perfektIndikativ;
        this.bedeutung = bedeutung;
        this.supinstamm = supinstamm;
    }
}
