package model;

public class Pet {

    private String name;
    private int age;
    private String breed;
    private String symptoms;

    private Status status;
    private Priority priority;
    private Species species;

    private Owner petOwner;

    private Vet veterinary;

    public Vet getVeterinary() {
        return veterinary;
    }

    public void setVeterinary(Vet veterinary) {
        this.veterinary = veterinary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Owner getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(Owner petOwner) {
        this.petOwner = petOwner;
    }

    public static enum Priority
    {
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        BLUE
    }

    public static enum Status
    {
        WAITING,
        IN_CONSULT,
        TRANSFER_TO_HOSPITALIZATION,
        AUTHORIZED_DEPARTURE,
        UNATTENDED_DEPARTURE
    }

    public static enum Species{
        DOG,
        CAT,
        RABBIT,
        REPTILE,
        BIRD
    }

    public Pet(Species species, String name, int age, String breed, String symptoms) {

        this.species = species;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.symptoms = symptoms;
        this.status = Status.WAITING;
    }

    public Pet() {
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
}
