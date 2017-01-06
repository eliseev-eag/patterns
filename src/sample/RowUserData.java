package sample;

import java.util.List;

/**
 * Created by Елисеев on 05.01.2017.
 */
public class RowUserData {
    private List<String> userData;

    public RowUserData(List<String> userData) {
        this.userData = userData;
    }

    public List<String> getUserData() {
        return userData;
    }

    public void setUserData(List<String> userData) {
        this.userData = userData;
    }
}
