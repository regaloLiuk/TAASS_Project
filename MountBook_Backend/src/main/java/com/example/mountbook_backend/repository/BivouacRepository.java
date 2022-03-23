package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Bivouac;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


@Repository
public interface BivouacRepository extends JpaRepository<Bivouac,Long>{

    public Optional<Bivouac> findByName(String name);

    @Query(value = "SELECT DISTINCT b FROM Bivouac b WHERE b.id NOT IN (SELECT os.bivouac.id FROM Reservation os)")
    public List<Bivouac> findAllWithoutRequest();

    @Query(value = "SELECT DISTINCT b FROM Bivouac b WHERE b.id IN (SELECT os.bivouac.id FROM Reservation os)")
    public List<Bivouac> findAllWithRequest();

    @Query(value =  "SELECT DISTINCT b FROM Bivouac b WHERE b.id NOT IN (" +
                    "   SELECT b1 FROM Bivouac b1 JOIN Reservation os ON (b1.id=os.bivouac.id) WHERE os.firstDay<=:date_start AND os.lastDay>=:date_end)")
    public List<Bivouac> findWithoutRequestForDate(@Param("date_start") Date dateStart, @Param("date_end") Date dateEnd);

    @Query(value =  "SELECT DISTINCT b FROM Bivouac b WHERE b.id IN (" +
            "   SELECT b1 FROM Bivouac b1 JOIN Reservation os ON (b1.id=os.bivouac.id) WHERE os.firstDay<=:date_start AND os.lastDay>=:date_end)")
    public List<Bivouac> findWithRequestForDate(@Param("date_start") Date dateStart, @Param("date_end") Date dateEnd);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Bivouac b SET b.isOpen=:state")
    public void updateOpenState(@Param("state") boolean state);


}
