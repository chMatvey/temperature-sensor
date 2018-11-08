package ru.chudakov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chudakov.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
