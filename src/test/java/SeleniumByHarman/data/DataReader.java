package SeleniumByHarman.data;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	


	public List<HashMap<String, String>> convertJsonDataToHashMap() throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//java//SeleniumByHarman//data//purchaseOrder.json"), StandardCharsets.UTF_8);
		
		// convert string to hashmap
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,  new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
		
	}

}
