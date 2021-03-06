package bitter.mybatis;

import bitter.mybatis.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapperDynamicSql {

    public List<Employee> getEmpsByConditionIf(Employee employee);
    public List<Employee> getEmpsByConditionTrim(Employee employee);
    public List<Employee> getEmpsByConditionChoose(Employee employee);
    public void updateEmp(Employee employee);

    public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);

    public void addEmps(@Param("emps") List<Employee> emps);
}

