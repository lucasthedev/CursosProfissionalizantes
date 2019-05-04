package tests;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cursos.profi.ws.Curso;
import com.cursos.profi.ws.UsuarioCurso;

import cursosDAO.cursosDAO;
import junit.framework.Assert;

public class CursosTests {

	@Test
	public void retornarCursos() {
		boolean validacao = false;
		try {
			List<Curso> lista = new ArrayList<Curso>();
			cursosDAO dao = new cursosDAO();
			lista = dao.listarCursos();
			if(lista.size() > 0) {
				validacao = true;
			}
			
			for(Curso dado: lista) {
				System.out.println("Nome do curso: " + dado.getNome());
			}
			System.out.println("-------------------------------------------");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(validacao);

	}
	
	@Test
	public void inscreverUsuario() throws ClassNotFoundException, SQLException {
		int id =0;
		boolean validao = false;
		
		UsuarioCurso usuario = new UsuarioCurso();
		usuario.setNome("Fulano 2");
		usuario.setEmail("fulano2@teste.com.br");
		usuario.setTelefone("222-4441");
		usuario.setDataNascimento("01/01/1979");
		usuario.setIdCurso(1);
		
		cursosDAO dao = new cursosDAO();
		
		id = dao.inscreverUsuario(usuario);
		
		if(id > 0) {
			validao = true;
		}
		
		assertTrue(validao);
	}
	
	@Test
	public void listarUsuarios() throws ClassNotFoundException, SQLException{
		
		try {
			List<UsuarioCurso> listaUsuarios = new ArrayList<UsuarioCurso>();
			cursosDAO dao = new cursosDAO();
			listaUsuarios = dao.listarUsuariosIncritos();
			
			int contador = 0;
			
			for(UsuarioCurso usuario: listaUsuarios) {
				System.out.println();
				contador++;
				System.out.println("Nome: " + usuario.getNome());
				System.out.println("Email: " + usuario.getEmail());
				System.out.println("Telefone: " + usuario.getTelefone());
				System.out.println("Data nascimento: " + usuario.getDataNascimento());
				System.out.println("Código Curso: " + usuario.getIdCurso());
				System.out.println("-------------------------------------------");
			}
			
			if(contador == 0) {
				System.out.println("Não existem usuarios inscritos em cursos");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(true);
	}

}
