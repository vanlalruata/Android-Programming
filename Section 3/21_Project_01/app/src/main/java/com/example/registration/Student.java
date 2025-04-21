package com.example.registration;

public class Student {
    private int id;
    private String fullName, dob, gender, fatherName, motherName, presentAddress, correspondingAddress, contact, email;

    public Student(int id, String fullName, String dob, String gender, String fatherName, String motherName, String presentAddress, String correspondingAddress, String contact, String email) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.presentAddress = presentAddress;
        this.correspondingAddress = correspondingAddress;
        this.contact = contact;
        this.email = email;
    }

    public Student(String fullName, String dob, String gender, String fatherName, String motherName, String presentAddress, String correspondingAddress, String contact, String email) {
        this(-1, fullName, dob, gender, fatherName, motherName, presentAddress, correspondingAddress, contact, email);
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getFatherName() { return fatherName; }
    public String getMotherName() { return motherName; }
    public String getPresentAddress() { return presentAddress; }
    public String getCorrespondingAddress() { return correspondingAddress; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }

    public void setId(int id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setDob(String dob) { this.dob = dob; }
    public void setGender(String gender) { this.gender = gender; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }
    public void setPresentAddress(String presentAddress) { this.presentAddress = presentAddress; }
    public void setCorrespondingAddress(String correspondingAddress) { this.correspondingAddress = correspondingAddress; }
    public void setContact(String contact) { this.contact = contact; }
    public void setEmail(String email) { this.email = email; }
}
