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

		  // InitialContext ��ü�� �����ؼ� initCtx ���۷����� �Ҵ�
	      Context initCtx = new InitialContext();

		  // initCtx ��ü�� ������ lookup()�޼ҵ带 ����ؼ� "java:comp/env" �̸��� �ش��ϴ� ��ü�� ���Ϲ޴´�.
		  // �̶� ��ü��  ���ϴ� Ÿ������ �� ��ȯ�ؼ� envCtx ���۷����� �Ҵ� �޴´�.
	      Context envCtx = (Context) initCtx.lookup("java:comp/env");
		  
		  // envCtx ��ü�� ������ lookup() �޼ҵ带 ����ؼ� "jdbc/academy"�� �ش��ϴ� ��ü�� ���� �޴´�.
		  // DataSource�� ����ȯ�ؼ� ds ���۷����� �Ҵ��Ѵ�.
	      DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
	      return ds.getConnection();
	    }
	
		
		// ID�ߺ�Ȯ��
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
					if(rs.next()) {		// �ߺ� ID	
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
	
		// ȸ�� ����
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
	
		
		//ȸ�� ����
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
					if(rs.next()) {	// ID�� �ִ� ���
						String dbpasswd = rs.getString("passwd");
						if(dbpasswd.equals(member.getPasswd())) {//�����ġ
							result = 1;
						}else {			//����� Ʋ�� ���
							result = -1;
						}
					}else {				// ID�� Ʋ�����
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
		
		
		// ȸ��Ż��
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
					if(rs.next()) {	// ID�� �ִ� ���
						String dbpasswd = rs.getString("passwd");
						if(dbpasswd.equals(member.getPasswd())) {//�����ġ
							sql = "delete from member where id=?";
							pstmt=con.prepareStatement(sql);
							pstmt.setString(1, member.getId());
							result = pstmt.executeUpdate();					
					
						}else {			//����� Ʋ�� ���
							result = -1;
						}
					}else {				// ID�� Ʋ�����
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
		
	
		// ȸ�� 1�� ���� ���ϱ�
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
					if(rs.next()) {	// ID�� �ִ� ���
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
		
		
		// ȸ������ ����
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
					if(rs.next()) {	// ID�� �ִ� ���
						String dbpasswd = rs.getString("passwd");
						if(dbpasswd.equals(member.getPasswd())) {//�����ġ
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
					
						}else {			//����� Ʋ�� ���
							result = -1;
						}
					}else {				// ID�� Ʋ�����
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
