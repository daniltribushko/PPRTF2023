package com.example.pprtf2023.repositopries;

import com.example.pprtf2023.models.entities.File;
import com.example.pprtf2023.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findAllByUser(User user);
}
