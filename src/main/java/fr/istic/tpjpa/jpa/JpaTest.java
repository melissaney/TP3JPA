package fr.istic.tpjpa.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import fr.istic.tpjpa.domain.Equipements;
import fr.istic.tpjpa.domain.Home;
import fr.istic.tpjpa.domain.Person;

public class JpaTest {

	private static EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("example");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		// TODO create entity

		Person per1 = new Person();
		Person per2 = new Person();

		Home hm1 = new Home();

		Home hm2 = new Home();

		// TODO run request
		manager.persist(per1);
		manager.persist(per2);
		manager.persist(hm1);
		manager.persist(hm2);

		createAmis();
		listAmis();
		TheEnd();
		tx.commit();

		// TODO run request
		System.out.println(".. done");
		manager.close();
	}

	private static void createAmis() {
		int numOfAmis = manager
				.createQuery("Select a From Person a", Person.class)
				.getResultList().size();

		if (numOfAmis <= 4) {
			Person dude = new Person("Toto", "Titi");
			manager.persist(dude);

			Home dudeHome = new Home(14, "Une rue".toString(),
					"192.168.1.24".toString());
			manager.persist(dudeHome);

			dudeHome.setProprietaire(dude);
			Equipements M40 = new Equipements("M40", "4200rnd/min", dudeHome);
			manager.persist(M40);

			Equipements M60 = new Equipements("M60", "6000rnd/min", dudeHome);
			manager.persist(M60);

			Equipements Ventilateur = new Equipements("Ventilateur", "10W",
					dudeHome);
			manager.persist(Ventilateur);

			/*
			 * Equipements VentilateurRotatif = new
			 * Equipements("VentilateurRotatif", "10W", dudeHome);
			 * manager.persist(VentilateurRotatif);
			 * 
			 * Equipements PCIA = new Equipements("PCIA", "200W", dudeHome);
			 * manager.persist(PCIA);
			 */// #1452 - Cannot add or update a child row: a foreign key
				// constraint fails (`base_10007924`.`Equipements`, CONSTRAINT
				// `FK2BF439A05844A209` FOREIGN KEY (`id`) REFERENCES `Home`
				// (`id`))

			List<Person> L = new ArrayList<Person>();

			Person dude2 = new Person("Pierre", "Dupont");
			manager.persist(dude2);

			Person dude3 = new Person("Dave", "Dupond");
			manager.persist(dude3);

			L.add(dude2);

			L.add(dude3);

			//dude.setAmis(L);
			manager.persist(new Person("random", "dude"));
		}
	}

	private static void listAmis() {
		List<Person> resultList = manager.createQuery("Select a From Person a",
				Person.class).getResultList();
		System.out.println("num of Person:" + resultList.size());

		for (Person next : resultList) {
			System.out.println("next Person: " + next.getPrenom() + " "
					+ next.getNom());
		}
	}

	private static void TheEnd() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		// assuming a is an Integer
		// if returning multiple fields, look into using a Tuple
		// or specifying the return type as an Object or Object[]
		javax.persistence.criteria.CriteriaQuery<Equipements> query = criteriaBuilder
				.createQuery(Equipements.class);
		Root<Home> from = query.from(Home.class);
		query.multiselect(from.get("id")).where(from.get("id").in(1, 2, 3, 4));
	}
}
