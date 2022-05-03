package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StructureRepository extends JpaRepository<Structure,Long> {

    Optional<Structure> findById(Long structureId);

    List<Structure> findAll();


    @Query(value = "SELECT DISTINCT (s) FROM Structure s WHERE s NOT IN (" +
            " SELECT s1 FROM Structure s1 JOIN Reservation res ON (s1.id = res.id)) AND s.type=0")
    List<Structure> findAllSheltersNotReserved();

    @Query(value = "SELECT DISTINCT (s) FROM Structure s WHERE s.id IN (" +
            " SELECT s1 FROM Structure s1 JOIN Reservation res ON (s1.id = res.id)) AND s.type=0")
    List<Structure> findAllSheltersReserved();

    @Query(value = "SELECT DISTINCT (s) FROM Structure s WHERE s.id NOT IN (" +
                   " SELECT s1 FROM Structure s1 JOIN Reservation res ON (s1.id = res.id) WHERE res.firstDay=:date_start AND res.lastDay=:date_end) AND s.type=:i")
    List<Structure> findSheltersNotReservedByDate(@Param("date_start") Date dateStart, @Param("date_end") Date dateEnd, int i);

    @Query(value = "SELECT DISTINCT (s) FROM Structure s WHERE s.id IN (" +
            " SELECT s1 FROM Structure s1 JOIN Reservation res ON (s1.id = res.id) WHERE res.firstDay=:date_start AND res.lastDay=:date_end) AND s.type=:i")
    List<Structure> findSheltersReservedByDate(@Param("date_start") Date dateStart, @Param("date_end") Date dateEnd, int i);

    @Query(value = "SELECT DISTINCT (s) FROM Structure s WHERE s.id NOT IN (" +
            " SELECT s1 FROM Structure s1 JOIN Reservation res ON (s1.id = res.id) WHERE res.firstDay=:date_start AND res.lastDay=:date_end) AND s.type=:i")
    List<Structure> findBivouacNotReservedByDate(@Param("date_start") Date dateStart, @Param("date_end") Date dateEnd, int i);

    @Query(value = "SELECT DISTINCT (s) FROM Structure s WHERE s.id IN (" +
            " SELECT s1 FROM Structure s1 JOIN Reservation res ON (s1.id = res.id) WHERE res.firstDay=:date_start AND res.lastDay=:date_end) AND s.type=:i")
    List<Structure> findBivouacsReservedByDate(@Param("date_start") Date dateStart, @Param("date_end") Date dateEnd, int i);

    @Query(value = "SELECT s.structure FROM Service s WHERE s.car=true")
    List<Structure> findShelterWithCar();
    @Query(value = "SELECT s.structure FROM Service s WHERE s.wifi=true")
    List<Structure> findShelterWithWifi();
    @Query(value = "SELECT s.structure FROM Service s WHERE s.equipment=true")
    List<Structure> findShelterWithEquipment();

    @Query(value = "SELECT s FROM Structure s WHERE s.price>=:min_price AND s.price<=:max_price AND s.type=:i")
    List<Structure> findShelterByPrice(@Param("min_price") float minPrice, @Param("max_price") float maxPrice, int i);

}
