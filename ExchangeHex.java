
public class ExchangeHex {

	public static void main(String[] args) {



        long uint = System.currentTimeMillis()/1000;
        

        String hexString = String.format("0x%08X", uint);	
        
        System.out.println("时间戳："+uint+"\n"+"时间戳16显示："+hexString);
        
      
      

        System.out.println();
        
        printHexString(luolaoshi(uint));

        
        System.out.println();

        

        
        printHexString(createValue());
	}
	
	
	
	   public static byte[] createValue() {
		   
		   System.out.print("构造的byte数组是：");
		   
	        byte[] value = new byte[5];

	        long uint = System.currentTimeMillis() / 1000;
	        byte[] time = luolaoshi(uint);
	        
	        value[0]= 0x02;
	        
	        for (int i = 1 ; i<value.length;i++){
	            value[i] = time[i-1];
	        }
	        return value;
	    }
	
	   
	   public static byte[] luolaoshi(long num){
		    byte[] b = new byte[4];  
		    for (int i=0;i<4;i++) {  
		    	b[i]=(byte)(num>>>(i*8));
		    	}  
		    return b;  
	   }
	
	
	public static void printHexString( byte[] b) {  
		   for (int i = 0; i < b.length; i++) {    
		     String hex = Integer.toHexString(b[i] & 0xFF);    
		     if (hex.length() == 1) {    
		       hex = '0' + hex;    
		     }    
		     System.out.print("0x"+hex.toUpperCase()+" " );    
		   }    
		  
		}  

}
