package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.*;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonService implements Callable<Villager> {

	private static final String SAYING_TAG = "saying";
	private static final String IMAGE_URI_TAG = "image_uri";
	private static final String TEXT_COLOR_TAG = "text-color";
	private static final String BUBBLE_COLOR_TAG = "bubble-color";
	private static final String BIRTHDAY_TAG = "birthday-string";
	private static final String GENDER_TAG = "gender";
	private static final String SPECIES_TAG = "species";
	private static final String NAME_ES_TAG = "name-USen";
	private static final String NAME_JSON_TAG = "name";
	public final static String VILLAGER_INFO_URL = "http://acnhapi.com/v1/villagers/";

	private static int id;
	private int time;

	public JsonService(int id) {
		JsonService.id = id;
	}

	public Villager loadVillager() {
		time = 0;
		try {
			JSONObject json = JsonService.readJsonFromUrl();
			JSONObject name = (JSONObject) json.get(NAME_JSON_TAG);
			return new Villager(name.get(NAME_ES_TAG).toString(), json.get(SPECIES_TAG).toString(),
					json.get(GENDER_TAG).toString(), json.get(BIRTHDAY_TAG).toString(),
					json.get(BUBBLE_COLOR_TAG).toString(), json.get(TEXT_COLOR_TAG).toString(),
					json.get(IMAGE_URI_TAG).toString(),
					json.get(SAYING_TAG).toString());
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JSONObject readJsonFromUrl() throws IOException, JSONException {
		InputStream is = new URL(VILLAGER_INFO_URL + String.valueOf(id)).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	@Override
	public Villager call() throws Exception {
		Thread.sleep(2);
		return loadVillager();
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}

	public static void main(String[] args) {
		try {
			ExecutorService executor = Executors.newFixedThreadPool(3);
			JsonService js = new JsonService(2);
			Future<Villager> future = executor.submit(js);
			int count = 0;
			while (!future.isDone()) {
				Thread.sleep(10);
				count++;
				System.out.println(count);
			}
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
