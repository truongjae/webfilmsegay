package com.truongjae.webfilmsegay.repository;

import com.truongjae.webfilmsegay.entity.Role;
import com.truongjae.webfilmsegay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    List<User> findAll();
    User findOneById(Long id);
    Boolean existsByUsername(String username);
    User findOneByUsername(String username);
}
