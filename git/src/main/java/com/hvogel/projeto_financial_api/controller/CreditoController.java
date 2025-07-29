package com.hvogel.projeto_financial_api.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hvogel.projeto_financial_api.dto.CreditoDTO;
import com.hvogel.projeto_financial_api.service.CreditoService;
import com.hvogel.projeto_financial_api.util.ResponseUtil;

@RestController
@RequestMapping("api/creditos")
public class CreditoController {
	
	private final Logger log = LoggerFactory.getLogger(CreditoController.class);
	
	private final CreditoService creditoService;
	
	@Autowired
	public CreditoController(CreditoService creditoService) {
		this.creditoService = creditoService;
	}
	
	@GetMapping("credito/{numeroCredito}")
	public ResponseEntity<CreditoDTO> findByNumeroCredito(@PathVariable String numeroCredito){
		log.debug("REST request to get Credito by numeroCredito : {}", numeroCredito);
        Optional<CreditoDTO> creditoDto = creditoService.findByNumeroCredito(numeroCredito);
        return ResponseUtil.wrapOrNotFound(creditoDto);		
	}
	
	@GetMapping("{numeroNfse}")
	public ResponseEntity<List<CreditoDTO>> findByNumeroNfse(@PathVariable String numeroNfse){
		log.debug("REST request to get List of Creditos by numeroNfse : {}", numeroNfse);		
        return ResponseEntity.ok().body(creditoService.findByNumeroNfse(numeroNfse));	
	}
	
	@GetMapping("credito/{id}/id")
	public ResponseEntity<CreditoDTO> findById(@PathVariable Long id){
		log.debug("REST request to get Credito by Id : {}", id);
        Optional<CreditoDTO> creditoDto = creditoService.findById(id);
        return ResponseUtil.wrapOrNotFound(creditoDto);		
	}
	
	@GetMapping()
	public ResponseEntity<List<CreditoDTO>> findAllByOrderByIdAsc(){
		log.debug("REST request to get List of All Creditos order by Id asc");		
        return ResponseEntity.ok().body(creditoService.findAllByOrderByIdAsc());	
	}
	
	@PostMapping
    public ResponseEntity<CreditoDTO> createCredito(@RequestBody CreditoDTO creditoDTO) {
		log.debug("REST request to post a credito object : {}", creditoDTO);
		CreditoDTO result = creditoService.save(creditoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
	
	@PutMapping
	public ResponseEntity<CreditoDTO> updateCredito(@RequestBody CreditoDTO creditoDTO) {
		log.debug("REST request to put a credito object : {}", creditoDTO);
	    return ResponseEntity.ok(creditoService.update(creditoDTO));
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<CreditoDTO> deleteCredito(@PathVariable Long id) {
		creditoService.delete(id);
		return ResponseEntity.noContent().build();
   }

}
