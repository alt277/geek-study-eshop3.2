package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.service.PictureService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/picture")
public class PictureController {

    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{pictureId}") // просто загружает картинку, а не возвращает представление по маппингу
    public void downloadProductPicture(@PathVariable("pictureId") Long pictureId, HttpServletResponse resp) throws IOException {
        logger.info("Downloading picture with id: {}", pictureId);

        Optional<String> opt = pictureService.getPictureContentTypeById(pictureId);
                                    // проверяем есть ли такая картинка в базе
        if (opt.isPresent()) {      //  если есть
            resp.setContentType(opt.get()); //в ответ на запрос помещаем какого типа возвращаемая информация картинки
                                          //в поток вывода пишем бинарные данные картинкм
            resp.getOutputStream().write(pictureService.getPictureDataById(pictureId).get());
        } else {                   // если нет кидаем 404 ошибку
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
