package io.springboot.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.springboot.main.Model.Employees;


@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long>{

}



//JpaRepository  it exposes to database crud methods
//it provides methods such as findall, saveall, flush(), deleteall, pag