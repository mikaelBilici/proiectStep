package proiectFinal.service;

import proiectFinal.exception.InvalidUserInteractionException;
import proiectFinal.model.Department;
import proiectFinal.model.Employee;
import proiectFinal.util.Constants;

import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeInteractionServiceImpl implements EmployeeInteractionService {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public Employee addEmployeeAction() {
        Employee employee =new Employee();
        employee.setId(getId());
        employee.setName(getName());
        employee.setDepartment(getDepartment());
        employee.setEmploymentDate(getEmploymentDate());
        employee.setSalary(getSalary());
        return employee;
    }

    @Override
    public Integer deleteEmployeeAction() {
        return getId();
    }

    @Override
    public Employee updateEmployeeAction() {
        return addEmployeeAction();
    }

    @Override
    public Department chooseDepartmentAction() {
        return getDepartment();
    }

    private Integer getId(){
        System.out.println("Id: ");
        try{
            Integer id = Integer.parseInt(scanner.nextLine());
            return id;
        }catch (Exception e){
            System.out.println("Wrong id. Try again!");
        }
        return getId();
    }

    private String getName(){
        System.out.println("Name: ");
        try{
            String name = scanner.nextLine();
            return name;
        }catch (Exception ex){
            System.out.println("Wrong name. Try again!");
        }
        return getName();
    }

    private Department getDepartment(){
        System.out.println("Department:" +
                Department.IT.getValue() + " -  IT, " +
                Department.HR.getValue() + "- HR, " +
                Department.SALES.getValue() + "- SALES " +
                Department.FMG.getValue() + "- FMG " +
                Department.MANAGER.getValue() + "- MANAGER " +
                Department.TRAINER.getValue() + "- TRAINER ");

        try{
            Integer depId = Integer.parseInt(scanner.nextLine());
            if(depId != Department.IT.getValue() && depId != Department.HR.getValue() &&
                    depId != Department.SALES.getValue() && depId != Department.FMG.getValue() &&
                    depId != Department.MANAGER.getValue() && depId != Department.TRAINER.getValue()){
                throw new InvalidUserInteractionException();
            }
            Department department = depId == Department.IT.getValue() ?
                    Department.IT : depId == Department.HR.getValue() ?
                    Department.HR : depId == Department.SALES.getValue() ?
                    Department.SALES : depId == Department.FMG.getValue() ?
                    Department.FMG : depId == Department.MANAGER.getValue() ?
                    Department.MANAGER : Department.TRAINER;

            return department;
        } catch (Exception ex){
            System.out.println("Wrong department. Try again");
        }
        return getDepartment();
    }

    private LocalDate getEmploymentDate(){
        System.out.println("Employment Date(" + Constants.DATE_FORMAT + "):");
        try{
            return LocalDate.parse(scanner.nextLine());
        }catch (Exception ex){
            System.out.println("Wrong employment date format. Try again!");
        }
        return getEmploymentDate();
    }

    private Double getSalary(){
        System.out.println("Salary: ");
        try{
            return Double.parseDouble(scanner.nextLine());
        }catch (Exception e){
            System.out.println("Wrong Salary. Try again!");
        }
        return getSalary();
    }
}
