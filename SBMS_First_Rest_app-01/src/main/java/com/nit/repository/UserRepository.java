package com.nit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nit.entity.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Integer>
{

}
