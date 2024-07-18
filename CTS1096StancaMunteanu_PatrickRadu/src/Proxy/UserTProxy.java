package Proxy;

public class UserTProxy implements IUser{
    private User user;

    public UserTProxy(User user) {
        this.user = user;
    }

    @Override
    public void train() {
        user.train();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void trainsomeone() {
        if(user.getProfil().getVarsta()>=18)
        {
            user.trainsomeone();
        }
        else
        {
            System.out.println("You are not old enough to train someone");
        }
    }
}
