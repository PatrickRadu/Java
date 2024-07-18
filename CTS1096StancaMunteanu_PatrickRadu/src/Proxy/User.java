package Proxy;

public class User implements IUser {
    private Profil profil;
    private String email;
    private String password;
    private String certificare_fitness;
    public User(String email, String password, Profil profil, String certificare_fitness) {
        this.email = email;
        this.password = password;
        this.profil = profil;
        this.certificare_fitness = certificare_fitness;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCertificare_fitness() {
        return certificare_fitness;
    }

    public void setCertificare_fitness(String certificare_fitness) {
        this.certificare_fitness = certificare_fitness;
    }

    @Override
    public void train() {
        System.out.println("Training");
    }

    @Override
    public void trainsomeone() {
        if(certificare_fitness!=null)
         System.out.println("Training someone");
        else System.out.println("You are not certified to train someone");
    }
}
