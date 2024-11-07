import java.sql.*;
import java.io.*;
import javax.sql.*;

class check
{
    public static void main(String args[])
    {
        Connection con;
        Statement state;
        ResultSet rs;
        int ch;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","591948");
            System.out.println("Statement object created");

            do
            {
                System.out.println("\n");
                System.out.println("Menu:");
                System.out.println("1.Insert Record into the Table");
                System.out.println("2.Update The Existing Record.");
                System.out.println("3.Display all the Records from the Table");
	        System.out.println("4.Delete the record");
                System.out.println("5.Exit");
                System.out.println("Enter your choice: ");

                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                ch=Integer.parseInt(br.readLine());

                switch(ch)
                {
                    case 1:
                    System.out.println("Enter Employee Number: ");
                    int no=Integer.parseInt(br.readLine());
                    System.out.println("Enter Employee Name: ");
                    String name=br.readLine();
                    System.out.println("Enter Employee Salary: ");
                    int sal=Integer.parseInt(br.readLine());
                    String sql="insert into employee values(?,?,?)";
                    PreparedStatement p=con.prepareStatement(sql);
                    p.setInt(1,no);
                    p.setString(2,name);
                    p.setInt(3,sal);
                    p.executeUpdate();
                    System.out.println("Record Added");
                    //p.close();
                    //con.close();
                    break;

                    case 2:
                    System.out.println("Enter Employee Number for the record you wish to Update: ");
                    no=Integer.parseInt(br.readLine());
                    System.out.println("Enter new Name: ");
                    name=br.readLine();
                    System.out.println("Enter new Salary: ");
                    sal=Integer.parseInt(br.readLine());
                    sql="update Employee set Name=?, Salary=? where no=?";
                    p=con.prepareStatement(sql);
                    p.setString(1,name);
                    p.setInt(2,sal);
                    p.setInt(3,no);
                    p.executeUpdate();
                    System.out.println("Record Updated");
                    //p.close();
                    //con.close();
                    break;

                    case 3:
                    state=con.createStatement();
                    sql="select * from employee";
                    rs=state.executeQuery(sql);
                    while(rs.next())
                    {
                        System.out.println("\n");
                        System.out.print("\t" +rs.getInt(1));
                        System.out.print("\t" +rs.getString(2));
                        System.out.print("\t" +rs.getInt(3));
                    }
                    break;

		    case 4:
		    System.out.println("Enter Employee Number for the record you wish to delete: ");  
	            no=Integer.parseInt(br.readLine());
	            sql="DELETE FROM Employee WHERE ENo = ?";   
		    p=con.prepareStatement(sql);
                    p.setInt(1,no);
		    p.executeUpdate();  
	            System.out.println("Record Deleted");           
                    break;

                    case 5:
                    System.exit(0);

                    default:
                    System.out.println("Invalid Choice");
                    break;
                }
            }while(ch!=4);
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}