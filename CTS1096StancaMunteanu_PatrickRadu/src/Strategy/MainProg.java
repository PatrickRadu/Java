package Strategy;

public class MainProg {
    ///main
    public static void main(String[] args) {
       Predictie predictie=new Predictie(new PredictiePerformanta(),"data");
       predictie.predict();
       Predictie predictie1=new Predictie();
         predictie1.setPredictie(new PredictieGreutate());
         predictie1.setData("data1");
            predictie1.predict();
    }
}
