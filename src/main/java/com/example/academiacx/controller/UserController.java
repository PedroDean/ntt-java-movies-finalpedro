    package com.example.academiacx.controller;

    import com.example.academiacx.facades.inter.UserFacade;
    import com.example.academiacx.models.DirectorModel;
    import com.example.academiacx.models.MovieModel;
    import com.example.academiacx.models.UserModel;
    import com.example.academiacx.models.dto.ResultDTO;
    import com.example.academiacx.models.dto.UserDTO;
    import com.example.academiacx.services.inter.DirectorService;
    import com.example.academiacx.services.inter.MovieService;
    import com.example.academiacx.services.inter.UserService;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping(value = "/users")
    public class UserController {

        @Autowired
        private UserService userService;


        @Autowired
        private MovieService movieService;
        @Autowired
        private DirectorService directorService;
        @Autowired
        private UserFacade userFacade;

        @Autowired
        private ModelMapper modelMapper;

        @GetMapping(value = "/list")
        public ResponseEntity<?> findAll() {
            List<UserDTO> response = userFacade.listUsers();
            if (response.isEmpty()) {
                ResultDTO errorResult = new ResultDTO("Não há usuários cadastrados!", 422);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResult);
            } else {
                return ResponseEntity.ok(response);
            }
        }


        @GetMapping("/{id}")
        public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
            Optional<UserModel> user = userService.findById(id);

            if (user.isPresent()) {
                UserDTO userDTO = modelMapper.map(user.get(), UserDTO.class);
                return ResponseEntity.ok(userDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping(value = "/create")
        public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
            UserDTO response = userFacade.create(userDTO);
            return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
        }
        @PutMapping("/{userId}/add-favorite-movie/{movieId}")
        public ResponseEntity<?> addFavoriteMovieToUser(@PathVariable Long userId, @PathVariable Long movieId) {
            return userFacade.addFavoriteMovie(userId, movieId);
        }

        @PutMapping("/{userId}/add-favorite-director/{directorId}")
        public ResponseEntity<?> addFavoriteDirectorToUser(@PathVariable Long userId, @PathVariable Long directorId) {
            return userFacade.addFavoriteDirector(userId, directorId);
        }
        @PutMapping(value = "/update")
        public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
            UserDTO response = userFacade.update(userDTO);
            return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
        }
        @DeleteMapping("delete/{id}")
        public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
            boolean success = userFacade.deleteById(id);
            return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
        }

    }
