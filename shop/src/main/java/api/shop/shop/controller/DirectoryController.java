package api.shop.shop.controller;

import api.shop.shop.model.Directory;
import api.shop.shop.service.DirectoryService;
import api.shop.shop.service.SubdirectoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/directory")
@AllArgsConstructor
public class DirectoryController {

    private final DirectoryService directoryService;
    private final SubdirectoryService subdirectoryService;

    @GetMapping(value = "/", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Directory> findAll() {
        return directoryService.findAll();
    }
}
