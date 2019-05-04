package com.cursos.profi.ws;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/obterCurso/{id}")
	public Curso obterCurso(@PathParam("id") int id) throws ClassNotFoundException, SQLException{
		try {
			cursosDAO cursosDAO = new cursosDAO();
			return cursosDAO.selecionar(id);
		} catch (SQLException e) {
			Logger.getLogger(CursosController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/inscreverUsuario")
	public Response inscreverUsuario(UsuarioCurso usuario) throws ClassNotFoundException, SQLException {
		try {
			cursosDAO curso = new cursosDAO();
			curso.inscreverUsuario(usuario);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			Logger.getLogger(CursosController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarUsuarios")
	public List<UsuarioCurso> listarUsuarios() throws ClassNotFoundException, SQLException{
		try {
			cursosDAO dao = new cursosDAO();
			return dao.listarUsuariosIncritos();
		}
		catch(Exception e) {
			Logger.getLogger(CursosController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

}
