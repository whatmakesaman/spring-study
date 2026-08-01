package com.example.payroll_practice;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployModelAssembler
        implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    @Override
    public EntityModel<Employee> toModel(Employee employee){

        return EntityModel.of(
                employee,

                linkTo(methodOn(EmployeeController.class)
                        .oneEmployee(employee.getId()))
                        .withSelfRel(),

                linkTo(methodOn(EmployeeController.class)
                        .all())
                        .withRel("employees")
        );
    }

}
