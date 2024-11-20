package org.example.gymkhanabackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity

public class Event {
    //Event entity

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_seq_gen")
    @SequenceGenerator(name = "events_seq_gen", sequenceName = "events_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "label", nullable = false, length = 50)
    private String label;

    @Column(name = "day", nullable = false)
    private Long day;
}
