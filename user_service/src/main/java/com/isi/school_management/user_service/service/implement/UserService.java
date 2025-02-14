package com.isi.school_management.user_service.service.implement;



import com.isi.school_management.user_service.dto.UserDto;
import com.isi.school_management.user_service.entity.UserEntity;
import com.isi.school_management.user_service.mapper.UserMapper;
import com.isi.school_management.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers(){
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream() //flux de valeur
                .map(UserMapper.INSTANCE ::userEntityToUserDto) //mapp
                .collect(Collectors.toList());
    }
    public UserDto creatUser(UserDto userDto){
        UserEntity userEntity = UserMapper.INSTANCE.userDtoToUserEntity(userDto);
        UserEntity savedUserEntity =userRepository.save(userEntity);
        return UserMapper.INSTANCE.userEntityToUserDto(savedUserEntity);
    }
    public UserDto getUserById(Long id) {
         UserEntity userEntity=userRepository.findById(id).orElse(null);
            if (userEntity == null) {
                return null; // Ou lance une ResourceNotFoundException ici
            }
          return UserMapper.INSTANCE.userEntityToUserDto(userEntity);
    }

    public UserDto updateUser(UserDto userDto, long Id){
        UserEntity excist_userEntity=userRepository.findById(Id).orElse(null);
        System.out.print("😊😊😊😊😊😊😊😊👍" + excist_userEntity);
        if (excist_userEntity == null) {
            return null; // Ou lance une ResourceNotFoundException ici
        }

        UserMapper.INSTANCE.updateUserEntityFromUserDto(userDto,excist_userEntity);
        UserEntity userEntityUpdate=userRepository.save(excist_userEntity);
        return UserMapper.INSTANCE.userEntityToUserDto(userEntityUpdate);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }




}
