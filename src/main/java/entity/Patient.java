package entity;

public class Patient {

    private String userId;
    private String password;
    private String userPhoneNumber;
    private String familyPhoneNumber;

    public Patient() {

    }

    public String getUserId() { //getterは値を取り出す
        return userId;
    }

    public void setUserId(String userId) { //setterは値を入れる
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getFamilyPhoneNumber() {
        return familyPhoneNumber;
    }

    public void setFamilyPhoneNumber(String familyPhoneNumber) {
        this.familyPhoneNumber = familyPhoneNumber;
    }
}