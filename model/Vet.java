package model;

public class Vet {

    private String idNumber;
    private String name;
    private String lastName;
    private String ruv;
    private boolean isAvailable;

    private int attendedPets;

    private Pet petInAttendance; //mascota que está atendiendo

    public Vet(String idNumber, String name, String lastName, String ruv, boolean isAvailable) {
        this.idNumber = idNumber;
        this.name = name;
        this.lastName = lastName;
        this.ruv = ruv;
        this.isAvailable = isAvailable;
        this.petInAttendance = null; //no está atendiendo ninguna mascota
        this.attendedPets = 0;
    }


    public Vet() {
        this.petInAttendance = null; //no está atendiendo ninguna mascota
        this.attendedPets = 0;
    }

    public int getAttendedPets() {
        return attendedPets;
    }

    public void setAttendedPets(int attendedPets) {
        this.attendedPets = attendedPets;
    }


    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRuv() {
        return ruv;
    }

    public void setRuv(String ruv) {
        this.ruv = ruv;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Pet getPet() {
        return petInAttendance;
    }

    public void setPet(Pet pet) {
        this.petInAttendance = pet;
    }
}
