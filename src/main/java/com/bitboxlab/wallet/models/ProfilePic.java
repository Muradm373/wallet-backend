package com.bitboxlab.wallet.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

    @Entity
    @Table(name = "profilepics")
public class ProfilePic {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    private String email;

    @Lob
    private byte[] data;

    public ProfilePic() {
    }

    public ProfilePic(String name, String type, byte[] data, String email) {
        this.name = name;
        this.type = type;
        this.data = data.clone();
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        final byte[] dataBitmap = data.clone();
        return dataBitmap;
    }

    public void setData(byte[] data) {
        this.data = data.clone();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
