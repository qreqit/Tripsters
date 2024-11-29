    package com.example.tripsters.model;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    @Entity
    @Table(name = "additional_points")
    @Getter
    @Setter
    public class AdditionalPoint {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        private String additionalPoint;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "trip_id", nullable = false)
        private Trip trip;
    }
