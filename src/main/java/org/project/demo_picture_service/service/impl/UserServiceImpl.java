package org.project.demo_picture_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.demo_picture_service.domain.exception.ResourceNotFoundException;
import org.project.demo_picture_service.domain.user.User;
import org.project.demo_picture_service.repository.UserRepository;
import org.project.demo_picture_service.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public User getByUsername(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with username " + username));
    }

    @Override
    @Transactional
    public User update(final User user) {
        user.setUsername(user.getUsername());
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public User create(final User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        userRepository.deleteById(id);
    }
}
