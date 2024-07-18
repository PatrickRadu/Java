package Strategy;

public class Predictie {
    private IPredictie predictie;
    private String data;

    public Predictie() {
        super();
        predictie=null;
        data="";
    }

    public Predictie(IPredictie predictie, String data) {
        this.predictie = predictie;
        this.data = data;
    }

    public void setPredictie(IPredictie predictie) {
        this.predictie = predictie;
    }

    public void setData(String data) {
        this.data = data;
    }
    public void predict()
    {
        System.out.println("Data: "+this.data);
        this.predictie.predict();
    }
}
