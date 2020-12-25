package com.example.study.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.model.entity.User;

@Repository
// JpaRepository<TableName(Entity), FK type(id)>
public interface UserRepository extends JpaRepository<User,  Long>{

	// SELECT * FROM user WHERE account = ? << test03, test04
	Optional<User> findByAccount(String account);

	Optional<User> findByEmail(String email);

	// SELECT * FROM user WHERE account = ? and email = ?
	Optional<User> findByAccountAndEmail(String account, String email);


}
