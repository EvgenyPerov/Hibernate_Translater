package entities;

import javax.persistence.*;

@Entity
@Table(name = "russian_words")
public class RussianWords implements Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "words")
    private  String words;

    // это поле нужно для JOIN таблиц
    @JoinColumn(name="id_eng")
    @ManyToOne(fetch = FetchType.LAZY)
    private EnglishWords english_words;

    public RussianWords(){ }

    public RussianWords(String word) {
        this.words = word;
    }


    // этот метод нужен для JOIN
    public void setEng(Word word){
        english_words = (EnglishWords) word;
    }

    public int getId() {
        return id;
    }

    public String getWords() {
        return words;
    }

    public EnglishWords getEnglish_words() {
        return english_words;
    }

    @Override
    public String toString() {
        return "RussianWords{" +
                "words='" + words + '\'' +
                ", engWordForJoin=" + english_words +
                '}';
    }
}
