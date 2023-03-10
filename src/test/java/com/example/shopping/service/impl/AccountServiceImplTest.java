
package com.example.shopping.service.impl;

import static org.junit.Assert.assertEquals;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.shopping.entity.Account;
import com.example.shopping.model.ResponseObject;
import com.example.shopping.model.dto.AccountDTORequest;
import com.example.shopping.model.dto.AccountDTOResponse;
import com.example.shopping.repository.AccountRepository;
import com.example.shopping.utils.JwtTokenUtils;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private JwtTokenUtils jwtTokenUtils;

    @Test
    public void testLogin_Success() {
        // Arrange
        AccountDTORequest account = new AccountDTORequest();
        account.setUsername("testuser");
        account.setPassword("testpass");

        Account acc = new Account();
        acc.setUsername("testuser");
        acc.setPassword("testpass");

        Mockito.when(accountRepository.findAccountByUsername("testuser")).thenReturn(Optional.of(acc));

        // Mockito.when(jwtTokenUtils.generateToken(acc, 24 * 60 *
        // 60)).thenReturn("testtoken");

        // Act
        ResponseEntity<ResponseObject> response = accountServiceImpl.login(account);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("500", response.getBody().getStatus());
        assertEquals("login successfully", response.getBody().getMessage());
        AccountDTOResponse result = (AccountDTOResponse) response.getBody().getData();
        assertEquals("testuser", result.getUsername());
        assertEquals(null, result.getToken());
    }

    @Test
    public void testLogin_Failed() {
        // Arrange
        AccountDTORequest account = new AccountDTORequest();
        account.setUsername("testuser");
        account.setPassword("testpass");

        Account acc = new Account();
        acc.setUsername("testuser");
        acc.setPassword("testpass");

        Mockito.when(accountRepository.findAccountByUsername("testuser")).thenReturn(Optional.empty());
        Mockito.when(jwtTokenUtils.generateToken(acc, 24 * 60 *
                60)).thenReturn("testtoken");

        // Act
        ResponseEntity<ResponseObject> response = accountServiceImpl.login(account);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("500", response.getBody().getStatus());
        assertEquals("login successfully", response.getBody().getMessage());
        AccountDTOResponse result = (AccountDTOResponse) response.getBody().getData();
        assertEquals("testuser", result.getUsername());
        assertEquals("testtoken", result.getToken());
    }
}
