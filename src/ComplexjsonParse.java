import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexjsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Print No of courses 
		JsonPath js=new JsonPath(payload.CoursePrice());
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		//Print Purchase Amount
		int purchaseamount=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseamount);
		
		//Print Title of the first course
		String title=js.get("courses[0].title");
		System.out.println(title);
		
		//Print All course titles and their respective Prices
		for(int i=0;i<count;i++)
		{
			String courseTitle=js.get("courses["+i+"].title");
			System.out.println(courseTitle);
			System.out.println(js.get("courses["+i+"].price").toString());
		}
		
	    //Print no of copies sold by RPA Course
		System.out.println("Print no of copies sold by RPA Course");
		for(int i=0;i<count;i++)
		{
			String courseTitles=js.get("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA"))
			{
				int copies=js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		
		
		

	}

}
