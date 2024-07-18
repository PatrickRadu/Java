package Instrumente;

public enum TipOperatiune {
    CUMPARARE("CUMPARARE"),
    VANZARE("VANZARE");

    private int directie;
    private final String tip;

//    TipOperatiune(int directie) {
//        this.directie = directie;
//    }

    TipOperatiune(String tip) {
        this.tip = tip;
        if(this.tip.equalsIgnoreCase("CUMPARARE"))
            this.directie=1;
        if(this.tip.equalsIgnoreCase("VANZARE"))
            this.directie=-1;
    }

    public String getTip()
    {
        return this.tip;
    }

    public int pozitie()
    {
        return this.directie;
    }
}
