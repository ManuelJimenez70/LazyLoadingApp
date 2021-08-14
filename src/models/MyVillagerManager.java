package models;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyVillagerManager {

	private int counter;
	private ExecutorService executor;
	private ArrayList<Villager> villagers;

	public MyVillagerManager(int villagetNum) {
		villagers = new ArrayList<Villager>();
		counter = 1;
		executor = Executors.newFixedThreadPool(6);
		try {
			for (int i = 0; i < villagetNum; i++) {
				Future<Villager> future = processFile();
				while (!future.isDone()) {
				}
				this.villagers.add(future.get());
			}

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public Future<Villager> processFile() {
		JsonService js = new JsonService(counter);
		counter++;
		return executor.submit(js);
	}

	public ArrayList<Villager> getVillagers() {
		return this.villagers;
	}
}
