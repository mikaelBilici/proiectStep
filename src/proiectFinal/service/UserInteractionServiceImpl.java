package proiectFinal.service;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import proiectFinal.exception.InvalidUserInteractionException;
import proiectFinal.model.Department;
import proiectFinal.model.Employee;

import java.util.Scanner;

import static proiectFinal.util.Constants.*;

public class UserInteractionServiceImpl implements UserInteractionService {
    private Scanner scanner = new Scanner(System.in);
    EmployeeInteractionService employeeInteractionService =new EmployeeInteractionServiceImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void initInteraction() {
        switch (chooseInitialAction()){
            case ACCESS_DATABASE:
                switch (chooseAccessDatabaseAction()){
                    case LIST_EMPLOYEES:
                        System.out.println("show employees");
                        break;
                    case ADD_EMPLOYEES:
                        // preluam datele de la tastatura
                        Employee employee = employeeInteractionService.addEmployeeAction();
                        // salvam informatiile in baza de date
                        // layerul Service, nu acceseaza direct baza de date
                        // UserInteractionService deleaga responsabilitatea catre EmployeeService
                        // care la randul sau transmite datele catre EmployeeRepository care face salvarea propriu-zisa
                        employeeService.addEmployee(employee);
                        System.out.println("add employee");
                        break;
                    case DELETE_EMPLOYEES:
                        int idToBeDeleted = employeeInteractionService.deleteEmployeeAction();
                        employeeService.deleteEmployee(idToBeDeleted);
                        break;
                    case UPDATE_EMPLOYEES:
                        Employee employeeToBeUpdated = employeeInteractionService.updateEmployeeAction();
                        employeeService.updateEmployee(employeeToBeUpdated);
                        System.out.println("update employee works");
                        break;
                    case MAIN_MENU:
                        initInteraction();
                        break;


                }
                break;
            case VIEW_REPORTS:
                switch (chooseViewReportAction()){
                    case AVERAGE_SALARY_BY_COMPANY:
                        System.out.println("Show average  salary " + employeeService.calculateAverageSalary()) ;
                        break;
                    case AVERAGE_SALARY_BY_DEPARTMENT:

                        Department department = employeeInteractionService.chooseDepartmentAction();
                        // cere de la tastatura departamentu
                        // apoi calculeaza media salariilor filtrind by department
                        Double averageByDepartment = employeeService.calculateAverageSalaryByDepartment(department);

                        System.out.println("Average salary for " + department + " is " + averageByDepartment);
                        break;
                    case TOP_10_MOST_EXPENSIVE_EMPLOYEES_SALARY:
                        employeeService.employeeSalaryComparator();
                        break;
                    case TOP_10_MOST_LOYAL_COMPANY_EMPLOYEES:
                        employeeService.employeeDateComparator();
                        break;
                    case MAIN_MENU:
                        initInteraction();
                        break;
                }
                break;
        }
        initInteraction();

    }

    private Integer chooseViewReportAction() {
        System.out.println("Average salary by company - press " + AVERAGE_SALARY_BY_COMPANY);
        System.out.println("Average salary by department - press " + AVERAGE_SALARY_BY_DEPARTMENT);
        System.out.println("Top 10 of the most expensive employees - press " + TOP_10_MOST_EXPENSIVE_EMPLOYEES_SALARY);
        System.out.println("Top 10 of the most loyal employees - press " + TOP_10_MOST_LOYAL_COMPANY_EMPLOYEES);
        System.out.println("Go to Main Menu - press " + MAIN_MENU);

        try{
            Integer action = Integer.parseInt(scanner.nextLine());
            if(action != AVERAGE_SALARY_BY_COMPANY &&
                    action != AVERAGE_SALARY_BY_DEPARTMENT &&
                    action != TOP_10_MOST_EXPENSIVE_EMPLOYEES_SALARY &&
                    action != TOP_10_MOST_LOYAL_COMPANY_EMPLOYEES &&
                    action != MAIN_MENU){
                throw new InvalidUserInteractionException();
            }
            return action;
        }catch (Exception e) {
            System.out.println("Please enter a valid number for your action.");
        }
        return chooseAccessDatabaseAction();
    }

    private Integer chooseInitialAction(){
        System.out.println("Choose action: ");
        System.out.println("Access database - press " + ACCESS_DATABASE);
        System.out.println("View Reports - press " + VIEW_REPORTS);

        try{
            int action = Integer.parseInt(scanner.nextLine());
            if(action != ACCESS_DATABASE && action != VIEW_REPORTS){
                throw new InvalidUserInteractionException();
            }
            return action;
        }catch (Exception ex){
            System.out.println("Please enter a valid number: " + ACCESS_DATABASE + "(access database) " +
                    " or " + VIEW_REPORTS + " 2 (view reports)!");

        }
        return chooseInitialAction();
    }
    private Integer chooseAccessDatabaseAction(){
        System.out.println("List employees - press " + LIST_EMPLOYEES);
        System.out.println("Add employees - press " + ADD_EMPLOYEES);
        System.out.println("Delete employees - press " + DELETE_EMPLOYEES);
        System.out.println("Update employees - press " + UPDATE_EMPLOYEES);
        System.out.println("Main Menu - press " + MAIN_MENU);

        try{
            Integer action = Integer.parseInt(scanner.nextLine());
            if(action != LIST_EMPLOYEES && action != ADD_EMPLOYEES &&
                    action != DELETE_EMPLOYEES && action != UPDATE_EMPLOYEES && action != MAIN_MENU){
                throw new InvalidUserInteractionException();
            }
            return action;

        }catch (Exception e){
            System.out.println("Please enter a valid number for your action!");
        }
        return chooseAccessDatabaseAction();
    }
}
