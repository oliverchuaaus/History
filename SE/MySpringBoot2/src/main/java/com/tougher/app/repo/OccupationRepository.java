package com.tougher.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tougher.app.model.ref.Occupation;

public interface OccupationRepository extends JpaRepository<Occupation, Long> {

}
