package cursosDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cursos.profi.ws.Curso;

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
	
}
