package ru.kharina.study.springbootwebbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kharina.study.springbootwebbank.dto.TransactionDto;

import ru.kharina.study.springbootwebbank.model.Transaction;
import ru.kharina.study.springbootwebbank.model.User;
import ru.kharina.study.springbootwebbank.repository.TransactionRepository;
import ru.kharina.study.springbootwebbank.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TransactionDto> getAllTransactionsDto() {
        return transactionRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private TransactionDto convertEntityToDto(Transaction transaction){
        TransactionDto userLocationDTO = new TransactionDto();
        userLocationDTO.setId(transaction.getId());
        userLocationDTO.setSumm(transaction.getSumm());
        userLocationDTO.setDate(transaction.getDate());
        userLocationDTO.setMessage(transaction.getMessage());
        userLocationDTO.setSender_id(transaction.getSender().getId());
        userLocationDTO.setReceipient_id(transaction.getReceipient().getId());
        return userLocationDTO;
    }

    @Override
    public void saveTransactionDto(TransactionDto dto){
        Transaction transaction = new Transaction();
        Date date = new Date();
        transaction.setDate(date);
        System.out.println(date);
        transaction.setSumm(dto.getSumm());
        transaction.setMessage(dto.getMessage());
        User sender = userRepository.getById(dto.getSender_id());
        sender.setSumm((int)(sender.getSumm()-dto.getSumm()));
        transaction.setSender(sender);
        User receipient = userRepository.getById(dto.getReceipient_id());
        receipient.setSumm((int)(receipient.getSumm()+dto.getSumm()));
        transaction.setReceipient(receipient);
        transactionRepository.save(transaction);
        userRepository.save(sender);
        userRepository.save(receipient);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public TransactionDto getTransactionById(int id) {
        return convertEntityToDto(transactionRepository.getById(id));
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
