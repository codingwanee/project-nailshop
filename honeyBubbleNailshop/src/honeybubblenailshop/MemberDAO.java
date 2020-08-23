package honeybubblenailshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MemberDAO extends DBCPManager {

	private static MemberDAO _dao;
	

	private MemberDAO() {
		// TODO Auto-generated constructor stub
	}	
	
	public static MemberDAO getMemberDAO() {
		if(_dao==null) {
			_dao=new MemberDAO();
		}
		return _dao;
	}	

// 신규회원 등록  ----------------------------------------------------------------------------------------------------
	public int insertMember(MemberDTO member) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {

			con=getConnection();
			
			String sql="insert into nailshop values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, member.getNo());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getType());
			pstmt.setString(5, member.getMemo());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertMember() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
// 회원정보 변경  ----------------------------------------------------------------------------------------------------
	public int updateMember(MemberDTO member) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update nailshop set name=?,phone=?"
					+ ",type=?,memo=? where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getType());
			pstmt.setString(4, member.getMemo());
			pstmt.setInt(5, member.getNo());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateMember() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
// 회원정보 삭제  ----------------------------------------------------------------------------------------------------
	public int deleteMember(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="delete from nailshop where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]deleteMember() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
//회원번호로 검색  ----------------------------------------------------------------------------------------------------
	public MemberDTO selectNoMember(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDTO member=null;
		try {
			con=getConnection();
			
			String sql="select * from nailshop where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				member=new MemberDTO();

				member.setNo(rs.getInt("no"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setType(rs.getString("type"));
				member.setMemo(rs.getString("memo"));
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectNoMember() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}

		return member;
	}

//이름 또는 전화번호로 회원 검색  ------------------------------------------------------------------------------------------
	public List<MemberDTO> selectNameMember(String name, String phone) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDTO> memberList=new ArrayList<MemberDTO>();
		try {
			con=getConnection();
					
			String sql;
			if(name.equals("")) {
				sql="select * from nailshop where phone=? order by no";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, phone);
			} else if(phone.equals("")) {
				sql="select * from nailshop where name like '%'||?||'%' order by no";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, name);
			} else {
				sql="select * from nailshop where name like '%'||?||'%' and phone=? order by no";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, phone);
			}
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO member=new MemberDTO();
				member.setNo(rs.getInt("no"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setType(rs.getString("type"));
				member.setMemo(rs.getString("memo"));

				memberList.add(member);
			}
 		} catch (SQLException e) {
			System.out.println("[에러]selectNoMember() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return memberList;
	}
	
//모든 회원 검색  ----------------------------------------------------------------------------------------------------
	public List<MemberDTO> selectAllMember() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<MemberDTO> memberList=new ArrayList<MemberDTO>();
		try {
			con=getConnection();					
			String sql="select * from nailshop order by no";
			pstmt=con.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO member=new MemberDTO();
				member.setNo(rs.getInt("no"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setType(rs.getString("type"));
				member.setMemo(rs.getString("memo"));
				memberList.add(member);
			}
 		} catch (SQLException e) {
			System.out.println("[에러]selectAllMember() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return memberList;
	}
}
