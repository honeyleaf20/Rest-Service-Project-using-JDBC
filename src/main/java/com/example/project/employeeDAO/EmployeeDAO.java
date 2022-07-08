package com.example.project.employeeDAO;
	import java.sql.Connection;


	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.example.project.model.Employee;

	@Component
	public class EmployeeDAO {
		public  Connection con=getDBConnection();
		
		
	  public  Employee getEmployee(int id) throws SQLException {
		 
		 Employee emp=new Employee();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM employee  WHERE id=? ");
			ps.setInt(1, id);
		   ResultSet rs= ps.executeQuery();
	      
	     while(rs.next()) {
	    	emp.setId(rs.getInt("id"));
	        emp.setName(rs.getString("name"));
	      	emp.setAddress(rs.getString("address"));
	      	emp.setRole(rs.getString("role"));
	      	emp.setSalary(rs.getFloat("salary"));
           
	      	}
	     ps.close();
		}
	   catch (SQLException e) {
		
		   System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}
		if(emp.getName()==null)throw new SQLException("Employee not found");
	   
	   return emp;
	   
	  }
	  
	  
	  
	  
	  public   Employee addEmployee(Employee newemp) throws SQLException {
		
		  String q="INSERT INTO testdb .employee (`id`, `name`, `address`, `role`, `salary`)"
					+ " VALUES (DEFAULT,?,?,?,?)";
			PreparedStatement ps= con.prepareStatement(q);
			ps.setString(1, newemp.getName());
			ps.setString(2, newemp.getAddress());
			ps.setString(3, newemp.getRole());
			ps.setFloat(4, newemp.getSalary());
			
			ps.executeUpdate();
			
			return newemp;
		}
	  
	  
	  
	  
	  public  List<Employee> getAllEmployee() throws SQLException {
		  
			List<Employee> res= new ArrayList<>();
			Statement st2=con.createStatement();
	        ResultSet rs= st2.executeQuery("SELECT * FROM employee");
	        while(rs.next()) {
	        	Employee emp=new Employee();
	        	emp.setId(rs.getInt("id"));      
	        	emp.setName(rs.getString("name"));
		      	emp.setAddress(rs.getString("address"));
		      	emp.setRole(rs.getString("role"));
		      	emp.setSalary(rs.getFloat("salary"));
	        	res.add(emp);
	        }
	        
	        st2.close();
	     
	        return res;
		}
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  private  Connection getDBConnection() {
		  Connection con=null;
			
			try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
			    con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/testdb","root","rootpass");
			    
				}catch(Exception e){
					System.out.println(e);
					} 
			return con;
				}
	

	
	}


