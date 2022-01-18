package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter,Long> {

    Optional<Shelter> findById(Long shelterId);

    List<Shelter> findAll();

    @Query(value = "SELECT s FROM Room r JOIN Shelter s ON (s.id=r.shelter.id) WHERE r.id=:room_id")
    Optional<Shelter> findShelterByRoom(@Param("room_id") Long roomId);

    // ritorna l'id di rifugi che non hanno camere nella lista delle prenotazioni per le date selezionate
    //DA PROVARE!!!
    @Query(value = "SELECT DISTINCT (r.shelter_id) FROM room r WHERE r.id NOT IN (" +
                   " SELECT rr.room_id FROM reserved_rooms rr JOIN reservation res ON (rr.reservation_id = res.id) WHERE res.first_day=:date_start AND res.last_day=:date_end)", nativeQuery = true)
    List<Long> findSheltersIdByDate(@Param("date_start") Date dateStart, @Param("date_end") Date dateEnd);

    @Query(value = "SELECT s.shelter FROM Service s WHERE s.wifi=:wifi AND s.equipment=:equipment AND s.car=:car")
    List<Shelter> findShelterIdByServece(@Param("wifi") boolean wifi, @Param("equipment") boolean equipment, @Param("car") boolean car);

    @Query(value = "SELECT r.shelter FROM Room r WHERE r.price>=:min_price AND r.price<=:max_price")
    List<Shelter> findShelterByPrice(@Param("min_price") float minPrice, @Param("max_price") float maxPrice);

}
