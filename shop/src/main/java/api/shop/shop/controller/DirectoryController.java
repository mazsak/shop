package api.shop.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/directory")
public class DirectoryController {

    @GetMapping(value = "/", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<String> findAll(){
        return Arrays.asList("elektronika", "samochody");
    }
}
