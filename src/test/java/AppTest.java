import java.io.FileNotFoundException;
import java.io.IOException;

import main.App;

public class AppTest {

	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		App.init("D:/My Documents/Desktop/MyBatisCodeGen/config.properties");
		
		if(App.tables==null) {
			App.genAll();
		}else {
			App.genSpecific(App.tables);
		}
	}
}
