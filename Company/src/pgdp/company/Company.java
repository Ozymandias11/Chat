package pgdp.company;
import pgdp.tree.Node;
import pgdp.tree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Company {

    private Employee CEO;
    private Tree<Integer> employeesTree;
    private Map<Integer,Employee> employees;
    private Queue<Integer> availableIDs;
    private static int availableID = 1;
    private String name;

    public Company(String name, Employee CEO) {
        this.name = name;
        this.CEO = CEO;
        employeesTree = new Tree<>(0);

        availableIDs = new PriorityQueue<>();
        employees = new HashMap<>();
        employees.put(0,CEO);
    }

    public void addEmployee(Employee newEmployee) {
        if(employeesTree.containsKey(newEmployee.getBoss().getID())){
            if(availableIDs.isEmpty()){
                newEmployee.setID(availableID);
                availableID++;
            }else{
                newEmployee.setID(availableIDs.poll());
            }
        }
        employeesTree.insert(newEmployee.getID(), newEmployee.getBoss().getID());
        employees.put(newEmployee.getID(), newEmployee);
    }

    public void fireEmployee(int ID) {
       if(employeesTree.containsKey(ID) && CEO.getID() != ID){
           availableIDs.add(ID);
           employeesTree.remove(ID);
           employees.remove(ID);

       }
    }

    public Employee findCommonBoss(Employee e1, Employee e2) {
        int id = employeesTree.LCA(e1.getID(), e2.getID());
        return employees.get(id);

    }

    public Employee findByID(int ID) {
       if(employeesTree.containsKey(ID))
           return employees.get(ID);
        return null;
    }

}
