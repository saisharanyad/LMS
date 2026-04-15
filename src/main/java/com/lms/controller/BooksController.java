package com.lms.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.lms.constants.BooksConstants;
import com.lms.dto.BookResponseDTO;
import com.lms.dto.BooksDTO;
import com.lms.dto.ResponseDto;
import com.lms.service.BooksService;


import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "/api/books")
public class BooksController {
	
	private static final Logger log = LoggerFactory.getLogger(BooksController.class);
	
	private BooksService iBooksService;
	
	@Autowired
	public BooksController(BooksService iBooksService) {
		this.iBooksService = iBooksService;
	}

	
	@PostMapping(path = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody BooksDTO booksDto) {
		
		iBooksService.createBook(booksDto);
		
		return  ResponseEntity.
				status(HttpStatus.CREATED).body(new ResponseDto(BooksConstants.STATUS_201,
						BooksConstants.MESSAGE_201));
	}
	
	
	@PutMapping("/update/bookavailability")
    public ResponseEntity<ResponseDto> updateBookAvailability(@RequestParam long bookId,
    		@RequestParam String availabilityStatus) {
        boolean isUpdated = iBooksService.updateBookAvailabilityStatus(bookId,availabilityStatus);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(BooksConstants.STATUS_200, BooksConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(BooksConstants.STATUS_500, BooksConstants.MESSAGE_500_UPDATE));
        }
    }
	
	
	@DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteBook(@RequestParam
                                                     long bookId) {
        boolean isDeleted = iBooksService.deleteBook(bookId);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(BooksConstants.STATUS_200, BooksConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(BooksConstants.STATUS_500, BooksConstants.MESSAGE_500_DELETE));
        }
    }
	
	
	@GetMapping
    public ResponseEntity<BookResponseDTO> searchBooks(@RequestParam(required=false) 
    		String partialSearchText,
    		@RequestParam(required=false) String title,
    		@RequestParam(required=false) String author,
    		@RequestParam(required=false,defaultValue="-1") Integer publishedYear,
    		@RequestParam(defaultValue="0") Integer page,
    		@RequestParam(defaultValue="10") Integer size) {
		
		log.info("partialSearchText ::: "+partialSearchText);
		log.info("title ::: "+title);
		log.info("author ::: "+author);
		log.info("publishedYear ::: "+publishedYear);
		log.info("page ::: "+page);
		log.info("size ::: "+size);
		
		return ResponseEntity
                .status(HttpStatus.OK)
                .body(iBooksService.searchBooks(partialSearchText,title,author,
                		publishedYear,page,size));
   
       
    }

	
}
