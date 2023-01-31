package com.fundamentals.springboot.repository;

import com.fundamentals.springboot.dto.UserDto;
import com.fundamentals.springboot.entity.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("select u from User u where u.email=?1")
  Optional<User> findByUserEmail(String email);

  @Query("select u from User u where u.name like ?1%")
  List<User> findAndSort(String name, Sort sort);

  List<User> findByName(String name);

  Optional<User> findByEmailAndName(String email, String name);

  List<User> findByNameLike(String name);

  List<User> findByNameOrEmail(String name, String email);

  List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

  List<User> findByNameContainingOrderByIdDesc(String name);

  @Query("select new com.fundamentals.springboot.dto.UserDto(u.id, u.name, u.birthDate)" +
      " from User u" +
      " where u.birthDate=:dateParameter" +
      " and u.email=:emailParameter")
  Optional<List<UserDto>> getAllByBirthDateAndEmail(@Param("dateParameter") LocalDate date,
      @Param("emailParameter") String email);
}
