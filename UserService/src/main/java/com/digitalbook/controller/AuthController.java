package com.digitalbook.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.digitalbook.model.ERole;
import com.digitalbook.model.Role;
import com.digitalbook.model.User;
import com.digitalbook.payload.request.Book;
import com.digitalbook.payload.request.BookSubscribeRequest;
import com.digitalbook.payload.request.LoginRequest;
import com.digitalbook.payload.request.Reader;
import com.digitalbook.payload.request.SignupRequest;
import com.digitalbook.payload.response.BookSubscribeResponse;
import com.digitalbook.payload.response.JwtResponse;
import com.digitalbook.payload.response.MessageResponse;
import com.digitalbook.repository.RoleRepository;
import com.digitalbook.repository.UserRepository;
import com.digitalbook.security.jwt.JwtUtils;
import com.digitalbook.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	Logger logger;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "author":
					Role authorRole = roleRepository.findByName(ERole.ROLE_AUTHOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(authorRole);

					break;
				case "guest":
					Role guestRole = roleRepository.findByName(ERole.ROLE_GUEST)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(guestRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping(value = "/author/createbook")
	public String createBook(@RequestBody Book  book   ) {
		System.out.println(book);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<Book> entity = new HttpEntity<Book>(book, headers);
		return restTemplate.exchange
				("http://localhost:8092/api/book/author/createbook", 
				HttpMethod.POST, entity, String.class).getBody();
	}
	
	@GetMapping(value = "/searchbooks")
	public ResponseEntity<?> searchBooks(
			 @Nullable @RequestParam("title") String title,	
			 @Nullable @RequestParam("authorName") String authorName, 
			 @Nullable @RequestParam("category") String category,
			 @Nullable @RequestParam("publisher") String publisher ) {
		  
		String uri = "http://localhost:8092/api/book/searchbooks?category="+category+"&title="+title+"&publisher="+publisher+"&authorName="+authorName;
		ResponseEntity<?> result = restTemplate.getForEntity(uri, List.class);
		if(result.getBody() == null)
			return ResponseEntity.badRequest().body("Invalid");
		List<Book> books = (List<Book>) result.getBody();
		if(books == null || books.isEmpty()) return ResponseEntity.badRequest().body("Invalid");
		return ResponseEntity.ok(books);
	}
	
	@PostMapping(value = "/{reader-id}/subscribe")
	public String subscribeBook(@PathVariable("reader-id") int readerId,@RequestBody BookSubscribeRequest  book) {
		String url = "http://localhost:8092/api/book/"+readerId+"/subscribe";
		//System.out.println(book);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<BookSubscribeRequest> entity = new HttpEntity<BookSubscribeRequest>(book, headers);
		return restTemplate.exchange
				(url,HttpMethod.POST, entity, String.class).getBody();
	}
	
	@PostMapping(value = "/readers/{reader-id}/books/{subscription-id}/unsubscribe")
	public String cancelSubscribeBook(@PathVariable("reader-id") long readerId
			,@PathVariable("subscription-id") long subscriptionId) {
		String url = "http://localhost:8092/api/book/readers/"+readerId+"/books/"+subscriptionId+"/unsubscribe";
		BookSubscribeResponse res=new BookSubscribeResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<BookSubscribeResponse> entity = new HttpEntity<BookSubscribeResponse>(res, headers);
		return restTemplate.exchange
				(url,HttpMethod.POST, entity, String.class).getBody();
	}
	
	@GetMapping(value = "/reader/getallbooks")
	public ResponseEntity<?> getALLBooks() {
		String url = "http://localhost:8092/api/book/reader/getallbooks";
		ResponseEntity<?> result = restTemplate.getForEntity(url, List.class);
		List<Book> books = (List<Book>) result.getBody();
		return ResponseEntity.ok(books);
	}
	
	@GetMapping(value = "/reader/{reader}/getsubscribeddetails")
	public ResponseEntity<?> getSubscribedDetails(@PathVariable("reader") String subscriberName) {
		System.out.println(subscriberName);
		String url = "http://localhost:8092/api/book/reader/"+subscriberName+"/getsubscribeddetails";
		ResponseEntity<?> result = restTemplate.getForEntity(url, List.class);
		List<Reader> books = (List<Reader>) result.getBody();
		return ResponseEntity.ok(books);
	}
	
	@GetMapping(value = "/author/{author}/getAuthorBooks")
	public ResponseEntity<?> getAuthorBooks(@PathVariable("author") String authorName) {
		String url = "http://localhost:8092/api/book/author/"+authorName+"/getAuthorBooks";
		ResponseEntity<?> result = restTemplate.getForEntity(url, List.class);
		List<Book> books = (List<Book>) result.getBody();
		return ResponseEntity.ok(books);
	}
	
	

}
