package homework_day22.objects;

import java.util.Objects;

public class Data {

    Integer id;
    String username;
    String realName;
    String password;
    String email;

    public Data(Integer id, String username, String realName, String password, String email) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data userData = (Data) o;
        return id == userData.id && Objects.equals(username, userData.username) && Objects.equals(realName, userData.realName) && Objects.equals(password, userData.password) && Objects.equals(email, userData.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, realName, password, email);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realname='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
