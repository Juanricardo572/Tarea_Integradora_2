package model;

import javafx.scene.input.ScrollEvent;

import java.util.*;

public class PetCenter {

    final int MAX_VETS = 7;

    final int MAX_PETS_IN_CONSULT = 120;

    private String name;

    /**
     * dynamic
     */
    private ArrayList<Vet> veterinarians;
    private ArrayList<Pet> pets;

    public PetCenter(String name) {
        this.name = name;
        veterinarians = new ArrayList<Vet>();
    }

    public PetCenter() {
        veterinarians = new ArrayList<Vet>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Vet> getVeterinarians() {
        return veterinarians;
    }

    public void setVeterinarians(ArrayList<Vet> veterinarians) {
        this.veterinarians = veterinarians;
    }


    /**
     *
     * @param veterinaryToRegister
     */
    public void addVet(  Vet veterinaryToRegister   )
    {
       if(veterinarians.size() < MAX_VETS)
       {
           veterinarians.add(veterinaryToRegister);
       }
       else
       {
           //print error message or
           // throw exception
       }
    }

    /**
     *
     * @param pId
     */
    public void deleteVet(String pId) {
        //check if are pets registered
        if (pets.isEmpty())
        {
            //search for vet to delete
            for (int i = 0; i < veterinarians.size(); i++) {
                if (veterinarians.get(i).getIdNumber().equals(pId)) {
                    veterinarians.remove(i);
                }
            }
        }
    }

    /**
     *
     * @param pPetToRegister
     */
    public void registerPet( Pet pPetToRegister )
    {
        if(pets.size() < MAX_PETS_IN_CONSULT)
        {
            pets.add(pPetToRegister);
        }
        else
        {
            //print error message
            //or throw exception
        }

    }

    /**
     *
     * @param pOwnerName
     * @param pPetName
     */
    public void removePet( String pOwnerName, String pPetName  )
    {
        Pet currentPet;
        for(int i = 0; i < pets.size(); i++)
        {
            currentPet = pets.get(i);
            //check if pet name and owner name coincide and pet status is pending
            if( currentPet.getName().equals(pPetName)  &&
                    currentPet.getPetOwner().getFullName().equals(pOwnerName) &&
                    currentPet.getStatus().equals(Pet.Status.WAITING))
            {
                pets.remove(i);

            }
        }
    }

    /**
     *
     * @param pVetId
     */
    public void startConsultation(String pVetId)
    {
        //check if vet exists
        boolean vetFound = false;
        Vet currentVet = null;
        Pet petToAttend;
        //iterate through all vets to find vet with pVetId
        for(int i = 0; i < veterinarians.size() && !vetFound; i++)
        {
            currentVet = veterinarians.get(i);
            if(currentVet.getIdNumber().equals(pVetId))
            {
                //vet is found, search for pets waiting for attendance
                vetFound = true;
            }
        }

        if(vetFound)
        {
           //search for pet waiting;
            petToAttend = searchPetWaitingForAttendance();
            if(petToAttend != null)
            {
                //assign pet to vet
                currentVet.setPet(petToAttend);

                //increase number of attended pets by the vet
                currentVet.setAttendedPets(currentVet.getAttendedPets() + 1);

                //change pet status:
                petToAttend.setStatus(Pet.Status.IN_CONSULT);

                //assign vet to pet
                petToAttend.setVeterinary(currentVet);

            }
            else //waiting pet not found
            {
                //show error message or throw exception
            }
        }
        else //vet not found:
        {
            //show error message or throw exception, vet not found
        }

    }

    private Vet searchVeterinary(String vetId)
    {
        Vet veterinary = null;
        for(int i = 0; i < veterinarians.size(); i++)
        {
            if(veterinarians.get(i).getIdNumber().equals(vetId))
            {
                veterinary = veterinarians.get(i);
            }
        }
        return veterinary;
    }

    /**
     *
     * @param pVetId
     * @param pPetName
     */
    public void endConsultation(String pVetId, String pPetName, boolean authorizeDeparture)
    {

        Vet currentVet;
        Pet currentPet;

        currentVet = searchVeterinary(pVetId);
        //vet found
        if(currentVet != null)
        {

            currentPet = currentVet.getPet();
            if(currentVet.getPet() != null)
            {
                //pet is in attendance with vet:
                if(currentPet.getName().equals(pPetName)) {
                    if (authorizeDeparture) {
                       currentPet.setStatus(Pet.Status.AUTHORIZED_DEPARTURE);
                    }
                    else
                    {
                        currentPet.setStatus(Pet.Status.TRANSFER_TO_HOSPITALIZATION);
                    }
                    //make vet available:
                    currentVet.setPet(null);
                }
            }
            else //pet is not in attendance with vet:
            {
                //show error message or throw exception, pet is not in attendance with vet
            }
        }
        else //vet not found
        {
            //show error message or throw exception, vet not found
        }


    }

    public int numberOfUnattendedPets()
    {
        int count = 0;
        for(int i = 0; i < pets.size(); i++)
        {
            if(  pets.get(i).getStatus().equals(Pet.Status.WAITING)  )
            {
                count++;
            }
        }
        return count;
    }



    /**
     * aux method to look up for a pet waiting to be attended
     * @return
     */
    private Pet searchPetWaitingForAttendance()
    {
        Pet petWaiting = null;
        for(int i = 0; i < pets.size(); i++)
        {
            if(pets.get(i).getStatus().equals(Pet.Status.WAITING))
            {
                petWaiting = pets.get(i);
            }
        }
        return petWaiting;
    }


    /**
     *
     */
    public void dailyClose()
    {

        //1. verify unattended pets:
        if(numberOfUnattendedPets() == 0)
        {
            System.out.println("Not unattended pets");
        }

        //2. print of name with the most attended pets
        System.out.println("Vet with most attended pets: " + vetWithMoreAttendedPets() );

        //3. attended pets by priority
        System.out.println("Mascotas en prioridad azul:" + attendedPetsByPriority(Pet.Priority.BLUE));
        System.out.println("Mascotas en prioridad verde:" + attendedPetsByPriority(Pet.Priority.GREEN));
        System.out.println("Mascotas en prioridad amarillo:" + attendedPetsByPriority(Pet.Priority.YELLOW));
        System.out.println("Mascotas en prioridad naranja:" + attendedPetsByPriority(Pet.Priority.ORANGE));
        System.out.println("Mascotas en prioridad red:" + attendedPetsByPriority(Pet.Priority.RED));

        //4. percentage of unattended departed pets
        double percUnattendedDeparted = (double) countPetsByStatus(Pet.Status.UNATTENDED_DEPARTURE) / (double) pets.size();
        System.out.println("Pets in unattended departure: " + percUnattendedDeparted);

        //5. remove all attended pets:
        removeAttendedPets();

    }

    private void removeAttendedPets()
    {
        for(int i = 0; i < pets.size(); i++)
        {
            if(pets.get(i).getStatus().equals(Pet.Status.AUTHORIZED_DEPARTURE) ||
                    pets.get(i).getStatus().equals(Pet.Status.TRANSFER_TO_HOSPITALIZATION)
                    // || pets.get(i).getStatus().equals(Pet.Status.IN_CONSULT)
               )
            {
                pets.remove(i);
            }
        }
        
    }

    private int countPetsByStatus(Pet.Status pStatus)
    {
        int count = 0;
        for(int i = 0; i < pets.size(); i++)
        {
            if( pets.get(i).getStatus().equals(pStatus) )
                count++;

        }
        return count;
    }


    public String vetWithMoreAttendedPets()
    {
        String vetName = "";
        int majorValue = 0;
        for(int i = 0; i < veterinarians.size(); i++)
        {
            if( veterinarians.get(i).getAttendedPets() > majorValue  )
            {
                majorValue = veterinarians.get(i).getAttendedPets();
                vetName = veterinarians.get(i).getName();
            }
        }
        return vetName;
    }

    public int attendedPetsByPriority(Pet.Priority pPriority)
    {
        int count = 0;
        for(int i = 0; i < pets.size(); i++)
        {
            if(pets.get(i).getPriority().equals(pPriority))
            {
                count++;
            }
        }
        return count;
    }


}
