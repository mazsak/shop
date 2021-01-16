package api.shop.shop.controller;

import api.shop.shop.model.Directory;
import api.shop.shop.model.Subdirectory;
import api.shop.shop.service.DirectoryService;
import api.shop.shop.service.SubdirectoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    ResponseEntity<List<Directory>> findAll() {
        return ResponseEntity.ok(directoryService.findAll());
    }

    @PostMapping(value = "/", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Directory> createDirectory(@RequestBody Directory directory) {
        try {
            directoryService.save(directory);
            return ResponseEntity.created(new URI("/directory/" + directory.getId())).body(directory);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Directory> getDirectory(@PathVariable final Long id) {
        try {
            Directory directory = directoryService.findById(id);
            return ResponseEntity.ok(directory);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Directory> updateDirectory(@RequestBody Directory directory, @PathVariable final Long id) {
        try {
            directory.setId(id);
            directoryService.save(directory);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Directory> removeDirectory(@PathVariable final Long id) {
        try {
            boolean removed = directoryService.deleteById(id);
            if (removed) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/subdirectory", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Subdirectory> findSubdirectoryAll() {
        return subdirectoryService.findAll();
    }

    @PostMapping(value = "/subdirectory", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Subdirectory> createSubdirectory(@RequestBody Subdirectory subdirectory) {
        try {
            subdirectoryService.save(subdirectory);
            return ResponseEntity.created(new URI("/directory/" + subdirectory.getId())).body(subdirectory);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/subdirectory/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Subdirectory> getSubdirectory(@PathVariable final Long id) {
        try {
            Subdirectory subdirectory = subdirectoryService.findById(id);
            return ResponseEntity.ok(subdirectory);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping(value = "/subdirectory/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Subdirectory> updateSubdirectory(@RequestBody Subdirectory subdirectory, @PathVariable final Long id) {
        try {
            subdirectory.setId(id);
            subdirectoryService.save(subdirectory);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "/subdirectory/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Subdirectory> removeSubdirectory(@PathVariable final Long id) {
        try {
            boolean removed = subdirectoryService.deleteById(id);
            if (removed) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
