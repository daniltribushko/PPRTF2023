package com.example.pprtf2023.services.imp;

import com.example.pprtf2023.exceptions.FileByIdNotFoundException;
import com.example.pprtf2023.exceptions.FileIsEmptyFileException;
import com.example.pprtf2023.exceptions.UserByUserNameNotFoundException;
import com.example.pprtf2023.exceptions.UserNotOwnerException;
import com.example.pprtf2023.models.entities.File;
import com.example.pprtf2023.models.entities.User;
import com.example.pprtf2023.repositopries.FileRepository;
import com.example.pprtf2023.repositopries.UserRepository;
import com.example.pprtf2023.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class FileServiceImp implements FileService {
    private static final String PATH = "src/main/resources/static/userfiles";
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImp(UserRepository userRepository,
                          FileRepository fileRepository){
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public String saveFile(MultipartFile multipartFile, String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserByUserNameNotFoundException(userName));
        String result = null;
        if (multipartFile.isEmpty()){
            throw new FileIsEmptyFileException();
        }
        try (InputStream inputStream = multipartFile.getInputStream()){
            String filePath = PATH + "/" + userName + "_" + user.getUserName() + ".txt";
            Path path = Path.of(filePath);
            File file = new File(filePath);
            user.addFile(file);
            fileRepository.save(file);
            result = filePath;
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Resource getFileById(Long id, String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserByUserNameNotFoundException(userName));
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new FileByIdNotFoundException(id));
        if (!user.getId().equals(file.getUser().getId())){
            throw new UserNotOwnerException(userName);
        }
        return new ClassPathResource("static/models/" +  userName + "_" + user.getUserName() + ".txt");
    }
}
