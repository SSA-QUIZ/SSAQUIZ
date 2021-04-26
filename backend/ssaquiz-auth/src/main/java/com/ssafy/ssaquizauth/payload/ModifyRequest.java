package com.ssafy.ssaquizauth.payload;

public class ModifyRequest {
    private String name;

    private String password;

    private String imageUri;

    private String email;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
