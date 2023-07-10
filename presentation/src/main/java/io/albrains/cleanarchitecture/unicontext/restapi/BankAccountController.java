package io.albrains.cleanarchitecture.unicontext.restapi;

import io.albrains.cleanarchitecture.unicontext.core.application.dto.OpenAccountRequest;
import io.albrains.cleanarchitecture.unicontext.core.application.dto.OpenAccountResponse;
import io.albrains.cleanarchitecture.unicontext.core.application.dto.ViewAccountRequest;
import io.albrains.cleanarchitecture.unicontext.core.application.dto.ViewAccountResponse;
import io.albrains.cleanarchitecture.unicontext.core.application.usecase.OpenAccountUseCase;
import io.albrains.cleanarchitecture.unicontext.core.application.usecase.ViewAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/bank-accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final OpenAccountUseCase openAccountUseCase;
    private final ViewAccountUseCase viewAccountUseCase;

    @PostMapping
    public ResponseEntity<OpenAccountResponse> openAccount(@RequestBody OpenAccountRequest request) {
        var response = openAccountUseCase.handle(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<ViewAccountResponse> viewAccount(@PathVariable String accountNumber) {
        var request = ViewAccountRequest.builder()
                .accountNumber(accountNumber)
                .build();
        var response = viewAccountUseCase.handle(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
