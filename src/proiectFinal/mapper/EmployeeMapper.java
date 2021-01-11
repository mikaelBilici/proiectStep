package proiectFinal.mapper;

import proiectFinal.model.Department;
import proiectFinal.model.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static proiectFinal.util.Constants.COMMA_DELIMITER;

public class EmployeeMapper {
    public Employee getEmployeeFromCsvLine(String csvLine){
        if(csvLine != null && csvLine.length() > 0){
            String[] values = csvLine.split(COMMA_DELIMITER);
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(values[0]));
            employee.setName(values[1]);
            if(values[2].equals("IT")){
                employee.setDepartment(Department.IT);
            }else if (values[2].equals("HR")) {
                employee.setDepartment(Department.HR);
            }else if (values[2].equals("FMG")) {
                employee.setDepartment(Department.FMG);
            }else if (values[2].equals("TRAINER")) {
                employee.setDepartment(Department.TRAINER);
            }else if (values[2].equals("MANAGER")) {
                employee.setDepartment(Department.MANAGER);
            }else{
                employee.setDepartment(Department.SALES);
            }
            employee.setEmploymentDate((LocalDate.parse(values[3])));
            employee.setSalary(Double.parseDouble(values[4]));
            return employee;
        }else{
            return new Employee();
        }
    }

    public String getCsvLineFromEmployee(Employee employee){
        StringBuilder sb = new StringBuilder();
        sb.append(employee.getId());
        sb.append(COMMA_DELIMITER);
        sb.append(employee.getName());
        sb.append(COMMA_DELIMITER);
        sb.append(employee.getDepartment());
        sb.append(COMMA_DELIMITER);
        sb.append(employee.getEmploymentDate().toString());
        sb.append(COMMA_DELIMITER);
        sb.append(employee.getSalary());
        sb.append(COMMA_DELIMITER);
        return sb.toString();
    }

    public List<Employee> getEmployeeList(List<String> stringEmployees){
        List<Employee> employees = new ArrayList<>();
        for(String employeeString : stringEmployees){
            employees.add(getEmployeeFromCsvLine(employeeString));
        }
        return employees;
    }
}
