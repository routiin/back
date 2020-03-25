package ru.podkovyrov.denis.routiin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.podkovyrov.denis.routiin.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
