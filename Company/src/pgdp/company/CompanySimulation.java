package pgdp.company;

import java.util.Scanner;

public class CompanySimulation {
    public static void main(String[] args) {
        simulation("Novak","ATP");
    }

    private static void simulation(String ceoName, String companyName) {
        Employee ceo = new Employee(ceoName,null);
        ceo.setID(0);
        Company company = new Company(companyName,ceo);
        Scanner scanner = new Scanner(System.in);
        String numberOfQueries = scanner.nextLine();

        try {
            for (int k = 0; k < Integer.parseInt(numberOfQueries); k++) {
                try {
                    String query = scanner.nextLine();
                    if (query.contains("EmployeeWithID")) {
                        int nameID = Integer.parseInt(query.substring(15));
                        if (company.findByID(nameID) == null) {
                            System.out.println("Employee can not found");
                        } else {
                            System.out.println(company.findByID(nameID));
                        }
                    } else if (query.contains("Add")){
                        int i = 4; // starting index of a name.
                        StringBuilder nameToObtain = new StringBuilder();
                        while(!Character.toString(query.charAt(i)).equals(" ")){
                            nameToObtain.append(query.charAt(i));
                            i++;
                        }
                        Employee boss = company.findByID(Integer.parseInt(query.substring(i + 1)));
                        if(boss == null)
                            System.out.println("Boss could not found");
                        else{
                            company.addEmployee(new Employee(nameToObtain.toString(), boss));
                            System.out.println(nameToObtain + " has added");
                        }


                    } else if (query.contains("Fire")) {
                        int nameID = Integer.parseInt(query.substring(5));
                        if (company.findByID(nameID) == null)
                            System.out.println("Employee can not found");
                        else {
                            company.fireEmployee(nameID);
                        }
                    } else if (query.contains("FindCommonBoss")) {
                        StringBuilder id = new StringBuilder(); int i = 15;
                        while(!Character.toString(query.charAt(i)).equals(" ")){
                            id.append(query.charAt(i));
                            i++;
                        }
                        Employee employee = company.findByID(Integer.parseInt(String.valueOf(id)));
                        Employee employee1 = company.findByID(Integer.parseInt(query.substring(i + 1)));
                        if(employee != null && employee1 != null){
                            System.out.println( company.findCommonBoss(employee, employee1) + " is a common boss");
                        }else
                            System.out.println("one or both employess have not found in a list");


                    } else {
                        System.out.println("Invalid input");
                        break;
                    }

                } catch (Exception e) {
                    System.out.println("Invalid input or employee can not found");
                }
            }
            scanner.close();
        }catch (Exception e){
            System.out.println("Invalid input");
        }
    }
}
