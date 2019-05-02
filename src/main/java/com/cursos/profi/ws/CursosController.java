package com.cursos.profi.ws;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cursosDAO.cursosDAO;

@Path("CursosController")
public class CursosController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarCursos")
	public List<Curso> listarCursos() throws ClassNotFoundException, SQLException{
		try {
			cursosDAO cursos = new cursosDAO();
			return cursos.listarCursos();
		} catch (Exception e) {
			Logger.getLogger(CursosController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

}
