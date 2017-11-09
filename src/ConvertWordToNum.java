
public class ConvertWordToNum {

	public static void ConvertWordToNum(String str) {
		// TODO Auto-generated constructor stub
		String[] splited = str.split("\\s+");
		String[] amount = splited[0].split("\\$");
		int amountInt = Integer.parseInt(amount[1]);
		System.out.println(amountInt + " " + splited[0] + " : " + splited[1]);
	}

	public static void main(String[] args) {
		String test = "$679.36 billion";
		ConvertWordToNum(test);
		
		// TODO Auto-generated method stub

	}

}
