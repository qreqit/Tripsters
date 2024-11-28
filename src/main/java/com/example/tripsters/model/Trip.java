package com.example.tripsters.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vote> votes = new HashSet<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChatMessage> chatMessages = new HashSet<>();

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column
    private TripStatus status;

    @Column(nullable = false)
    private String startPoint;

    @Column(nullable = false)
    private String endPoint;


    @ElementCollection
    @CollectionTable(name = "trip_additional_points", joinColumns = @JoinColumn(name = "trip_id"))
    @Column(name = "point")
    private List<String> additionalpoints = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "trips_users",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    public enum TripStatus {
        INCOMING,
        INPROGRESS,
        COMPLETED
    }
}
