package ru.data.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.data.dao.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
}
