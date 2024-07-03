package com.stb.politik.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stb.politik.security.AuthRequest;
import com.stb.politik.security.jwt.JwtService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the backend";
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestBody AuthRequest authRequest) {
        log.info("UserController.loginUser - authenticate: " + authRequest.getUsername() + " " + authRequest.getPassword());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }
    
    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('admin')")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUsers/{id}")
    // @PreAuthorize("hasAuthority('citizen')")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/username")
    public Optional<User> getUserByUsername(@RequestBody String username) {
        log.info("UserController.getUserByUsername - user" + this.userService.getUserByUsername(username));
        return userService.getUserByUsername(username);
    }

    @PostMapping("/phone")
    public User getUserByPhone(@RequestBody String phone) {
        return userService.getUserByPhone(phone);
    }

    @PostMapping("/email")
    public User getUserByEmail(@RequestBody String email) {
        return userService.getUserByEmail(email);
    }
    


























    // @PostMapping("/")
    // public User saveUser(@RequestBody User user) throws Exception {
    //     Set<UserRol> userRoles = new HashSet<>();

    //     Rol rol = new Rol();
    //     rol.setId(2L);
    //     rol.setName("citizen");

    //     UserRol userRol = new UserRol();
    //     userRol.setRol(rol);
    //     userRol.setUser(user);

    //     userRoles.add(userRol);
        
    //     return userService.saveUser(user, userRoles);
    // }

    // @GetMapping("/{username}")
    // public User getUser(@PathVariable("username") String username) {
    //     return userService.getUser(username);
    // }

    // @DeleteMapping("/{userId}")
    // public void deleteUser(@PathVariable("userId") Long userId) {
    //     userService.deleteUser(userId);
    // }

    














    // @Autowired
    // private CredentialsService credentialsService;

    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder;

    // @GetMapping("")
    // public List<User> getAllUsers() {
    //     return userService.getAllUsers();
    // }

    // @PostMapping("/phone")
    // public User getUserByPhone(@RequestBody String phone) {
    //     return userService.getUserByPhone(phone);
    // }

    // @PostMapping("/email")
    // public User getUserByEmail(@RequestBody String email) {
    //     return userService.getUserByEmail(email);
    // }

    // @PostMapping("/username")
    // public User getUserByUsername(@RequestBody String username) {
    //     System.out.println("UserController.getUserByUsername - created_at" + this.userService.getUserByUsername(username).getCreatedAt());
    //     return this.userService.getUserByUsername(username);
    // }

    // @PostMapping("/signup")
    // public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
    //     User user = new User(); // creo el user
    //     user.setUsername(userDTO.getUsername());
    //     user.setRol(userDTO.getRol());
    //     user.setName(userDTO.getName());
    //     user.setLastname(userDTO.getLastname());
    //     // esto es para que si el campo está vacío, no se guarde en la db, ya que si
    //     // está vacío es '' y sería duplicado
    //     user.setPhone(userDTO.getPhone().isEmpty() ? null : userDTO.getPhone());
    //     user.setEmail(userDTO.getEmail().isEmpty() ? null : userDTO.getEmail());
    //     user.setBirthday(userDTO.getBirthday());
    //     // Configurar la fecha y hora actual
    //     LocalDateTime currentDateTime = LocalDateTime.now();
    //     user.setCreatedAt(currentDateTime); // Establecer la fecha y hora actual en el usuario

    //     // guardo el user en la db
    //     this.userService.saveUser(user);

    //     // //Ahora he de hashear la password
    //     String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
    //     // creo las credenciales y las seteo
    //     Credentials credentials = new Credentials();
    //     // como el user ya tiene id (pq ha sido saved en la db), se lo seteo a las
    //     // credenciales
    //     credentials.setUser(user);
    //     credentials.setPasswordHash(hashedPassword);
    //     // guardo las credenciales en la db
    //     this.credentialsService.saveCredentials(credentials);

    //     // Generar un token de autenticación
    //     String jwt = JwtUtils.generateToken(user.getUsername()); // modifieeeed 090121---------------------

    //     // Devolver el token al cliente
    //     return ResponseEntity.ok(new AuthenticationResponse(jwt, user));
    // }

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    //     String password = loginRequest.getPassword();
    //     // en funcion de username:
    //     String username = loginRequest.getUsername();
    //     // int index = loginRequest.getIndex(); //en teoria ya no hace falta

    //     User user = null;
    //     user = this.userService.getUserByUsername(username); // busco el user con este username
    //     if (user == null) {
    //         log.info("login() - Backend - No existe un usuario con ese username.");
    //         return new ResponseEntity<>("Invalid username", HttpStatus.UNAUTHORIZED);
    //     }

    //     // obtengo las credentials de dicho user
    //     Credentials credentials = this.credentialsService.getCredentialsByUser(user);
    //     if (credentials == null) {
    //         log.info("login() - Backend - No credentials found for user");
    //         return new ResponseEntity<>("No credentials found for user", HttpStatus.UNAUTHORIZED);
    //     }
    //     // obtengo la password de dicho username
    //     String pwHash = credentials.getPasswordHash();
    //     log.info("credentials - pwHash del user:" + user.getUsername() + " = " + pwHash);

    //     // Verificar la contraseña (introducida vs la de dicho user)
    //     boolean passwordMatches = passwordEncoder.matches(password, pwHash);
    //     if (!passwordMatches) {
    //         log.info("login() - Backend - contraseña incorrecta.");
    //         return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
    //     } else {
    //         log.info("login() - Backend - contraseña correcta.");

    //         // Generar un token JWT
    //         String jwt = JwtUtils.generateToken(user.getUsername()); // modifieeeed 090221 ---------------------
    //         log.info("login() - Backend - jwt generado: " + jwt);

    //         // Devolver el token al cliente
    //         return ResponseEntity.ok(new AuthenticationResponse(jwt, user));
    //     }
    // }


    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    //     log.info("UserController.login - loginRequest: " + loginRequest);
    //     try {
    //         return this.userService.login(loginRequest);
    //     } catch (Exception e) {
    //         log.error("UserController.login - Exception: " + e.getMessage());
    //     }
    //     return new ResponseEntity<>("Error en el loginnnn", HttpStatus.INTERNAL_SERVER_ERROR);
    // }

}