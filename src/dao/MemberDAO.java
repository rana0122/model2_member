package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MemberDTO;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	
	private Connection getConnection() throws Exception {

		  // InitialContext 객체를 생성해서 initCtx 레퍼런스에 할당
	      Context initCtx = new InitialContext();

		  // initCtx 객체를 가지고 lookup()메소드를 사용해서 "java:comp/env" 이름에 해당하는 객체를 리턴받는다.
		  // 이때 객체를  원하는 타입으로 형 변환해서 envCtx 레퍼런스에 할당 받는다.
	      Context envCtx = (Context) initCtx.lookup("java:comp/env");
		  
		  // envCtx 객체를 가지고 lookup() 메소드를 사용해서 "jdbc/academy"에 해당하는 객체를 리턴 받는다.
		  // DataSource로 형변환해서 ds 레퍼런스에 할당한다.
	      DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
	      return ds.getConnection();
	    }
	
		
		// ID중복확인
		public int idCheck(String id) {
			int result=0;
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql;
			
			try {
					con=getConnection();
					sql="select * from member where id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, id);
					rs=pstmt.executeQuery();
					if(rs.next()) {		// 중복 ID	
						result = 1;
					}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) {
					try {
						rs.close();
					}catch(Exception e) {}
				}
				if(pstmt != null) {
					try {
						pstmt.close();
					}catch(Exception e) {}
				}
				if(con != null) {
					try {
						con.close();
					}catch(Exception e) {}
				}
			}
			
			return result;
		}	
	
		// 회원 가입
		public int insertMember(MemberDTO member) {
			int result=0;
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql;
			
			try {
					con = getConnection();
					sql="insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, member.getId());
					pstmt.setString(2, member.getPasswd());
					pstmt.setString(3, member.getName());
					pstmt.setString(4, member.getJumin1());
					pstmt.setString(5, member.getJumin2());
					pstmt.setString(6, member.getEmail());
					pstmt.setString(7, member.getTel());
					pstmt.setString(8, member.getPhone());
					pstmt.setString(9, member.getPostcode());
					pstmt.setString(10, member.getAddress());
					pstmt.setString(11, member.getGender());
					pstmt.setString(12, member.getHobby());
					pstmt.setString(13, member.getIntro());
					
					result = pstmt.executeUpdate();
					
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					}catch(Exception e) {}
				}
				if(con != null) {
					try {
						con.close();
					}catch(Exception e) {}
				}
			}		
			
			return result;
		}
	
		
		//회원 인증
		public int memberAuth(MemberDTO member) {
			int result=0;
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql;
			
			try {
					con = getConnection();
					sql="select * from member where id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, member.getId());
					rs=pstmt.executeQuery();
					if(rs.next()) {	// ID가 있는 경우
						String dbpasswd = rs.getString("passwd");
						if(dbpasswd.equals(member.getPasswd())) {//비번일치
							result = 1;
						}else {			//비번이 틀린 경우
							result = -1;
						}
					}else {				// ID가 틀린경우
							result = -2;
					}
					
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) {
					try {
						rs.close();
					}catch(Exception e) {}
				}
				if(pstmt != null) {
					try {
						pstmt.close();
					}catch(Exception e) {}
				}
				if(con != null) {
					try {
						con.close();
					}catch(Exception e) {}
				}
			}			
			
			return result;
		}
		
		
		// 회원탈퇴
		public int deleteMember(MemberDTO member) {
			int result=0;
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql;
			
			try {
					con = getConnection();
					sql="select * from member where id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, member.getId());
					rs=pstmt.executeQuery();
					if(rs.next()) {	// ID가 있는 경우
						String dbpasswd = rs.getString("passwd");
						if(dbpasswd.equals(member.getPasswd())) {//비번일치
							sql = "delete from member where id=?";
							pstmt=con.prepareStatement(sql);
							pstmt.setString(1, member.getId());
							result = pstmt.executeUpdate();					
					
						}else {			//비번이 틀린 경우
							result = -1;
						}
					}else {				// ID가 틀린경우
							result = -2;
					}
					
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) {
					try {
						rs.close();
					}catch(Exception e) {}
				}
				if(pstmt != null) {
					try {
						pstmt.close();
					}catch(Exception e) {}
				}
				if(con != null) {
					try {
						con.close();
					}catch(Exception e) {}
				}
			}			
			
			return result;
		}
		
	
		// 회원 1명 정보 구하기
		public MemberDTO getMember(String id) {			
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			MemberDTO member=null;
			String sql;
			
			try {
					con = getConnection();
					sql="select * from member where id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, id);
					rs=pstmt.executeQuery();
					if(rs.next()) {	// ID가 있는 경우
						member = new MemberDTO();
						member.setId(rs.getString("id"));
						member.setPasswd(rs.getString("passwd"));
						member.setName(rs.getString("name"));
						member.setJumin1(rs.getString("jumin1"));
						member.setJumin2(rs.getString("jumin2"));
						member.setEmail(rs.getString("email"));
						member.setTel(rs.getString("tel"));
						member.setPhone(rs.getString("phone"));
						member.setPostcode(rs.getString("postcode"));
						member.setAddress(rs.getString("address"));
						member.setGender(rs.getString("gender"));
						member.setHobby(rs.getString("hobby"));
						member.setIntro(rs.getString("intro"));
						member.setRegister(rs.getTimestamp("register"));		
											
					}
					
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) {
					try {
						rs.close();
					}catch(Exception e) {}
				}
				if(pstmt != null) {
					try {
						pstmt.close();
					}catch(Exception e) {}
				}
				if(con != null) {
					try {
						con.close();
					}catch(Exception e) {}
				}
			}				
			
			return member;
		}
		
		
		// 회원정보 수정
		public int updateMember(MemberDTO member) {
			int result=0;
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql;
			
			try {
					con = getConnection();
					sql="select * from member where id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, member.getId());
					rs=pstmt.executeQuery();
					if(rs.next()) {	// ID가 있는 경우
						String dbpasswd = rs.getString("passwd");
						if(dbpasswd.equals(member.getPasswd())) {//비번일치
							sql = "update member set  name=?,jumin1=?,jumin2=?,";
							sql += " email=?,tel=?,phone=?,postcode=?,address=?,";
							sql +=" gender=?,hobby=?, intro=? where id=?";
							pstmt=con.prepareStatement(sql);
							pstmt.setString(1, member.getName());
							pstmt.setString(2, member.getJumin1());
							pstmt.setString(3, member.getJumin2());
							pstmt.setString(4, member.getEmail());
							pstmt.setString(5, member.getTel());
							pstmt.setString(6, member.getPhone());
							pstmt.setString(7, member.getPostcode());
							pstmt.setString(8, member.getAddress());
							pstmt.setString(9, member.getGender());
							pstmt.setString(10, member.getHobby());
							pstmt.setString(11, member.getIntro());
							pstmt.setString(12, member.getId());
							result = pstmt.executeUpdate();					
					
						}else {			//비번이 틀린 경우
							result = -1;
						}
					}else {				// ID가 틀린경우
							result = -2;
					}
					
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) {
					try {
						rs.close();
					}catch(Exception e) {}
				}
				if(pstmt != null) {
					try {
						pstmt.close();
					}catch(Exception e) {}
				}
				if(con != null) {
					try {
						con.close();
					}catch(Exception e) {}
				}
			}			
			
			return result;
		}		
		

}
