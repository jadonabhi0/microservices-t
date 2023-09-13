package com.user.service.repositary;/*
    @author jadon
*/

import com.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary extends JpaRepository<User, String> {

}
