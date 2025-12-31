package kr.cr.ddit;
import kr.cr.ddit.service.MessegeService;
import java.util.*;

public class PrintMessege{
	public static void main(String[] args){
		MessegeService service = new MessegeService();
		String messege = service.getMessege();
		String messege1 = service.getMessege();
		
		List<String> list = List.of(messege, messege1);
		System.out.println(list);
	}
}