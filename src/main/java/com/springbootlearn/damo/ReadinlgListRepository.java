package com.springbootlearn.damo;

import com.springbootlearn.damo.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadinlgListRepository extends JpaRepository<Book,Long> {
    List<Book> findByReader(String reader);
}
