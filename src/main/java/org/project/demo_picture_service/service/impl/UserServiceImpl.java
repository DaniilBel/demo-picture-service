package org.project.demo_picture_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.demo_picture_service.domain.exception.ResourceNotFoundException;
import org.project.demo_picture_service.domain.user.User;
import org.project.demo_picture_service.repository.UserRepository;
import org.project.demo_picture_service.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Cacheable(
            value = "UserService::getById",
            key = "#id"
    )
    public User getById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    @Cacheable(
            value = "UserService::getByUsername",
            key = "#username"
    )
    public User getByUsername(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with username " + username));
    }

    @Override
    @Transactional
    @Caching(put = {
            @CachePut(
                    value = "UserService::getById",
                    key = "#user.id"
            ),
            @CachePut(
                    value = "UserService::getByUsername",
                    key = "#user.username"
            )
    })
    public User update(final User user) {
        user.setUsername(user.getUsername());
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    @Caching(cacheable = {
            @Cacheable(
                    value = "UserService::getById",
                    condition = "#user.username!=null",
                    key = "#user.id"
            ),
            @Cacheable(
                    value = "UserService::getByUsername",
                    condition = "#user.username!=null",
                    key = "#user.username"
            )
    })
    public User create(final User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    @CacheEvict(
            value = "UserService::getById",
            key = "#id"
    )
    public void delete(final Long id) {
        userRepository.deleteById(id);
    }
}
