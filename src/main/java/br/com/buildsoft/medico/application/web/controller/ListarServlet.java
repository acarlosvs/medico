package br.com.buildsoft.medico.application.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.buildsoft.medico.domain.services.MedicoService;

@WebServlet("/medico/listar")
public class ListarServlet extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MedicoService medicoService = new MedicoService();
		
		Gson gson = new Gson();
		String listaMedicos = gson.toJson(medicoService.listar());
		
		response.setContentType("application/json");
		response.getWriter().write(listaMedicos);
    }
}
