package com.own.sqlite1.model;

/**
 * Created by DLira on 25/03/2018.
 */

public class Anime {
    private String idAnime;
    private String name;
    private String creator;
    private String gender;
    private String year;
    private String chapters;

    //Constructor
    public Anime(String idAnime, String name, String creator, String gender, String  year, String chapters) {
        this.idAnime = idAnime;
        this.name = name;
        this.creator = creator;
        this.gender = gender;
        this.year = year;
        this.chapters = chapters;
    }

    //Constructor empty
    public Anime(){
        this("", "", "", "", "","");
    }

    //Generated getters and setters
    public String getIdAnime() {return idAnime;}

    public void setIdAnime(String idAnime) {this.idAnime = idAnime;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getCreator() {return creator;}

    public void setCreator(String creator) {this.creator = creator;}

    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}

    public String getYear() {return year;}

    public void setYear(String year) {this.year = year;}

    public String getChapters() {return chapters;}

    public void setChapters(String chapters) {this.chapters = chapters;}

    //Generated toString
    @Override
    public String toString() {
        return "Animes{" +
                "idAnime='" + idAnime + '\'' +
                ", name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", gender='" + gender + '\'' +
                ", year=" + year +
                ", chapters=" + chapters +
                '}';
    }

}//End
