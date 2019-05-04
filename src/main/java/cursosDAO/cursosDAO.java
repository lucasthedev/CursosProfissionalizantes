package cursosDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cursos.profi.ws.Curso;
import com.cursos.profi.ws.UsuarioCurso;

import infra.ConexaoJDBC;
import infra.ConexaoPostgresJDBC;

public class cursosDAO {
	
	private final ConexaoJDBC conexao;
	
	public cursosDAO() throws ClassNotFoundException, SQLException {
		this.conexao = new ConexaoPostgresJDBC();
	}
	
	public List<Curso> listarCursos() throws ClassNotFoundException, SQLException{
		String sql = " SELECT id, nome, carga_horaria, dt_inicio, dias_semana, url_video FROM cursos ORDER BY nome ";
		
		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			List<Curso> lista = new ArrayList<Curso>();
			
			while(rs.next()) {
				lista.add(parser(rs));
			}
			
			return lista;
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public Curso selecionar(int id) throws ClassNotFoundException, SQLException{
		String sqlQuery = " SELECT id, nome, carga_horaria, dt_inicio, dias_semana, url_video FROM cursos WHERE id = ? ";
		
		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return parser(rs);
			}
			
		} catch (SQLException e) {
			throw e;
		}
		return null;
	}
	
	public int inscreverUsuario(UsuarioCurso usuario) throws SQLException, ClassNotFoundException {
		int id = 0;
		
		String sqlQuery = " INSERT INTO usuario_curso (id_curso, nome, email, telefone, data_nascimento) VALUES (?,?,?,?,?) RETURNING id ";
		
		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			stmt.setInt(1, usuario.getIdCurso());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getTelefone());
			stmt.setString(5, usuario.getDataNascimento());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("id");
			}
			
			this.conexao.commit();
		}
		catch(SQLException e) {
			this.conexao.rollback();
			throw e;
		}
		return id;
	}
	
	public List<UsuarioCurso> listarUsuariosIncritos() throws ClassNotFoundException, SQLException{
		String sqlQuery = " SELECT id, id_curso, nome, email, telefone, data_nascimento FROM usuario_curso ORDER BY nome ";
		
		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			
			List<UsuarioCurso> listaUsuarios = new ArrayList<UsuarioCurso>();
			
			while(rs.next()) {
				listaUsuarios.add(parserUsuario(rs));
			}
			
			return listaUsuarios;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Curso parser(ResultSet rs) throws SQLException {
		
		Curso curso = new Curso();
		
		curso.setId(rs.getInt("id"));
		curso.setNome(rs.getString("nome"));
		curso.setCargaHoraria(rs.getString("carga_horaria"));
		curso.setDataInicio(rs.getString("dt_inicio"));
		curso.setDiasSemana(rs.getString("dias_semana"));
		curso.setUrlVideo(rs.getString("url_video"));
		
		return curso;
	}
	
	private UsuarioCurso parserUsuario (ResultSet rs) throws SQLException{
		UsuarioCurso usuario = new UsuarioCurso();
		
		usuario.setId(rs.getInt("id"));
		usuario.setIdCurso(rs.getInt("id_curso"));
		usuario.setNome(rs.getString("nome"));
		usuario.setEmail(rs.getString("email"));
		usuario.setTelefone(rs.getString("telefone"));
		usuario.setDataNascimento(rs.getString("data_nascimento"));
		
		return usuario;
	}
	
}