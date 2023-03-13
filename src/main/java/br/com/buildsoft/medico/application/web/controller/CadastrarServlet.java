package br.com.buildsoft.medico.application.web.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.buildsoft.medico.domain.models.Medico;
import br.com.buildsoft.medico.domain.services.MedicoService;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/medico/cadastrar")
public class CadastrarServlet extends HttpServlet {
	
	MedicoService medicoService;
	
	public CadastrarServlet() {
		this.medicoService = new MedicoService();
	}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        bufferedReader.lines().forEach(line -> stringBuilder.append(line));
        
        Gson gson = new Gson();
        Medico medico = gson.fromJson(stringBuilder.toString(), Medico.class);
        
    	Medico medicoSalvo = medicoService.salvar(medico);
    	
    	response.setContentType("application/json");
    	response.getWriter().write(gson.toJson(medicoSalvo));
    }
}
