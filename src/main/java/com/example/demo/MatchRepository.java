package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatchRepository extends CrudRepository<Matches, Integer> {

    @Query("SELECT m FROM Matches m WHERE m.userId = ?1")
    List<Matches> findAllByUserId(Integer userId);

    @Query("SELECT m FROM Matches m WHERE m.userId = ?1 and m.approved = TRUE")
    List<Matches> findAllApprovedMatches(Integer userId);

    @Query("SELECT m FROM Matches m WHERE m.animalId = ?1 and m.approved = TRUE")
    List<Matches> findAllApprovedMatchesByAnimalId(Integer animalId);

    @Query("SELECT m FROM Matches m WHERE m.animalId = ?1")
    List<Matches> findAllByAnimalId(Integer animalId);

    @Query("SELECT m FROM Matches m WHERE m.animalId = ?1 and m.userId = ?2")
    Matches findByAnimalIdAndUserId(Integer animalId, Integer userId);

    @Query("SELECT m FROM Matches m WHERE m.animalId = ?1 and m.userId = ?2 and m.approved = TRUE")
    Matches findApprovedByAnimalIdAndUserId(Integer animalId, Integer userId);

    @Query("SELECT new com.example.demo.AnimalMatches(u, a, count(m.userId)) " +
            "FROM User u " +
            "LEFT JOIN Animal a ON a.ownerId = u.id " +
            "LEFT JOIN Matches m on a.id = m.animalId " +
            "WHERE u.id = ?1 " +
            "group by a.id")
    List<AnimalMatches> findCountByUserId(Integer userId);




}
