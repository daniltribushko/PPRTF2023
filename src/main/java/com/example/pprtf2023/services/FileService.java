package com.example.pprtf2023.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Сервис для работы с файлами, которые будут содержать инструкции по построению моделей
 */
public interface FileService {
    /**
     * Сохранение файла
     * @param multipartFile файл
     * @param userName имя пользователя
     */
    String saveFile(MultipartFile multipartFile, String userName);

    /**
     * Получение файла по имени
     * @param id идентификатор файла
     * @param userName имя пользователя
     * @return ресурс файла
     */
    Resource getFileById(Long id, String userName);
}
