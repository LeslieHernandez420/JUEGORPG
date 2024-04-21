package player.jobs;

import player.Player;
import player.skills.Skill;

import java.util.HashMap;
import java.util.Map;

public abstract class Job {

	protected final Player player;
	protected String name;
	protected HashMap<String, Skill> skills;

	public Job(Player player, String name) {

		this.player = player;
		this.name = name;
		this.skills = new HashMap<>();
	}

	public void addSkill(Skill skill) {

		this.skills.put(skill.getName(), skill);
	}

	public void callSkill(String skillName) {

		this.skills.get(skillName).activate(this.player);
	}

	public String showSkills() {

		StringBuilder skills = new StringBuilder();
		skills.append("Habilidades de ").append(this.name).append(":\n");
		for (Map.Entry<String, Skill> entry : this.skills.entrySet()) {
			skills.append(entry.getKey()).append("\n");
		}
		return skills.toString();
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public HashMap<String, Skill> getSkills() {

		return skills;
	}

	public void setSkills(HashMap<String, Skill> skills) {

		this.skills = skills;
	}
}
