package Proxy;

public class ProgMain {
    public static void main(String[] args) {
        Profil profil = new Profil("Marian", 20);
        IUser user = new User("marian@stud.ro", "1234", profil, "certificat");
        UserTProxy userTProxy = new UserTProxy((User) user);
        userTProxy.trainsomeone();
        userTProxy.getUser().setCertificare_fitness(null);
        userTProxy.trainsomeone();
        userTProxy.getUser().getProfil().setVarsta(17);
        userTProxy.trainsomeone();
    }
}
