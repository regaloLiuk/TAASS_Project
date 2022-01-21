package com.example.mountbook_backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mountbook_backend.entity.OvernightStays;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


@Repository
public interface OvernightStaysRepository extends JpaRepository<OvernightStays,Long>{

    @Query(value = "SELECT os.id FROM OvernightStays os WHERE os.firstDay<=:first_day AND os.lastDay>=:last_day AND os.bivouac.id=:bivouac")
    public List<Long> existsRequestFromDateAndBivouac(@Param("first_day") Date firstDay, @Param("last_day") Date lastDay, @Param("bivouac") Long bivouac);

    @Query(value = "DELETE FROM OvernightStays os WHERE os.id<=:overnightStay_id AND os.user.id>=:user_id")
    public void deleteSign(@Param("overnightStay_id") Long overnightStayId, @Param("user_id") Long userId);

}
