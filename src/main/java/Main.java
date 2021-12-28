import View.ClientView;
import entities.EnglishWords;
import entities.RussianWords;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import services.Language;
import services.Service;

import javax.persistence.metamodel.EntityType;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {

   public static void main(final String[] args) throws Exception {
       Service service = new Service();

       ClientView clientView = new ClientView(service);

       //*** String engWord, String transcript, int level, String...rusWordList**//
//       Set<String> set = new TreeSet<String>();
//       set.add("Слева");  set.add("Налево");  set.add("Покинул");
//       service.inputData("Left", "[lэft]", 2, set);

       //*** String engWord, int level, String...rusWordList**(without Transcription//
//       service.inputData("Sequentially", 4, "set");

//       System.out.println(service.getTranstate("Sequentially"));

//       System.out.println(service.delete("Go"));

//       service.deleteAllWords();

//       System.out.println(service.getEngWordsByLevel(4));

    }
}