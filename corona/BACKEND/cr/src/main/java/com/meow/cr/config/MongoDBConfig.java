package com.meow.cr.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.meow.cr.document.City;
import com.meow.cr.document.CoronaCase;
import com.meow.cr.repository.CityRepository;
import com.meow.cr.repository.CoronaRepository;

@EnableMongoRepositories(basePackageClasses = CoronaRepository.class)
@Configuration
@Profile("dev")

public class MongoDBConfig {

	@Bean
	CommandLineRunner commandLineRunner(CoronaRepository rep) {
		return new CommandLineRunner() {
			@Autowired
			private CityRepository CityRep;

			@Override
			public void run(String... args) throws Exception {

				rep.deleteAll();
				rep.save(new CoronaCase("ANKARA", LocalDate.now(), 1, 5, 10));
				rep.save(new CoronaCase("DENIZLI", LocalDate.now().minusDays(1), 1, 5, 10));
				rep.save(new CoronaCase("ISTANBUL", LocalDate.now().minusDays(2), 1, 565, 346));

				CityRep.deleteAll();
				CityRep.save(new City("ADANA", 1));
				CityRep.save(new City("ADIYAMAN", 2));
				CityRep.save(new City("AFYONKARAHISAR", 3));
				CityRep.save(new City("AGRI", 4));
				CityRep.save(new City("AKSARAY", 68));
				CityRep.save(new City("AMASYA", 5));
				CityRep.save(new City("ANKARA", 6, 1));
				CityRep.save(new City("ANTALYA", 7));
				CityRep.save(new City("ARDAHAN", 75));
				CityRep.save(new City("ARTVIN", 8));
				CityRep.save(new City("AYDIN", 9));
				CityRep.save(new City("BALIKESIR", 10));
				CityRep.save(new City("BARTIN", 74));
				CityRep.save(new City("BATMAN", 72));
				CityRep.save(new City("BAYBURT", 69));
				CityRep.save(new City("BILECIK", 11));
				CityRep.save(new City("BINGOL", 12));
				CityRep.save(new City("BITLIS", 13));
				CityRep.save(new City("BOLU", 14));
				CityRep.save(new City("BURDUR", 15));
				CityRep.save(new City("BURSA", 16));
				CityRep.save(new City("CANAKKALE", 17));
				CityRep.save(new City("CANKIRI", 18));
				CityRep.save(new City("CORUM", 19));
				CityRep.save(new City("DENIZLI", 20, 1));
				CityRep.save(new City("DIYARBAKIR", 21));
				CityRep.save(new City("DUZCE", 81));
				CityRep.save(new City("EDIRNE", 22));
				CityRep.save(new City("ELAZIG", 23));
				CityRep.save(new City("ERZINCAN", 24));
				CityRep.save(new City("ERZURUM", 25));
				CityRep.save(new City("ESKISEHIR", 26));
				CityRep.save(new City("GAZIANTEP", 27));
				CityRep.save(new City("GIRESUN", 28));
				CityRep.save(new City("GUMUSHANE", 29));
				CityRep.save(new City("HAKKARI", 30));
				CityRep.save(new City("HATAY", 31));
				CityRep.save(new City("IGDIR", 76));
				CityRep.save(new City("ISPARTA", 32));
				CityRep.save(new City("ISTANBUL", 34, 1));
				CityRep.save(new City("IZMIR", 35));
				CityRep.save(new City("KAHRAMANMARAS", 46));
				CityRep.save(new City("KARABUK", 78));
				CityRep.save(new City("KARAMAN", 70));
				CityRep.save(new City("KARS", 36));
				CityRep.save(new City("KASTAMONU", 37));
				CityRep.save(new City("KAYSERI", 38));
				CityRep.save(new City("KIRIKKALE", 71));
				CityRep.save(new City("KIRKLARELI", 39));
				CityRep.save(new City("KIRSEHIR", 40));
				CityRep.save(new City("KILIS", 79));
				CityRep.save(new City("KOCAELI", 41));
				CityRep.save(new City("KONYA", 42));
				CityRep.save(new City("KUTAHYA", 43));
				CityRep.save(new City("MALATYA", 44));
				CityRep.save(new City("MANISA", 45));
				CityRep.save(new City("MARDIN", 47));
				CityRep.save(new City("MERSIN", 33));
				CityRep.save(new City("MUGLA", 48));
				CityRep.save(new City("MUS", 49));
				CityRep.save(new City("NEVSEHIR", 50));
				CityRep.save(new City("NIGDE", 51));
				CityRep.save(new City("ORDU", 52));
				CityRep.save(new City("OSMANIYE", 80));
				CityRep.save(new City("RIZE", 53));
				CityRep.save(new City("SAKARYA", 54));
				CityRep.save(new City("SAMSUN", 55));
				CityRep.save(new City("SIIRT", 56));
				CityRep.save(new City("SINOP", 57));
				CityRep.save(new City("SIVAS", 58));
				CityRep.save(new City("SANLIURFA", 63));
				CityRep.save(new City("SIRNAK", 73));
				CityRep.save(new City("TEKIRDAG", 59));
				CityRep.save(new City("TOKAT", 60));
				CityRep.save(new City("TRABZON", 61));
				CityRep.save(new City("TUNCELI", 62));
				CityRep.save(new City("USAK", 64));
				CityRep.save(new City("VAN", 65));
				CityRep.save(new City("YALOVA", 77));
				CityRep.save(new City("YOZGAT", 66));
				CityRep.save(new City("ZONGULDAK", 67));

			}
		};

	}
}
