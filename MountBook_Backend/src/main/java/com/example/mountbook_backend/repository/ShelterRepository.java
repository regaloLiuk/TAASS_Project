package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter,Long> {

    Optional<Shelter> findById(Long shelterId);

    // ritorna l'id di rifugi che non hanno camere nella lista delle prenotazioni per le date selezionate
    //DA PROVARE!!!
    @Query(value = "SELECT r.shelter_id FROM room r WHERE r.id NOT IN (" +
                   " SELECT rr.room_id FROM reserved_rooms rr JOIN reservation res ON (rr.reservation_id = res.id) WHERE res.first_day=:date_start AND res.last_day=:date_end)", nativeQuery = true)
    List<Long> findSheltersIdByDate(@Param("date_start") Date dateStart, @Param("date_end") Date dateEnd);

    @Query(value = "SELECT ss.shelter_id FROM shelter_services ss WHERE ss.serveice_id=:service1", nativeQuery = true)
    List<Long> findShelterIdByServece(@Param("service") Long service);

//    @Query(value = "SELECT ss.shelter_id FROM shelter_services ss WHERE ss.serveice_id=:service1 AND ss.serveice_id=:service2", nativeQuery = true)
//    List<Long> findShelterIdByServece(@Param("service1") Long service1, @Param("service2") Long service2);
//
//    @Query(value = "SELECT ss.shelter_id FROM shelter_services ss WHERE ss.serveice_id=:service1 AND ss.serveice_id=:service2", nativeQuery = true)
//    List<Long> findShelterIdByServece(@Param("service1") Long service1, @Param("service2") Long service2,
//                                      @Param("service3") Long service3);

}
