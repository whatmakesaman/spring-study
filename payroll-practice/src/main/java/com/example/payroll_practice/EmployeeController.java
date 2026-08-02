package com.example.payroll_practice;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployModelAssembler assembler;

    EmployeeController(EmployeeRepository repository,EmployModelAssembler assembler) {
        this.repository = repository;
        this.assembler=assembler;
    }

    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all(){
        List<EntityModel<Employee>> employees=repository.findAll()
                .stream()
                .map(employee -> assembler.toModel(employee))
                .toList();

        return CollectionModel.of(
                employees,
                linkTo(methodOn(EmployeeController.class)
                        .all())
                        .withSelfRel()
        );
    }


    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee){

        EntityModel<Employee> entityModel=
                assembler.toModel(repository.save(newEmployee));

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri()
                )
                .body(entityModel);
        }

    @GetMapping("/employees/{id}")
    EntityModel<Employee> oneEmployee(@PathVariable Long id){

        Employee employee= repository.findById(id)
                            .orElseThrow(()->new EmployeeNotFoundException(id));

        return assembler.toModel(employee);



    }

    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody  Employee newEmployee, @PathVariable Long id){

        Employee updatedEmployee= repository.findById(id)
                .map(employee ->{
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());

                    return repository.save(employee);
                } )
                .orElseGet(()->repository.save(newEmployee));

        EntityModel<Employee> entityModel= assembler.toModel(updatedEmployee);

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri()
                )
                .body(entityModel);
    }

    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id)
    {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


