package com.JCM.cobranca.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.JCM.cobranca.model.StatusTitulo;
import com.JCM.cobranca.model.Titulo;
import com.JCM.cobranca.repository.TitulosRepository;

@Service
public class TituloService {
	
	@Autowired
	private TitulosRepository titulosRepository;
	
	public void salvar(Titulo titulo) {
		
		try {
			titulosRepository.save(titulo);
		}catch(DataIntegrityViolationException e){
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}
	
	public void excluir(Long codigo) {
		titulosRepository.deleteById(codigo);
	}

	public String receber(Long codigo) {
		@SuppressWarnings("deprecation")
		Titulo titulo = titulosRepository.getOne(codigo);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulosRepository.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
		
	}
	
	
}
