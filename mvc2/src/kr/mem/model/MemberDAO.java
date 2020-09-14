package kr.mem.model;
import java.sql.*;
import java.util.ArrayList;
public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	// 초기화블럭(무조건 실행됨)
	static {
		try {	// 동적로딩 시 DriverManager와 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnect() {
		String url="jdbc:oracle:thin:@127.0.0.1:1521:XE"; // @까지 프로토콜
		String user="hr";
		String password="hr";
		
		try {
			// 위에서 드라이버매니저와 연결되었기에 따로 초기화 필요 없이 스태틱기능으로 사용가능
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public int memberInsert(MemberVO vo) {
		conn=getConnect();
		// 나중에 MyBatis로 sql문을 빼서 sql문 수정됐을때도 편하게 유지보수
		String SQL="insert into tblMem values(seq_num.nextval,?,?,?,?,?)";
		int cnt = -1; // -1(실패)
		 try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPhone());
			ps.setString(3, vo.getAddr());
			ps.setDouble(4, vo.getLat());
			ps.setDouble(5, vo.getLng());
			cnt = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		 return cnt;
	}
	public ArrayList<MemberVO> memberAllList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		conn = getConnect();
		String SQL="select * from tblMem order by num desc";
		try {
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				double lat = rs.getDouble("lat");
				double lng = rs.getDouble("lng");
				MemberVO vo = new MemberVO(num,name,phone,addr,lat,lng);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	public int memberDelete(int num) {
		conn = getConnect();
		String SQL="delete from tblMem where num=?";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			cnt = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	public void dbClose() {
			try {
				if (rs!=null) rs.close();
				if (ps!=null) ps.close();
				if (conn!=null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	public MemberVO memberContent(int num) {
		MemberVO vo = null;
		conn = getConnect();
		String SQL="select * from tblMem where num=?";
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				double lat = rs.getDouble("lat");
				double lng = rs.getDouble("lng");
				vo = new MemberVO(num,name,phone,addr,lat,lng);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return vo;
	}
	public int memberUpdate(MemberVO vo) {
		conn = getConnect();
		int cnt = -1;
		String SQL = "update tblMem set phone=?, addr=? where num = ?";
		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, vo.getPhone());
			ps.setString(2, vo.getAddr());
			ps.setInt(3, vo.getNum());
			cnt = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
}
