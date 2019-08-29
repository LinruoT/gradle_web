package bitter.mybatis;

import bitter.mybatis.domain.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmpMapper {

    Employee selectEmp(Long id);

    void insertEmp(Employee employee);

    Employee selectEmpToResultMap(Long id);

    @Select("select * from employee where id=#{id}")
    Employee selectEmp2(Long id);

    Employee selectEmpByFirstnameAndLastname(@Param("firstName") String firstName, @Param("lastName") String lastName);

    Employee selectEmpByName(@Param("whichName") String whichName, @Param("name") String name);

    List<Employee> selectEmpByAge(int age);
    List<Employee> selectEmpByDept(long did);

    Employee getEmpAndDept(Long id);
    Employee getEmpAndDeptStep(Long id);

}
