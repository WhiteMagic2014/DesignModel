import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExchangeDate {

	
	
	public static void main(String[] args) throws ParseException {


		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
		String dstr="2017-08-05 18:57:22";  
		Date date=sdf.parse(dstr);  
		
		System.out.println(date);
		
	}

}
