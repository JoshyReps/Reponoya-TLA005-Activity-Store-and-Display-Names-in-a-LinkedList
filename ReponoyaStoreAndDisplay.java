package com.mycompany.reponoyastoreanddisplay;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ReponoyaStoreAndDisplay {

    static Scanner s = new Scanner(System.in);
    static PersonListHolder personList = new PersonListHolder();
    
    public static void main(String[] args) {

        while(true) {
            
            System.out.println("""

                               ----- Linked List Store and Display Names ------

                               What Would you Like to Perform :

                               1. Add
                               2. Remove
                               3. Display
                               4. Sort
                               5. Exit
                               """);

            Integer action;

            action = (Integer) returnInput("Action");

            if(action < 0 || action > 5) {
                System.out.println("Invalid Action");
            }
            else if(action == 5) {
                System.out.println("Good Bye!"); return;
            }
            else {
                mainMenu(action);
            }
        }
    }
    
    public static void mainMenu (int action) {
        
        switch(action) {

            case 1 -> {

                System.out.println("\n--------- Adding New Person ---------");
                 String newPersonName = (String) returnInput("Name");
                 Integer newPersonBalon = (Integer) returnInput("Balon");
                 String newPersonCourse = (String) returnInput("Course");
                 Integer newPersonYearLevel = (Integer) returnInput("Year Level");
                System.out.println("-------------------------------------\n");

                personList.addName(newPersonName, newPersonBalon, newPersonCourse, newPersonYearLevel);
            }
            case 2 -> {

                System.out.println("\n--------- Removing Existing Person ---------");
                 String personName = (String) returnInput("Name");
                System.out.println("--------------------------------------------\n");
                personList.removeName(personName);
            }
            case 3 -> {

                System.out.println("\n--------- Displaying Every Person ----------");
                 personList.displayNames();
                System.out.println("--------------------------------------------\n");
            }
            case 4 -> {
                sortMenu();
            }
        }
    }
    
    
    public static void sortMenu () {
        
        while(true) {
            
            System.out.println("""
                           ---------- Sort List By -----------
                           1. Name (Alphabetically) 
                           2. Balon (Highest to Lowest)
                           3. Course (Alphabetically)
                           4. Year Level (Highest to Lowest)
                           5. Reverse 
                           6. Exit
                           ------------------------------------
                           """);
        
            Integer sortInput = (Integer) returnInput("Action");

            if(sortInput == 6) return;
            
            
            switch(sortInput) {
                case 1 -> {
                    System.out.println("People List has been Sorted (By Name)");
                    personList.getPeople().sort(Comparator.comparing(Person::name)); return;
                }
                case 2 -> {
                    System.out.println("People List has been Sorted (By Balon)");
                    personList.getPeople().sort(Comparator.comparing(Person::balon).reversed()); return;
                }
                case 3 -> {
                    System.out.println("People List has been Sorted (By Course)");
                    personList.getPeople().sort(Comparator.comparing(Person::course)); return;
                }
                case 4 -> {
                    System.out.println("People List has been Sorted (By Year Level)");
                    personList.getPeople().sort(Comparator.comparing(Person::yearLevel).reversed()); return;
                }
                case 5 -> {
                    System.out.println("People List has Been Reversed");
                    personList.getPeople().reversed(); return;
                }
                default -> {
                    System.out.println("That is not a Valid Input!");
                }
            }
        }
    }
    
    
    public static Object returnInput (String message) {
        
        Object value = null;
        
        while(true) {
            
            System.out.print("Enter %s : ".formatted(message));

            if(message.equals("Balon") || message.equals("Year Level") || message.equals("Action")){
                try {
                    value = Integer.valueOf(s.nextLine());
                }
                catch(Exception e) { 
                    System.out.println("That is Invalid") ;
                }
            }
            else value = s.nextLine();
            
            if(value != null) break;
        }
        
        return value;
    }
}


record Person(String name, int balon, String course, int yearLevel){

    @Override
    public String toString() {
        
        return """
               ------------------------------
               Name : %s
               Balon : %d
               Course and Year Level : %d - %s
               ------------------------------
               """.formatted(name, 
                             balon, 
                             yearLevel, 
                             course);
    }

};


class PersonListHolder {
    
    private List<Person> people;
    
    public PersonListHolder (Person... names) {
        this.people = new LinkedList<>(List.of(names));
    }
    
    private Person findPerson(String name) {
        
        for(Person person : people) {
           if(person.name().equals(name)) {
               return person;
           }
       }
        return null;
    }
    
    public void addName(String name, int balon, String course, int yearLevel) {
        
        Person newPerson = new Person(name, balon, course, yearLevel);
        
        if(name.isBlank() || findPerson(name) != null) {
            System.out.println("That is an Invalid Name/That name already Exists"); return;
        }
        
        people.add(newPerson);
        System.out.printf("%s (%d - %s) has been Successfully Added!%n".formatted(newPerson.name(),
                                                                                newPerson.yearLevel(), 
                                                                                newPerson.course()));
    }
    
    public void removeName(String name) {
        
        Person personFound = findPerson(name);
        
        if(personFound == null) {
            System.out.println("Person does not Exist!"); return;
        }
        
        people.remove(personFound);
        System.out.printf("%s (%d - %s) has been Removed!%n".formatted(personFound.name(),
                                                                   personFound.yearLevel(), 
                                                                   personFound.course()));
    }
    
    public void displayNames() {
        if(people.isEmpty()) System.out.println("You Currently Have no People Listed :(");
        else people.forEach(System.out::println);
    }
    
    
    public List getPeople() {
        return people;
    }
}
