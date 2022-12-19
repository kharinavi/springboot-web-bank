package ru.kharina.study.springbootwebbank.service;

import ru.kharina.study.springbootwebbank.dto.TransactionDto;
import ru.kharina.study.springbootwebbank.model.User;

import java.util.List;

public interface UserService {
    List<TransactionDto> getAllTransactionsDto();

    void saveTransactionDto(TransactionDto dto);

    List<User> getAllUsers();

    User getUserById(int id);

    TransactionDto getTransactionById(int id);

    void saveUser(User user);

    void deleteUser(int id);
}
