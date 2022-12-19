package ru.kharina.study.springbootwebbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kharina.study.springbootwebbank.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
