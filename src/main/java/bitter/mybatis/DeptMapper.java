package bitter.mybatis;

import bitter.mybatis.domain.Department;

public interface DeptMapper {
    Department selectDeptById(Long id);
    void insertDept(Department department);
}
