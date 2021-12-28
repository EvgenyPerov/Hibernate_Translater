package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "english_words")
public class EnglishWords implements Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "words")
    private  String words;

    @Column(name = "transcript")
    private String transcript;

    @Column // имя можно не указывать, потому что оно совпадает с таблицей
    private int level;

    // это поле нужно для JOIN таблиц
    @OneToMany(mappedBy = "english_words", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RussianWords> rusWordsList;

    public EnglishWords(){ } // пустой конструктор

    public EnglishWords(String words,  int level){
        this.words = words;
        this.transcript ="[]";
        this.level = level;
        rusWordsList= new ArrayList<>();
    }

    public EnglishWords(String words, String transcript, int level) {
        this.words = words;
        this.transcript = transcript;
        this.level = level;
        rusWordsList= new ArrayList<>();
    }

    // этот метод нужен для JOIN таблиц
    public void setTranslateRusWord(Word word){
        RussianWords rusWord = (RussianWords) word;
        rusWordsList.add(rusWord);
        rusWord.setEng(this);

    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public void updateLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getWords() {
        return words;
    }

    public String getTranscript() {
        return transcript;
    }

    public int getLevel() {
        return level;
    }

    public List<RussianWords> getRusWordsList() {
        return rusWordsList;
    }

    @Override
    public String toString() {
        return "EnglishWords{" +
                "words='" + words + '\'' +
                ", transcript='" + transcript + '\'' +
                ", level=" + level +
                '}';
    }
}
