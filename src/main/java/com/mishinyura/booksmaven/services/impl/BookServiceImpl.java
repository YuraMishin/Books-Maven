package com.mishinyura.booksmaven.services.impl;

import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.entities.Book;
import com.mishinyura.booksmaven.repositories.BookRepository;
import com.mishinyura.booksmaven.services.BookService;
import com.mishinyura.booksmaven.utils.constants.MainConstants;
import com.mishinyura.booksmaven.utils.exceptions.BookNotCreatedException;
import com.mishinyura.booksmaven.utils.exceptions.BookNotFoundException;
import com.mishinyura.booksmaven.utils.exceptions.BookNotFoundExceptionMVC;
import com.mishinyura.booksmaven.utils.files.FileUploadUtil;
import com.mishinyura.booksmaven.utils.validators.BookValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final BookValidator bookValidator;
    private static final int BOOKS_PER_PAGE = 2;

    @Override
    public Long getBooksCount() {
        return bookRepository.count();
    }

    @Override
    public List<BookResDto> findAllBooks() {
        var books = bookRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );
        books.forEach(book -> book.setPhotos(getPath(book)));
        return modelMapper
                .map(books, new TypeToken<List<BookResDto>>() {
                }.getType());
    }

    private String getPath(Book book) {
        var path = new StringBuilder()
                .append("/")
                .append(MainConstants.BOOK_PHOTOS)
                .append("/")
                .append(book.getId())
                .append("/")
                .append(book.getPhotos())
                .append("/")
                .toString();
        if (book.getId() == null || book.getPhotos() == null) {
            path = "/img/default-book.png";
        }
        return path;
    }

    @Transactional
    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public BookResDto findBookById(Long id) {
        var bookFound = bookRepository.findById(id);
        var book = bookFound.orElseThrow(BookNotFoundException::new);
        return modelMapper.map(book, BookResDto.class);
    }

    @Override
    public String findBookByIdMVC(Model model, Long id, String page) {
        var bookFound = bookRepository.findById(id);
        if (bookFound.isPresent()) {
            var book = bookFound.get();
            book.setPhotos(getPath(book));

            var bookResDto = modelMapper.map(book, BookResDto.class);

            model.addAttribute("book", bookResDto);
            model.addAttribute("pageTitle", String.format("Edit Book (ID: %s)", id));
            return page;
        }
        throw new BookNotFoundExceptionMVC("Book not found !");
    }

    @Transactional
    @Override
    public void createBook(BookReqDto book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new BookNotCreatedException(errorMsg.toString().trim());
        }
        var bookToSave = modelMapper.map(book, Book.class);
        bookRepository.save(bookToSave);
    }

    @Transactional
    @Override
    public String createBookMVC(
            BookReqDto book,
            BindingResult bindingResult,
            MultipartFile multipartFile) {

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "book/new";
        }

        var bookToSave = modelMapper.map(book, Book.class);
        var fileName = "default-book.png";

        if (!multipartFile.isEmpty()) {
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            bookToSave.setPhotos(fileName);
            var bookSaved = bookRepository.save(bookToSave);
            var uploadDir = Paths.get(
                            MainConstants.BOOK_PHOTOS,
                            bookSaved.getId().toString())
                    .toString();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            bookToSave.setPhotos(fileName);
            bookRepository.save(bookToSave);
        }
        return "redirect:/books/";
    }

    @Transactional
    @Override
    public void updateBook(Long id, BookReqDto book, MultipartFile multipartFile) {
        bookRepository.findById(id)
                .ifPresent(bookToSave -> {
                    modelMapper.map(book, bookToSave);
                    if (!multipartFile.isEmpty()) {
                        var fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                        bookToSave.setPhotos(fileName);

                        var uploadDir = Paths.get(
                                        MainConstants.BOOK_PHOTOS,
                                        bookToSave.getId().toString())
                                .toString();
                        try {
                            FileSystemUtils.deleteRecursively(Paths.get(uploadDir));
                        } catch (IOException e) {
                            log.error(
                                    "{} - Exception caught - {}",
                                    LocalDateTime.now(),
                                    "IOException! Could not clean folder:" + uploadDir
                            );
                        }
                        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
                    }
                    bookRepository.save(bookToSave);
                });
    }

    @Transactional
    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<BookReqDto> findBookByTitle(String title) {
        var bookFound = bookRepository.findByTitle(title);
        if (bookFound.isPresent()) {
            return Optional.of(modelMapper.map(bookFound.get(), BookReqDto.class));
        }
        return Optional.empty();
    }

    @Override
    public boolean isBookUnique(String title) {
        return Objects.isNull(bookRepository.getBookByTitle(title));
    }

    @Transactional
    @Override
    public void updateBookEnabledStatus(Long id, boolean enabled) {
        bookRepository.updateBookEnabledStatus(id, enabled);
    }

    @Override
    public Page<Book> findAllBooksByPage(int pageNum) {
        var pageable = PageRequest.of(pageNum - 1, BOOKS_PER_PAGE);
        var books = bookRepository.findAll(pageable);
        return books;
    }
}
