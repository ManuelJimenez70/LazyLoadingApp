package models;

public class Villager {
//nombre, especie, genero, cumpleaños y el buuble and text color

	private String name, species, gender, birthday, bubbleColor, textColor, imageUrl, message;

	public Villager(String name, String species, String gender, String birthday, String buuble_color, String text_color,
			String image_url, String message) {
		super();
		this.name = name;
		this.species = species;
		this.gender = gender;
		this.birthday = birthday;
		this.bubbleColor = buuble_color;
		this.textColor = text_color;
		this.imageUrl = image_url;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public String getSpecies() {
		return species;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public String getMessage() {
		return message;
	}

	public String getGender() {
		return gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getBubbleColor() {
		return bubbleColor;
	}

	public String getTextColor() {
		return textColor;
	}

	@Override
	public String toString() {
		return "name: " + name + ", species: " + species + ", gender: " + gender + ", birthday: " + birthday
				+ ", buuble_color: " + bubbleColor + ", text_color: " + textColor + ", image_url: " + imageUrl
				+ "saying: " + message;
	}

}
