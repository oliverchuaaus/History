package com.tougher.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tougher.app.model.ref.Hobby;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {

}
