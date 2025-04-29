package com.formation.platform.service;
import static com.formation.platform.mapper.UserMapper.toDto;
import static com.formation.platform.mapper.UserMapper.toEntity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.formation.platform.dto.UserDto;
import com.formation.platform.mapper.UserMapper;
import com.formation.platform.model.User;
import com.formation.platform.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    public UserDto create(UserDto dto) {
        User user = toEntity(dto);
        return toDto(userRepository.save(user));
    }

    public UserDto getById(Long id) {
        return toDto(userRepository.findById(id).orElseThrow());
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
