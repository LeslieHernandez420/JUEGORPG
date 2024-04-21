package util.enemies;

import enemies.enemy;
import enemies.navy.NavyOfficer;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import player.Player;
import util.annotations.BossEnemy;
import util.annotations.RegularEnemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class EnemyFactory {

	private static final Random random = new Random();

	public static enemy generateRegularEnemy(Player player) {

		Reflections reflections = new Reflections(new ConfigurationBuilder()
				.setUrls(ClasspathHelper.forJavaClassPath())
				.setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()));

		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(RegularEnemy.class);
		List<Class<?>> classList = new ArrayList<>(classes);

		Class<?> claseEnemyRegular = classList.get(random.nextInt(classList.size()));

		try {

			return (enemy) claseEnemyRegular.getDeclaredConstructor(Player.class).newInstance(player);
		} catch (Exception e) {
			e.printStackTrace();
			return new NavyOfficer(player);
		}
	}

	public static enemy generateBossEnemy(Player player) {

		Reflections reflections = new Reflections(new ConfigurationBuilder()
				.setUrls(ClasspathHelper.forJavaClassPath())
				.setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()));

		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(BossEnemy.class);
		List<Class<?>> classList = new ArrayList<>(classes);

		Class<?> claseJefe = classList.get(random.nextInt(classList.size()));

		try {
			return (enemy) claseJefe.getDeclaredConstructor(Player.class).newInstance(player);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}