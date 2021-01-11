package proiectFinal.service;

import proiectFinal.model.Department;
import proiectFinal.model.Employee;

public interface EmployeeInteractionService {
    Employee addEmployeeAction();
    Integer deleteEmployeeAction();
    Employee updateEmployeeAction();
    Department chooseDepartmentAction();


}
