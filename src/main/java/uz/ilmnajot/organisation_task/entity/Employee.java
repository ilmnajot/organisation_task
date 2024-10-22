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
    private String pinfl;

    @Column(nullable = false)
    private LocalDate hiredDate;

    private LocalDate firedDate; // the date when one employee is fired // ishdan ketgan sana

    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;
}
