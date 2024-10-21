package uz.ilmnajot.organisation_task.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String pinfl;

    @Column(nullable = false)
    private LocalDate hiredDate;

    private boolean deleted = false;

    @ManyToOne(optional = false)
    private Organisation organisation;
}
