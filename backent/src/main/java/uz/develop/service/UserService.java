package uz.develop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.develop.entity.UserEntity;
import uz.develop.payload.ApiResponse;
import uz.develop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ApiResponse addUser(UserEntity user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            UserEntity userEntity = userRepository.save(user);
            return new ApiResponse("User saqlandi", true, userEntity);
        } else {
            return new ApiResponse("Bu user allaqachon mavjud", false);
        }
    }

    public ApiResponse editUser(UserEntity body) {
        Optional<UserEntity> repositoryById = userRepository.findById(body.getId());
        if (repositoryById.isPresent()) {
            UserEntity oldUser = repositoryById.get();
            oldUser.setEmail(body.getEmail());
            oldUser.setPassword(body.getPassword());
            oldUser.setName(body.getName());
            userRepository.save(oldUser);
            return new ApiResponse("User malumotlari almashtirildi", true, oldUser);
        } else return new ApiResponse("Bunday id lik user topilmadi", false);
    }

    public ApiResponse deleteUser(Integer id) {
        boolean existsById = userRepository.existsById(id);
        if (existsById) {
            userRepository.deleteById(id);
            return new ApiResponse("User o`chirildi", true, id);
        } else return new ApiResponse("Bunday user mavjud emas", false);
    }

    public ApiResponse getUsers() {
        List<UserEntity> userRepositoryAll = userRepository.findAll();
        if (!userRepositoryAll.isEmpty()) {
            return new ApiResponse("Userlar ro`yxati", true, userRepositoryAll);
        } else return new ApiResponse("User mavjud emas", false, null);
    }

    public UserEntity getUser(Integer id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        return byId.orElse(null);
    }
}
