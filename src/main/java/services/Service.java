package services;

import entities.EnglishWords;
import entities.RussianWords;
import entities.Word;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Service {

    public void inputData(String engWord, String transcript, int level, Set<String> rusWordsTranslate) {
        if (!isExist(engWord)) {
        EnglishWords engWordRes = new EnglishWords(engWord, transcript, level);
        addWordToTable(engWordRes, Language.Eng);

        for (String trans: rusWordsTranslate) {
        RussianWords rusWordTrans = new RussianWords(trans);
        engWordRes.setTranslateRusWord(rusWordTrans);
        rusWordTrans.setEng(engWordRes);
        }
        update(engWordRes);
        } else
            System.out.println("Word '"+engWord +"' already exist in the vocabluary");
    }

    public void inputData(String engWord, int level, Set<String> rusWordsTranslate) {
        EnglishWords engWordRes = new EnglishWords(engWord, level);
        addWordToTable(engWordRes, Language.Eng);

        for (String trans: rusWordsTranslate) {
            RussianWords rusWordTrans = new RussianWords(trans);
            engWordRes.setTranslateRusWord(rusWordTrans);
            rusWordTrans.setEng(engWordRes);
        }
        update(engWordRes);
    }

    public void addWordToTable(Word word, Language language) {
        EnglishWords wordE = (EnglishWords) word;
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(wordE);
        transaction.commit();
        session.close();
    }

    public void update(Word word){
        EnglishWords wordE = (EnglishWords) word;
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(wordE);
        transaction.commit();
        session.close();
    }

    public boolean deleteAllWords(){
        Session session = HibernateSessionFactory.getSession();
        List<EnglishWords> list = session.createQuery("FROM EnglishWords ").list();
        if (!list.isEmpty()) {
            Transaction tx1 = session.beginTransaction();
            for (EnglishWords englishWord : list) {
                session.delete(englishWord);
            }
            tx1.commit();
        }
        session.close();
        return  true;
    }

    public boolean delete(String word){
        if (isExist(word)) {
            Session session = HibernateSessionFactory.getSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(getEnglishWordbyName(word));
            tx1.commit();
            session.close();
            return true;
        }else
            System.out.println("Word '"+word +"' NOT exist in the vocabluary");
        return false;
    }
// *** вспомогательный метод получения обьекта по значению поля. Используется в Delete ***
    public EnglishWords getEnglishWordbyName(String name){
        EnglishWords result= null;
        Session session = HibernateSessionFactory.getSession();
        List<EnglishWords> list = session.createQuery("FROM EnglishWords ").list();
        for (EnglishWords englishWord: list ) {
            if (englishWord.getWords().equalsIgnoreCase(name)) {
                result = englishWord;
                break;
            }
        }
        session.close();
        return result;
    }

        // проверка что такое слово есть в Английском  словаре
    public boolean isExist(String word){
        boolean result= false;
        Session session = HibernateSessionFactory.getSession();
        List<EnglishWords> list = session.createQuery("FROM EnglishWords ").list();
        for (EnglishWords englishWord: list ) {
            if (englishWord.getWords().equalsIgnoreCase(word))
                result = true;
//                break;
        }
        session.close();
        return result;
    }
    //*** получить русский перевод для введенного английского слова ***
    public String getTranstate(String engWord) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx1 = session.beginTransaction();
        StringBuilder sb = new StringBuilder();
        if (isExist(engWord)) {
            String sql2 = "FROM RussianWords";
            Query query2 = session.createQuery(sql2);
            Iterator iterator2 = query2.stream().iterator();
            while (iterator2.hasNext()) {
                RussianWords rus2 = (RussianWords) iterator2.next();
                if (rus2.getEnglish_words().getWords().equalsIgnoreCase(engWord)) {
                    sb.append(rus2.getWords() + "\n");
                }
            }
        } else  sb.append("Inputed word not found");
        tx1.commit();
        session.close();
        return sb.toString();
    }

    // *** получить список слов по уровню сложности <= ***
    public String getEngWordsByLevel(int level){
        Session session = HibernateSessionFactory.getSession();
        Transaction tx1 = session.beginTransaction();
        List<EnglishWords> list = session.createQuery("FROM EnglishWords ").list();
        List<EnglishWords> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (!list.isEmpty() && (level>=1) && (level<=5)) {
            result = list.stream().filter(word -> word.getLevel()<=level).collect(Collectors.toList());
            for (EnglishWords englishWord: result ) {
            sb.append(englishWord.getWords()+ "\n") ;
            }
        } else   sb.append("Input incorrect (level from 1 to 5), or Empty Database");
        tx1.commit();
        session.close();
        return sb.toString();
    }

}
