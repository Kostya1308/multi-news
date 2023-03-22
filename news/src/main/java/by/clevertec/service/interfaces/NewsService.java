package by.clevertec.service.interfaces;

import by.clevertec.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface NewsService extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n WHERE n.title LIKE %:key%")
    Page<News> findAllByTitleContainsPageable(@Param("key") String key, Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.dateTimeCreate < :dateTime")
    Page<News> findAllByDateTimeCreateLessThanPageable(@Param("dateTime") LocalDateTime dateTime, Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.dateTimeCreate > :dateTime")
    Page<News> findAllByDateTimeCreateGreaterThanPageable(@Param("dateTime") LocalDateTime dateTime, Pageable pageable);
}